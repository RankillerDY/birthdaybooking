package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.Dtos.Request.CreateCartRq;
import com.swp.birthdaybooking.entities.Cart;
import com.swp.birthdaybooking.mapper.CartMapper;
import com.swp.birthdaybooking.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService extends BaseService<Cart,Integer> {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

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
