package com.retro.api.dto.inventory.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.entity.enums.TransactionType;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class RequestInventoryDTO {
    @JsonProperty("quantity")
    @Min(0)
    private long quantity;

    @JsonProperty("transaction_type")
    private TransactionType transactionType;
}
