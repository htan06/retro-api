package com.retro.api.service;

import com.retro.api.dto.product.request.*;
import com.retro.api.dto.product.response.ProductDetailsDTO;
import com.retro.api.dto.product.response.ProductOverviewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailsDTO createProduct(CreateProductDTO createProduct, MultipartFile thumbnail, List<MultipartFile> images);

    ProductDetailsDTO getProductDetails(UUID id);

    List<ProductOverviewDTO> getListProductOverview();

    ProductDetailsDTO updateProductInfo(UUID id, UpdateProductInfoDTO updateProductInfo);

    ProductDetailsDTO addRating(UUID id, AddRatingProductDTO addRatingProduct);

    ProductDetailsDTO updateSalePrice(UUID id, UpdateProductPriceDTO updateProductPrice);

    ProductDetailsDTO updateStatus(UUID id, UpdateProductStatusDTO updateProductStatus);

    void deleteProduct(UUID id);
}
