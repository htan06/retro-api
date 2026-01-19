package com.retro.api.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retro.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class UserInfoDTO {
    private UUID id;

    private String username;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public static UserInfoDTO from(User u) {
        return new UserInfoDTO(
                u.getId(),
                u.getUsername(),
                u.getFirstName(),
                u.getLastName()
        );
    }
}
