package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.enums.Role;
import com.swp.birthdaybooking.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public ResponseEntity<ResponseObject> getAll() {
        try {
            var list = cartRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found carts", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find carts", null));
        }
    }
}
