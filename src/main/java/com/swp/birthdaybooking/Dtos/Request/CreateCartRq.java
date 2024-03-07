package com.swp.birthdaybooking.Dtos.Request;

public record CreateCartRq(Integer guestId, Integer packageId, Integer quantity, Integer price) {
    public CreateCartRq {
        if (guestId == null) {
            throw new IllegalArgumentException("GuestId is required");
        }
        if (packageId == null) {
            throw new IllegalArgumentException("PackageId is required");
        }
        if (quantity == null) {
            throw new IllegalArgumentException("Quantity is required");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price is required");
        }
    }
}
