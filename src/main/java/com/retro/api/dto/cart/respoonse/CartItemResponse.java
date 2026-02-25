package com.retro.api.dto.cart.respoonse;

import com.retro.api.entity.CartItem;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class CartItemResponse {
    private String productName;
    private String thumbnail;
    private int quantity;
    private BigDecimal price;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getProduct().getProductName(),
                cartItem.getProduct().getThumbnail(),
                cartItem.getQuantity(),
                cartItem.getProduct().getSalePrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
        );
    }
}
