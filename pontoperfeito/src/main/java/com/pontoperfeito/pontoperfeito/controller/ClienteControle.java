package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pontoperfeito.pontoperfeito.model.PagamentoModelo;

@RestController
public class ClienteControle {
    @GetMapping("/testes")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> rota() throws JsonProcessingException {
        PagamentoModelo a = new PagamentoModelo();
        ObjectMapper objectMapper = new ObjectMapper();
        a.setId(1L);
        a.setPedido_id(1151515L);
        a.setPaid_at(new Date());
        a.setValor_total(50);
        a.setSituacao(1);
        String pagamentoJson = objectMapper.writeValueAsString(a);
        return ResponseEntity.ok(pagamentoJson);
    }
}
