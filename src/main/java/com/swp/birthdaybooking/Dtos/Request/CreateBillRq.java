package com.swp.birthdaybooking.Dtos.Request;

public record CreateBillRq(Integer guestId, Integer cartId, String paymentMethod) {
}
