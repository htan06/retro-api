package com.retro.api.dto.request;

import com.retro.api.entity.ProductState;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateProductDTO {
    private String sku;
    private String productName;
    private String thumbnail;
    private String summary;
    private String descriptions;
    private UUID categoryId;
    private UUID brandId;
    private ProductState productState;
    private BigDecimal salePrice;
    private Integer discount;
    private List<String> imageUrl;
}
