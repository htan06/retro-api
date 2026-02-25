package com.retro.api.controller;

import com.retro.api.dto.api.ApiResponse;
import com.retro.api.dto.cart.request.AddToCartDTO;
import com.retro.api.dto.cart.respoonse.CartItemResponse;
import com.retro.api.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<CartItemResponse>> getCart(
            @AuthenticationPrincipal(expression = "id") UUID userId) {

        return ApiResponse.<List<CartItemResponse>>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(cartService.getCart(userId))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CartItemResponse> addToCart(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @RequestBody @Valid AddToCartDTO addToCartDTO) {

        return ApiResponse.<CartItemResponse>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(cartService.addToCart(userId, addToCartDTO.getProductId()))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @PatchMapping("/{itemId}/in")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CartItemResponse> increaseQuantity(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID itemId) {

        return ApiResponse.<CartItemResponse>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(cartService.increaseQuantity(userId, itemId, 1))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @PatchMapping("/{itemId}/de")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<CartItemResponse> decreaseQuantity(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID itemId) {

        return ApiResponse.<CartItemResponse>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(cartService.decreaseQuantity(userId, itemId, 1))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> removeItem(
            @AuthenticationPrincipal(expression = "id") UUID userId,
            @PathVariable UUID itemId) {

        cartService.removeItem(userId, itemId);

        return ApiResponse.<Void>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(null)
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN', 'USER')")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> removeAllItem(
            @AuthenticationPrincipal(expression = "id") UUID userId) {

        cartService.removeAllItem(userId);

        return ApiResponse.<Void>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(null)
                .build();
    }
}
