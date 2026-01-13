package com.retro.api.exception;

public enum CatalogExceptionEnum {
    PRODUCT_NOT_FOUND("Product not found"),
    PRICE_INVALID("The price must be greater than or equal to 0."),
    DISCOUNT_INVALID("The discount must be greater than or equal to 0 and less than or equal to 100."),
    RATING_INVALID("Rating must be greater than or equal to 0 and less than or equal to 5."),
    VARIANT_NOT_FOUND("Product variant not found."),
    SKU_INVALID("Sku must be not blank"),
    PRODUCT_NAME_INVALID("Product name must not be blank"),
    PRODUCT_THUMBNAIL_INVALID("Product thumbnail must not be blank"),
    PRODUCT_SUMMARY_INVALID("Product summary must not be blank"),
    PRODUCT_IMAGES_INVALID("Product images must not be blank"),
    PRODUCT_DESCRIPTIONS_INVALID("Product descriptions must not be blank"),
    TOTAL_RATING_INVALID("Total rating must be initialized as 0"),
    REVIEW_COUNT_INVALID("Review count must be initialized as 0"),
    PRODUCT_VARIANT_DUPLICATE("Product variant must be unique"),
    PRODUCT_VARIANT_NOT_FOUND("Product variant not found"),

    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_NAME_INVALID("Category name must be not blank"),

    BRAND_NOT_FOUND("Brand not found"),
    BRAND_NAME_INVALID("Brand name must be not blank"),

    COLOR_NOT_FOUND("Color not found"),
    COLOR_NAME_INVALID("Color name must be not blank"),
    COLOR_HEX_NULL("Color hex is null"),
    COLOR_HEX_INVALID("Hexadecimal color codes must begin with the '#' character and have exactly 3 or 6 characters."),

    SIZE_NOT_FOUND("Size not found."),
    SIZE_NAME_INVALID("Size name must be not blank");

    private String message;

    CatalogExceptionEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
