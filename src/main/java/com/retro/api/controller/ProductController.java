package com.retro.api.controller;

import com.retro.api.dto.request.CreateProductDTO;
import com.retro.api.dto.response.ApiResponse;
import com.retro.api.dto.response.ProductDetailsDTO;
import com.retro.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductDetailsDTO> createProduct(@RequestBody @Valid CreateProductDTO createProduct) {
        return ApiResponse.<ProductDetailsDTO>builder()
                .statusCode(201)
                .message("Successfully")
                .timestamp(new Date())
                .data(productService.createProduct(createProduct))
                .build();
    }
}
