package com.retro.api.dto.cart.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AddToCartDTO {
    private UUID productID;
}
