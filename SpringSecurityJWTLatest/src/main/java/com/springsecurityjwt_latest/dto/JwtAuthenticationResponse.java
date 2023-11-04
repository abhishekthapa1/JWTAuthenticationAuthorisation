package com.springsecurityjwt_latest.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshtoken;
}
