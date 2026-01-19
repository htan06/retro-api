package com.retro.api.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Builder
public class ApiResponse<T> {
    @JsonProperty("status_code")
    private int statusCode;

    private String message;

    private Date timestamp;

    private T data;
}
