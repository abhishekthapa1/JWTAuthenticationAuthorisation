package com.springsecurityjwt_latest.services.impl;

import com.springsecurityjwt_latest.entities.User;
import com.springsecurityjwt_latest.repository.UserRepository;
import com.springsecurityjwt_latest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService(){
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException("User is not found"));
            }
        };
    }
}
