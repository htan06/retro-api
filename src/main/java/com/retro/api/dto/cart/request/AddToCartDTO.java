package com.retro.api.dto.cart.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AddToCartDTO {
    @JsonProperty("product_id")
    private UUID productId;
}
