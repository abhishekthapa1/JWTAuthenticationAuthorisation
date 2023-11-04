package com.springsecurityjwt_latest.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
   UserDetailsService userDetailsService();
}
