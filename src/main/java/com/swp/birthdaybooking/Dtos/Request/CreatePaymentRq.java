package com.swp.birthdaybooking.Dtos.Request;

import java.util.Date;

public record CreatePaymentRq(Integer billId, Date paymentDate, String method) {
}
