package com.springsecurityjwt_latest.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String token;
}
