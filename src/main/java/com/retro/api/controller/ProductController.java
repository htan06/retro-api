package com.retro.api.controller;

import com.retro.api.dto.product.request.*;
import com.retro.api.dto.api.ApiResponse;
import com.retro.api.dto.product.response.ProductDetailsDTO;
import com.retro.api.dto.product.response.ProductOverviewDTO;
import com.retro.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDetailsDTO> createProduct(
            @RequestPart(name = "info") @Valid CreateProductDTO createProduct,
            @RequestPart(name = "thumbnail") MultipartFile thumbnail,
            @RequestPart(name = "images") List<MultipartFile> images)
    {

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(201)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.createProduct(createProduct, thumbnail, images))
                .build();
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ProductOverviewDTO>> getListProductOverview() {
        return ApiResponse.<List<ProductOverviewDTO>>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.getListProductOverview())
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductDetailsDTO> getProductDetails(@PathVariable UUID id) {
        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.getProductDetails(id))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PatchMapping("/{id}/update-info")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<ProductDetailsDTO> updateProductInfo(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductInfoDTO updateProductInfo) {

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.updateProductInfo(id, updateProductInfo))
                .build();
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{id}/add-rating")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<ProductDetailsDTO> addRating(
            @PathVariable UUID id,
            @RequestBody @Valid AddRatingProductDTO addRatingProduct) {

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.addRating(id, addRatingProduct))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PatchMapping("/{id}/update-sale-price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<ProductDetailsDTO> updateSalePrice(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductPriceDTO updateProductPrice) {

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.updateSalePrice(id, updateProductPrice))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @PatchMapping("/{id}/update-state")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<ProductDetailsDTO> updateStatus(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateProductStatusDTO updateProductState) {

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.updateStatus(id, updateProductState))
                .build();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<ProductDetailsDTO> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);

        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .build();
    }
}
