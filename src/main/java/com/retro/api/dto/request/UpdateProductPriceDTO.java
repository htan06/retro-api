package com.retro.api.dto.request;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class UpdateProductPriceDTO {
    private UUID productID;
    private BigDecimal newPrice;
}
