package com.springsecurityjwt_latest.repository;

import com.springsecurityjwt_latest.entities.Role;
import com.springsecurityjwt_latest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
