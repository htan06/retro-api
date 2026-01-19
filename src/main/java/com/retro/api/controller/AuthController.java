package com.retro.api.controller;

import com.retro.api.dto.api.ApiResponse;
import com.retro.api.dto.auth.request.UserLoginDTO;
import com.retro.api.dto.auth.request.UserRegisterDTO;
import com.retro.api.dto.auth.response.UserLoginResponseDTO;
import com.retro.api.dto.user.request.ChangePasswordDTO;
import com.retro.api.dto.user.response.UserInfoDTO;
import com.retro.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> register(@RequestBody @Valid UserRegisterDTO userRegister) {

        authService.register(userRegister);

        return ApiResponse.<Void>builder()
                .statusCode(201)
                .message("Successfully")
                .timestamp(new Date())
                .build();
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponseDTO> login(@RequestBody @Valid UserLoginDTO userLogin) {

        UserLoginResponseDTO response = authService.login(userLogin);

        return ApiResponse.<UserLoginResponseDTO>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(response)
                .build();
    }

    @PatchMapping("/change-password")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserInfoDTO> changePassword(Principal principal, @RequestBody @Valid ChangePasswordDTO changePassword) {
        UserInfoDTO info = authService.changePassword(principal.getName(), changePassword);

        return ApiResponse.<UserInfoDTO>builder()
                .statusCode(200)
                .message("Successfully")
                .timestamp(new Date())
                .data(info)
                .build();

    }
}
