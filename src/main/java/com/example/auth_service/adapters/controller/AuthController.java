    // AuthController.java
    package com.example.auth_service.adapters.controller;

    import lombok.RequiredArgsConstructor;
    import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
    import org.springframework.beans.factory.annotation.Autowired;
    import com.example.auth_service.application.AuthService;
    import com.example.auth_service.domain.model.User;



    @RestController
    @RequestMapping("/auth")
    @RequiredArgsConstructor
    public class AuthController {

        @Autowired
        private AuthService authService;

       

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody User user) {
            // Apenas delega para o service
            return authService.login(user.getUsername(), user.getPassword());
        }

        @GetMapping("/test")
        public String test() {
            return "Auth funcionando!";
        }


        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody User user) {
        try {
            authService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao registrar usuário: " + e.getMessage());
        }
    }
    }