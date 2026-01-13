package com.retro.api.dto.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AddRatingProductDTO {
    private UUID productId;
    private int rating;
}
