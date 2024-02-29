package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findAppUserByUsername(String username);
}
