package com.example.auth_service.domain.repository;

import java.util.Optional;
import com.example.auth_service.domain.model.User;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);
    User save(User user);
}
