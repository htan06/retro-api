package com.retro.api.exception;

public enum IdentityExceptionEnum {
    USER_NOT_FOUND("User not found"),
    ROLE_NOT_FOUND("Role not found");

    private String message;

    IdentityExceptionEnum(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
