package com.retro.api.service;

import com.retro.api.dto.cart.respoonse.CartItemResponse;
import com.retro.api.entity.CartItem;
import com.retro.api.entity.User;

import java.util.List;
import java.util.UUID;

public interface CartService {
    List<CartItemResponse> getCart(UUID userId);

    CartItemResponse addToCart(UUID userId, UUID productId);

    CartItemResponse increaseQuantity(UUID userId, UUID itemId, int quantity);

    CartItemResponse decreaseQuantity(UUID userId, UUID itemId, int quantity);

    void removeItem(UUID userId, UUID itemId);

    void removeAllItem(UUID userId);
}
