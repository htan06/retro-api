package com.retro.api.dto.product.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProductInfoDTO {
    @NotBlank
    @JsonProperty("product_name")
    private String productName;

    @NotBlank
    private String thumbnail;

    @NotBlank
    private String summary;

    @NotBlank
    private String descriptions;

    @NotNull
    @JsonProperty("image_urls")
    private List<@NotBlank String> imageUrl;
}
