package com.example.auth_service.adapters.repository;

import org.springframework.stereotype.Repository;
import com.example.auth_service.domain.model.User;
import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.example.auth_service.domain.repository.UserRepositoryPort;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository repository;

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
