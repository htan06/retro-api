package com.retro.api.dto.cart.respoonse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class CartItemResponse {
    private UUID id;

    @JsonProperty("product_name")
    private String productName;

    private String thumbnail;
    private int quantity;
    private BigDecimal price;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getProduct().getProductName(),
                cartItem.getProduct().getThumbnail(),
                cartItem.getQuantity(),
                cartItem.getProduct().getSalePrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()))
        );
    }
}
