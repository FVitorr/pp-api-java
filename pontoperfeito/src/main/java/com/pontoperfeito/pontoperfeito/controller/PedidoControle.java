package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pontoperfeito.pontoperfeito.model.PedidoModelo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoControle {
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoModelo>> rota() throws JsonProcessingException {
        PedidoModelo controle = new PedidoModelo();
        List<PedidoModelo> itens = controle.obterItens();
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoModelo> criarCliente(@RequestBody PedidoModelo cliente ) {
        PedidoModelo novoItem = new PedidoModelo();
        //JOGAR PRO BANCO DE DADOS
        return ResponseEntity.ok(novoItem);
    }

}
