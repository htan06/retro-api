package com.retro.api.dto.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBrandInfoDTO {
    private UUID id;
    private String name;
}
