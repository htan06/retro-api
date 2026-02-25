package com.retro.api.controller;

import com.retro.api.dto.api.ApiResponse;
import com.retro.api.dto.inventory.request.RequestInventoryDTO;
import com.retro.api.dto.inventory.response.InventoryDTO;
import com.retro.api.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<InventoryDTO> update(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable(name = "id") UUID id,
            @RequestBody @Valid RequestInventoryDTO requestInventory) {

        return ApiResponse.<InventoryDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(inventoryService.update(id, id, requestInventory))
                .build();
    }
}