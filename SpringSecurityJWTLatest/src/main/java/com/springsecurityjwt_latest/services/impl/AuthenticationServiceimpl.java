package com.springsecurityjwt_latest.services.impl;

import com.springsecurityjwt_latest.dto.JwtAuthenticationResponse;
import com.springsecurityjwt_latest.dto.RefreshTokenRequest;
import com.springsecurityjwt_latest.dto.SignUpRequest;
import com.springsecurityjwt_latest.dto.SigninRequest;
import com.springsecurityjwt_latest.entities.Role;
import com.springsecurityjwt_latest.entities.User;
import com.springsecurityjwt_latest.repository.UserRepository;
import com.springsecurityjwt_latest.services.AuthenticationService;
import com.springsecurityjwt_latest.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceimpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);

    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail()
                , signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail()).
                orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshToken);
        return jwtAuthenticationResponse;

    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
    User user = userRepository.findByEmail(userEmail).orElseThrow();
    if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshtoken(refreshTokenRequest.getToken());
        return jwtAuthenticationResponse;
    }
    return  null;
    }
}
