package com.retro.api.service.impl;

import com.retro.api.dto.inventory.response.InventoryTransactionDTO;
import com.retro.api.entity.InventoryTransaction;
import com.retro.api.entity.User;
import com.retro.api.entity.enums.TransactionType;
import com.retro.api.exception.IdentityException;
import com.retro.api.exception.IdentityExceptionEnum;
import com.retro.api.repository.InventoryTransactionRepository;
import com.retro.api.repository.ProductRepository;
import com.retro.api.repository.UserRepository;
import com.retro.api.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryTransactionServiceImpl implements InventoryTransactionService {

    private final InventoryTransactionRepository inventoryTransactionRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void addTransaction(UUID actorId, TransactionType transactionType, UUID productId, long quantity) {
        User userActor = userRepository.getReferenceById(actorId);

        InventoryTransaction inventoryTransaction = InventoryTransaction.builder()
                .user(userActor)
                .product(productRepository.getReferenceById(productId))
                .quantity(quantity)
                .transactionType(transactionType)
                .build();

        InventoryTransactionDTO.from(
                inventoryTransactionRepository.save(inventoryTransaction));
    }
}
