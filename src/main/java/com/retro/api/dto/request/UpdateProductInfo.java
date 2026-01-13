package com.retro.api.dto.request;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class UpdateProductInfo {
    private UUID id;
    private String productName;
    private String thumbnail;
    private String summary;
    private String descriptions;
    private List<String> imageUrl;
}
