package com.retro.api.dto.inventory.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class InventoryDTO {
    @JsonProperty("sku")
    private String sku;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("import_price")
    private BigDecimal importPrice;

    @JsonProperty("sale_price")
    private BigDecimal salePrice;

    @JsonProperty("discount")
    private int discount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("stock_quantity")
    private long stockQuantity;

    @JsonProperty("sold_quantity")
    private long soldQuantity;

    public static InventoryDTO from(Inventory i) {
        return new InventoryDTO(
                i.getProduct().getSku(),
                i.getProduct().getProductName(),
                i.getImportPrice(),
                i.getProduct().getSalePrice(),
                i.getProduct().getDiscount(),
                i.getProduct().getProductStatus().name(),
                i.getStockQuantity(),
                i.getSoldQuantity()
        );
    }
}
