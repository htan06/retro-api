package com.retro.api.dto.response;

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

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("data")
    private T data;
}
