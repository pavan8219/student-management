package com.pavan.studentmanagement.service;

import com.pavan.studentmanagement.dto.LoginRequestDto;
import com.pavan.studentmanagement.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName()
                        ,loginRequestDto.getPassword()));

        User user= (User) authentication.getPrincipal();

        String accessToken=jwtService.createAccessToken(user);
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .build();

    }
}
