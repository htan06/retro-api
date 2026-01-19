package com.retro.api.dto.product.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateProductPriceDTO {
    @DecimalMin("0.00")
    @JsonProperty("new_price")
    private BigDecimal newPrice;
}
