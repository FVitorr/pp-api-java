package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.pontoperfeito.pontoperfeito.model.ClienteModelo;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteControle {
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModelo>> rota() throws JsonProcessingException {
        ClienteModelo controle = new ClienteModelo();
        List<ClienteModelo> clientes = controle.obterPagamentos();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteModelo> criarCliente(@RequestBody ClienteModelo cliente ) {
        ClienteModelo novoCliente = new ClienteModelo(cliente.getNome(), cliente.getEndereco() ,cliente.getTelefone() ,cliente.getEmail());
        //JOGAR PRO BANCO DE DADOS
        return ResponseEntity.ok(novoCliente);
    }

}
