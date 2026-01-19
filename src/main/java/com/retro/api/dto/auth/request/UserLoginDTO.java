package com.retro.api.dto.auth.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserLoginDTO {
    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;
}
