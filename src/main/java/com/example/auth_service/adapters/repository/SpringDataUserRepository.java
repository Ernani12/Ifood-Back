package com.example.auth_service.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.domain.model.User;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}