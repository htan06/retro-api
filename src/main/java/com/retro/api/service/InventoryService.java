package com.retro.api.service;

import com.retro.api.dto.inventory.request.RequestInventoryDTO;
import com.retro.api.dto.inventory.response.InventoryDTO;

import java.util.UUID;

public interface InventoryService {
    InventoryDTO update(UUID id, String managerUsername, RequestInventoryDTO requestInventory);
}
