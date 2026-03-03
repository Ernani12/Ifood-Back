package com.example.auth_service.application;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.auth_service.domain.model.User;
import com.example.auth_service.domain.repository.UserRepositoryPort;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;


     public ResponseEntity<String> login(String username, String password) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (auth.isAuthenticated()) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário ou senha inválidos!");
            }

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuário ou senha inválidos!");
        }
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
