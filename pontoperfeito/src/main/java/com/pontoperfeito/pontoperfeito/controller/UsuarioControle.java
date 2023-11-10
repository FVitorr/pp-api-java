package com.pontoperfeito.pontoperfeito.controller;
import com.pontoperfeito.pontoperfeito.model.UsuarioModelo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioControle {
    @PostMapping("/auth")
    public ResponseEntity<UsuarioModelo> criarCliente(@RequestBody UsuarioModelo cliente ) {
        UsuarioModelo novoItem = new UsuarioModelo(cliente.getEmail(),cliente.getPassword());
        //JOGAR PRO BANCO DE DADOS
        return ResponseEntity.ok(novoItem);
    }

}
