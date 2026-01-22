package com.retro.api.service.impl;

import com.retro.api.dto.auth.request.UserLoginDTO;
import com.retro.api.dto.auth.response.UserLoginResponseDTO;
import com.retro.api.dto.user.request.ChangePasswordDTO;
import com.retro.api.dto.auth.request.UserRegisterDTO;
import com.retro.api.dto.user.response.UserInfoDTO;
import com.retro.api.entity.enums.AccountStatus;
import com.retro.api.entity.Role;
import com.retro.api.entity.enums.RoleEnum;
import com.retro.api.entity.User;
import com.retro.api.exception.IdentityException;
import com.retro.api.exception.IdentityExceptionEnum;
import com.retro.api.repository.RoleRepository;
import com.retro.api.repository.UserRepository;
import com.retro.api.service.AuthService;
import com.retro.api.service.JwtService;
import com.retro.api.service.enums.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserInfoDTO register(UserRegisterDTO userRegister) {
        String hashPassword = passwordEncoder.encode(userRegister.getPassword());

        Role role = roleRepository.findByRoleName(RoleEnum.USER.name())
                .orElseThrow(() -> new IdentityException(IdentityExceptionEnum.ROLE_NOT_FOUND));

        User user = User.builder()
                .firstName(userRegister.getFirstName())
                .lastName(userRegister.getLastName())
                .phoneNumber(userRegister.getPhoneNumber())
                .email(userRegister.getEmail())
                .username(userRegister.getUsername())
                .password(hashPassword)
                .roles(Set.of(role))
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        return UserInfoDTO.from(userRepository.save(user));
    }

    @Override
    public UserLoginResponseDTO login(UserLoginDTO userLogin) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword())
        );

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        String accessToken = jwtService.generaToken(TokenType.ACCESS, userLogin.getUsername(), roles);
        String refreshToken = jwtService.generaToken(TokenType.REFRESH, userLogin.getUsername(), roles);

        return UserLoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public UserInfoDTO changePassword(String username, ChangePasswordDTO changePassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IdentityException(IdentityExceptionEnum.USER_NOT_FOUND));

        boolean matchCurrentPassword = passwordEncoder.matches(changePassword.getCurrentPassword(), user.getPassword());

        if (matchCurrentPassword) {
            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        } else {
            throw new IdentityException(IdentityExceptionEnum.CURRENT_PASSWORD_IS_INCORRECT);
        }

        return UserInfoDTO.from(
                userRepository.save(user)
        );
    }

    @Override
    public void lockUser(UUID id) {

    }

    @Override
    public void deleteUser(UUID id) {

    }
}
