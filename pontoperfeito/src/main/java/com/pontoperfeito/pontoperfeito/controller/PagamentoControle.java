package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pontoperfeito.pontoperfeito.model.PagamentoResponse;

import java.util.List;

@RestController
public class PagamentoControle {
    @GetMapping("/pagamentos")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<PagamentoResponse>> rota() throws JsonProcessingException {
        PagamentoResponse controle = new PagamentoResponse();
        List<PagamentoResponse> pagamentos = controle.obterPagamentos();
        return ResponseEntity.ok(pagamentos);
    }
}
