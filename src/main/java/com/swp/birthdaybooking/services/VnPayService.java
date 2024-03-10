package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.config.VnPayConfig;
import com.swp.birthdaybooking.repositories.BillDetailRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class VnPayService {
    private final BillDetailRepository billDetailRepository;
    private final VnPayConfig vnPayConfig;

    public VnPayService(BillDetailRepository billDetailRepository, VnPayConfig vnPayConfig) {
        this.billDetailRepository = billDetailRepository;
        this.vnPayConfig = vnPayConfig;
    }

    public String createOrder(Integer billId, String urlReturn, String vnp_IpAddr) {
        Long total = billDetailRepository.getTotalPriceByBillId(billId);

        var vnp_Version = vnPayConfig.vnp_Version;
        var vnp_Command = vnPayConfig.vnp_Command;
        var vnp_TxnRef = vnPayConfig.getRandomNumber(8);
        var vnp_TmnCode = vnPayConfig.vnp_TmnCode;
        var orderType = "order-type";

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        var vnp_CreateDate = formatter.format(cld.getTime());

        cld.add(Calendar.MINUTE, 15);
        var vnp_ExpireDate = formatter.format(cld.getTime());
        var vnpParams = new HashMap<String, String>() {{
            put("vnp_Version", vnp_Version);
            put("vnp_Command", vnp_Command);
            put("vnp_TmnCode", vnp_TmnCode);
            put("vnp_Amount", String.valueOf(total * 100));
            put("vnp_CurrCode", "VND");
            put("vnp_TxnRef", vnp_TxnRef);
            put("vnp_OrderInfo", "Thanh toán đơn hàng :" + vnp_TxnRef);
            put("vnp_OrderType", orderType);
            put("vnp_Locale", "vn");
            put("vnp_ReturnUrl", urlReturn);
            put("vnp_IpAddr", vnp_IpAddr);
            put("vnp_CreateDate", vnp_CreateDate);
            put("vnp_ExpireDate", vnp_ExpireDate);
        }};

        var fieldNames = vnpParams.keySet().stream().sorted().toList();
        var hashData = new StringBuilder();
        var query = new StringBuilder();
        var itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnpParams.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        var queryUrl = query.toString();
        var vnp_SecureHash = vnPayConfig.hmacSHA512(vnPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return vnPayConfig.vnp_PayUrl + "?" + queryUrl;
    }

    public int orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = vnPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
}
