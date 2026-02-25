package com.retro.api.controller;

import com.retro.api.dto.api.ApiResponse;
import com.retro.api.dto.user.request.UpdateUserInfoDTO;
import com.retro.api.dto.user.response.UserInfoDTO;
import com.retro.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-info")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<UserInfoDTO> getInfo(@AuthenticationPrincipal(expression = "id") UUID id) {
        UserInfoDTO info = userService.getUserInfo(id);

        return ApiResponse.<UserInfoDTO>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(info)
                .build();
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER','ADMIN')")
    @PatchMapping
    public ApiResponse<UserInfoDTO> updateInfo(
            @AuthenticationPrincipal(expression = "id") UUID id,
            @RequestBody @Valid UpdateUserInfoDTO updateUserInfo)
    {
        UserInfoDTO updatedInfo = userService.updateUserInfo(id, updateUserInfo);

        return ApiResponse.<UserInfoDTO>builder()
                .statusCode(204)
                .message("Successfully")
                .timestamp(new Date())
                .data(updatedInfo)
                .build();
    }
}
