package com.retro.api.service;

import com.retro.api.dto.user.request.UpdateUserInfoDTO;
import com.retro.api.dto.user.response.UserInfoDTO;

import java.util.UUID;

public interface UserService {
    UserInfoDTO getUserInfo(String username);

    UserInfoDTO updateUserInfo(String username, UpdateUserInfoDTO updateUserInfo);
}
