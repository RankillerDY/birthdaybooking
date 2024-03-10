package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Request.CreatePaymentRq;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.config.VnPayConfig;
import com.swp.birthdaybooking.services.PaymentService;
import com.swp.birthdaybooking.services.VnPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@Slf4j
public class PaymentController {
    private final VnPayService vnPayService;
    @Value("${payment.return-url}")
    private String paymentReturnUrl;
    private final VnPayConfig vnPayConfig;
    private final PaymentService paymentService;

    public PaymentController(VnPayService vnPayService, VnPayConfig vnPayConfig, PaymentService paymentService) {
        this.vnPayService = vnPayService;
        this.vnPayConfig = vnPayConfig;
        this.paymentService = paymentService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> payment(@RequestParam(name = "bill_id") Integer billId, HttpServletRequest req) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Payment successful",
                        vnPayService.createOrder(billId, paymentReturnUrl, vnPayConfig.getIpAddress(req)))
                );
    }

    @GetMapping("/vnpay-payment")
    public ResponseEntity<?> getResult(HttpServletRequest request,
                                       @RequestParam("vnp_Amount") Long vnp_Amount,
                                       @RequestParam("vnp_BankCode") String vnp_BankCode,
                                       @RequestParam("vnp_BankTranNo") String vnp_BankTranNo,
                                       @RequestParam("vnp_CardType") String vnp_CardType,
                                       @RequestParam("vnp_OrderInfo") String vnp_OrderInfo,
                                       @RequestParam("vnp_PayDate") String vnp_PayDate,
                                       @RequestParam("vnp_ResponseCode") String vnp_ResponseCode,
                                       @RequestParam("vnp_TmnCode") String vnp_TmnCode,
                                       @RequestParam("vnp_TransactionNo") String vnp_TransactionNo,
                                       @RequestParam("vnp_TransactionStatus") String vnp_TransactionStatus,
                                       @RequestParam("vnp_TxnRef") String vnp_TxnRef,
                                       @RequestParam("vnp_SecureHash") String vnp_SecureHash) {
        return vnp_TransactionStatus.equals("00") ?
                ResponseEntity.ok(new ResponseObject("Successful", "Payment successful", null)) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("Failed", "Payment processing error", null));
    }

    @PostMapping("/create-payment")
    public ResponseEntity<ResponseObject> createPayment(@RequestBody CreatePaymentRq paymentRq) {
        return ResponseEntity
                .ok(new ResponseObject("Successful", "Payment successful",
                        paymentService.createPayment(paymentRq))
                );
    }
}
