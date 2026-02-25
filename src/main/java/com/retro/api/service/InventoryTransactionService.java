package com.retro.api.service;

import com.retro.api.entity.enums.TransactionType;

import java.util.UUID;

public interface InventoryTransactionService {
    void addTransaction(UUID actorId, TransactionType transactionType, UUID productId, long quantity);
}
