package com.retro.api.exception;

public class CatalogException extends RuntimeException {
    private String errCode;

    public CatalogException(CatalogExceptionEnum productExceptionEnum) {
        super(productExceptionEnum.toString());
        this.errCode = productExceptionEnum.name();
    }
}
