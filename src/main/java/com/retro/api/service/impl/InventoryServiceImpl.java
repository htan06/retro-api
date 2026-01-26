package com.retro.api.service.impl;

import com.retro.api.dto.inventory.request.RequestInventoryDTO;
import com.retro.api.dto.inventory.response.InventoryDTO;
import com.retro.api.entity.Inventory;
import com.retro.api.repository.InventoryRepository;
import com.retro.api.service.InventoryService;
import com.retro.api.service.InventoryTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryTransactionService inventoryTransactionService;

    @Override
    @Transactional
    public InventoryDTO update(UUID id, String managerUsername, RequestInventoryDTO requestInventory) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        switch (requestInventory.getTransactionType()) {
            case SALE -> sale(inventory, requestInventory.getQuantity());
            case PURCHASE -> purchase(inventory, requestInventory.getQuantity());
            case RETURN_IN -> returnIn(inventory, requestInventory.getQuantity());
            case RETURN_OUT -> returnOut(inventory, requestInventory.getQuantity());
        }

        inventoryTransactionService.addTransaction(
                managerUsername,
                requestInventory.getTransactionType(),
                inventory.getProduct().getId(),
                requestInventory.getQuantity());

        return InventoryDTO.from(inventoryRepository.save(inventory));
    }

    private void purchase(Inventory inventory, long quantity) {
        inventory.setStockQuantity(inventory.getStockQuantity() + quantity);
    }

    private void sale(Inventory inventory, long quantity) {
        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
        inventory.setSoldQuantity(inventory.getSoldQuantity() + quantity);
    }

    private void returnIn(Inventory inventory, long quantity) {
        inventory.setStockQuantity(inventory.getStockQuantity() + quantity);
        inventory.setSoldQuantity(inventory.getSoldQuantity() - quantity);
    }

    private void returnOut(Inventory inventory, long quantity) {
        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
    }
}
