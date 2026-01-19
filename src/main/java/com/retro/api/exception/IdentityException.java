package com.retro.api.exception;

public class IdentityException extends RuntimeException {
    public IdentityException(IdentityExceptionEnum identityExceptionEnum) {
        super(identityExceptionEnum.name());
    }
}
