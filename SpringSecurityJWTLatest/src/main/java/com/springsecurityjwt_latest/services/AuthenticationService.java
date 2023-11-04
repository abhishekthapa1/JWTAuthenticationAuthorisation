package com.springsecurityjwt_latest.services;


import com.springsecurityjwt_latest.dto.JwtAuthenticationResponse;
import com.springsecurityjwt_latest.dto.RefreshTokenRequest;
import com.springsecurityjwt_latest.dto.SignUpRequest;
import com.springsecurityjwt_latest.dto.SigninRequest;
import com.springsecurityjwt_latest.entities.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
