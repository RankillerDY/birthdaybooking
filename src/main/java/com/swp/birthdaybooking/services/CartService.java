package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateCartRq;
import com.swp.birthdaybooking.entities.Cart;
import com.swp.birthdaybooking.mapper.CartMapper;
import com.swp.birthdaybooking.repositories.CartRepository;
import com.swp.birthdaybooking.Dtos.Response.ResponseObject;
import com.swp.birthdaybooking.enums.Role;
import com.swp.birthdaybooking.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<Cart,Integer> {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public ResponseEntity<ResponseObject> getAll() {
        try {
            var list = cartRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Successful", "Found carts", list));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Couldn't find carts", null));
        }
    }
    public CartService(CartRepository cartRepository, CartMapper cartMapper) {
        super(cartRepository);
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public Cart getCartById(Integer cartId) {
        return getById(cartId);
    }

    /**
     * Add a product to the cart
     */
    public Cart addPackageToCart(CreateCartRq createCartRq) {
        return cartRepository.save(cartMapper.mapToCart(createCartRq));
    }
}
