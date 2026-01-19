package com.retro.api.dto.product.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class AddRatingProductDTO {
    @Min(1)
    @Max(5)
    private int rating;
}
