package com.swp.birthdaybooking.Controllers;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.config.VnPayConfig;
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

    public PaymentController(VnPayService vnPayService) {
        this.vnPayService = vnPayService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> payment(@RequestParam(name = "bill_id") Integer billId, HttpServletRequest req) {

        return ResponseEntity
                .ok(new ResponseObject("Successful", "Payment successful",
                        vnPayService.createOrder(billId, paymentReturnUrl, VnPayConfig.getIpAddress(req))));
    }

    @GetMapping("/vnpay-payment")
    public ResponseEntity<?> getResult(@RequestParam(name = "vnp_TransactionStatus") String vnpTransactionStatus) {
        return vnpTransactionStatus.equals("00") ?
                ResponseEntity.ok(new ResponseObject("Successful", "Payment successful", null)) :
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("Failed", "Payment processing error", null));
    }

}
