package com.retro.api.service.impl;

import com.retro.api.dto.user.request.UpdateUserInfoDTO;
import com.retro.api.dto.user.response.UserInfoDTO;
import com.retro.api.entity.User;
import com.retro.api.entity.enums.AccountStatus;
import com.retro.api.exception.IdentityException;
import com.retro.api.exception.IdentityExceptionEnum;
import com.retro.api.repository.UserRepository;
import com.retro.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfoDTO getUserInfo(UUID id) {
        User user = findUserById(id);
        return UserInfoDTO.from(user);
    }

    @Override
    public UserInfoDTO updateUserInfo(UUID id, UpdateUserInfoDTO updateUserInfo) {
        User user = findUserById(id);

        user.setFirstName(updateUserInfo.getFirstName());
        user.setLastName(updateUserInfo.getLastName());

        return UserInfoDTO.from(
                userRepository.save(user)
        );
    }

    @Override
    public User findUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IdentityException(IdentityExceptionEnum.USER_NOT_FOUND));
    }
}
