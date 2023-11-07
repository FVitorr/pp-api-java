package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.pontoperfeito.pontoperfeito.model.ItemModelo;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ItemControle {
    @GetMapping("/itens")
    public ResponseEntity<List<ItemModelo>> rota() throws JsonProcessingException {
        ItemModelo controle = new ItemModelo();
        List<ItemModelo> itens = controle.obterItens();
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/itens")
    public ResponseEntity<ItemModelo> criarCliente(@RequestBody ItemModelo cliente ) {
        ItemModelo novoItem = new ItemModelo(cliente.getNome(), cliente.getValor() ,cliente.getDescricao());
        //JOGAR PRO BANCO DE DADOS
        return ResponseEntity.ok(novoItem);
    }

}
