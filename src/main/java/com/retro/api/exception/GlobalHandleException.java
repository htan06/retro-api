package com.retro.api.exception;

import com.retro.api.dto.response.ApiResponse;
import com.retro.api.dto.response.ErrResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalHandleException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrResponse handleInvalidParam(HttpServletRequest request, MethodArgumentNotValidException ex) {
        List<FieldError> fieldError = ex.getFieldErrors();

        List<String> errResponse = fieldError
                .stream()
                .map(err -> "%s %s".formatted(err.getField(), err.getDefaultMessage()))
                .toList();

        return ErrResponse.builder()
                .err(errResponse)
                .timestamp(new Date())
                .path(request.getRequestURI())
                .build();
    }
}
