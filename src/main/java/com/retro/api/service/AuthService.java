package com.retro.api.service;

import com.retro.api.dto.auth.request.UserLoginDTO;
import com.retro.api.dto.auth.response.UserLoginResponseDTO;
import com.retro.api.dto.user.request.ChangePasswordDTO;
import com.retro.api.dto.auth.request.UserRegisterDTO;
import com.retro.api.dto.user.response.UserInfoDTO;

import java.util.UUID;

public interface AuthService {
    UserInfoDTO register(UserRegisterDTO userRegister);

    UserLoginResponseDTO login(UserLoginDTO userLogin);

    UserInfoDTO changePassword(ChangePasswordDTO changePassword);

    void lockUser(UUID id);

    void deleteUser(UUID id);
}
