package com.retro.api.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserRegisterDTO {
    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;
}
