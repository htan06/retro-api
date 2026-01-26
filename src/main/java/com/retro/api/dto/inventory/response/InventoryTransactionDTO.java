package com.retro.api.dto.inventory.response;

import com.retro.api.entity.InventoryTransaction;
import com.retro.api.entity.enums.TransactionType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InventoryTransactionDTO {

    private TransactionType transactionType;
    private String managerName;
    private String sku;
    private long quantity;

    public static InventoryTransactionDTO from(InventoryTransaction it) {
        return new InventoryTransactionDTO(
                it.getTransactionType(),
                "%s %s".formatted(it.getUser().getFirstName(), it.getUser().getLastName()),
                it.getProduct().getSku(),
                it.getQuantity()
        );
    }
}
