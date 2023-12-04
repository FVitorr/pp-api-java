package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthRepositorio {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void registrarUsuario(String email, String senha) {
        jdbcTemplate.update("INSERT INTO usuarios (email, senha) VALUES (?, ?)", email, senha);
    }

    public boolean autenticarUsuario(String email, String senha) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND senha = ?";
        
        // Correção na chamada do método queryForObject
        int count = jdbcTemplate.queryForObject(sql, Integer.class, email, senha);
    
        return count > 0;
    }
    
}
