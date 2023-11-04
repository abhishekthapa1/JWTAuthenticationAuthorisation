package com.springsecurityjwt_latest.dto;

import lombok.Data;

@Data
public class SigninRequest {
    private String email;
    private String password;
}
