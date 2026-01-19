package com.retro.api.dto.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrResponse<T> {
    private T err;

    private Date timestamp;

    private String path;
}
