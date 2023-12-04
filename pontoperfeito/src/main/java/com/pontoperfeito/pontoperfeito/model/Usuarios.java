package com.pontoperfeito.pontoperfeito.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;

    public Usuarios() {
    }

    public Usuarios(String id) {
        this.id = Long.valueOf(id);
    }
    // Construtor
    public Usuarios(Long id,String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }
    
}
