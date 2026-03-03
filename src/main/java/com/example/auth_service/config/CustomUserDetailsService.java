package com.example.auth_service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.example.auth_service.domain.repository.UserRepositoryPort;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    // 🔥 COLE AQUI O HASH GERADO UMA ÚNICA VEZ
    private static final String ADMIN_PASSWORD =
            "$2a$10$CjCY5rDEKSmE/EnWSkp7kOmIPnqol31ns6HSR6rGhkxPpCZmL4Xhi";

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 🔥 ADMIN FIXO
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password(ADMIN_PASSWORD)
                    .roles("USER")
                    .build();
        }

        // 🔥 USUÁRIO DO BANCO
        var user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // já deve estar criptografado
                .roles("USER")
                .build();
    }
}