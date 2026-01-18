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

    ProductDetailsDTO updateProductInfo(UpdateProductInfoDTO updateProductInfo);

    ProductDetailsDTO addRating(AddRatingProductDTO addRatingProduct);

    ProductDetailsDTO updatePrice(UpdateProductPriceDTO updateProductPrice);

    ProductDetailsDTO updateState(UpdateProductStateDTO updateProductState);

    void deleteProduct(UUID id);
}
