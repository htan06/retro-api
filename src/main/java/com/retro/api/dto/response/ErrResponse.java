package com.retro.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrResponse {
    @JsonProperty("err")
    private List<String> err;

    @JsonProperty("timestamp")
    private Date timestamp;

    @JsonProperty("path")
    private String path;
}
