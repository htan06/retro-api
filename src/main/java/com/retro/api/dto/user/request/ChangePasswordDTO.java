package com.retro.api.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChangePasswordDTO {
    @NotNull
    @Size(min = 6)
    @JsonProperty("new_password")
    private String newPassword;
}
