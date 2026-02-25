package com.retro.api.service;

import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;

import java.util.UUID;

public interface CartService {
    CartItem addToCart(User user, UUID productId);

    CartItem increaseQuantity(User user, UUID itemId, int quantity);

    CartItem decreaseQuantity(User user, UUID itemId, int quantity);

    void removeItem(User user, UUID itemId);

    void removeAllItem(User user);
}
