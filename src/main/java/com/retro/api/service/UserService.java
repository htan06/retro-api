package com.retro.api.service;

import com.retro.api.dto.user.request.UpdateUserInfoDTO;
import com.retro.api.dto.user.response.UserInfoDTO;
import com.retro.api.entity.User;

import java.util.UUID;

public interface UserService {
    UserInfoDTO getUserInfo(UUID id);

    UserInfoDTO updateUserInfo(UUID id, UpdateUserInfoDTO updateUserInfo);

    User findUserById(UUID id);
}
