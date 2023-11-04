package com.springsecurityjwt_latest;

import com.springsecurityjwt_latest.entities.Role;
import com.springsecurityjwt_latest.entities.User;
import com.springsecurityjwt_latest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtLatestApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtLatestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User adminAccount = userRepository.findByRole(Role.ADMIN);
        if(null == adminAccount){
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setFirstname("admin");
        user.setLastname("admin");
        user.setRole(Role.ADMIN);
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        userRepository.save(user);
        }
    }
}
