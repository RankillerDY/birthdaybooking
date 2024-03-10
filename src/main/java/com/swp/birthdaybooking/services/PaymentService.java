package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreatePaymentRq;
import com.swp.birthdaybooking.entities.Payment;
import com.swp.birthdaybooking.mapper.PaymentMapper;
import com.swp.birthdaybooking.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public Payment createPayment(CreatePaymentRq paymentRq) {
        return paymentRepository.save(paymentMapper.mapToPayment(paymentRq));
    }
}