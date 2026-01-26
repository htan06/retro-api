package com.retro.api.service;

import com.retro.api.entity.enums.TransactionType;

import java.util.UUID;

public interface InventoryTransactionService {
    void addTransaction(String actorUsername, TransactionType transactionType, UUID productId, long quantity);
}
