package com.retro.api.service;

import com.retro.api.dto.request.*;
import com.retro.api.dto.response.ProductDetailsDTO;
import com.retro.api.dto.response.ProductOverviewDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailsDTO createProduct(CreateProductDTO createProductDTO);

    ProductDetailsDTO getProductDetails(UUID id);

    List<ProductOverviewDTO> getListProductOverview();

    ProductDetailsDTO updateProductInfo(UUID id, UpdateProductInfoDTO updateProductInfo);

    ProductDetailsDTO addRating(UUID id, AddRatingProductDTO addRatingProduct);

    ProductDetailsDTO updateSalePrice(UUID id, UpdateProductPriceDTO updateProductPrice);

    ProductDetailsDTO updateState(UUID id, UpdateProductStateDTO updateProductState);

    void deleteProduct(UUID id);
}
