package com.pontoperfeito.pontoperfeito.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pontoperfeito.pontoperfeito.repositories.AuthRepositorio;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthRepositorio authRepositorio;

    @Autowired
    public AuthController(AuthRepositorio authRepositorio) {
        this.authRepositorio = authRepositorio;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registrarUsuario(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String senha = requestBody.get("senha");
    
        authRepositorio.registrarUsuario(email, senha);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> autenticarUsuario(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String senha = requestBody.get("senha");
        boolean autenticado = authRepositorio.autenticarUsuario(email, senha);

        if (autenticado) {
        return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
