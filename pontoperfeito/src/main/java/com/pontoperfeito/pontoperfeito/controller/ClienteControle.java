package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pontoperfeito.pontoperfeito.model.PagamentoResponse;

@RestController
public class ClienteControle {
    @GetMapping("/pagamentos")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> rota() throws JsonProcessingException {
        ArrayList<PagamentoResponse> pagamentosList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        
        PagamentoResponse b1 = new PagamentoResponse();
        b1.setPedido("Breno");
        b1.setStatus("Pendente");
        b1.setValor(17);

        PagamentoResponse b2 = new PagamentoResponse();
        b2.setPedido("Alice");
        b2.setStatus("Concluído");
        b2.setValor(25);

        PagamentoResponse b3 = new PagamentoResponse();
        b3.setPedido("Carlos");
        b3.setStatus("Pendente");
        b3.setValor(30);

        PagamentoResponse b4 = new PagamentoResponse();
        b4.setPedido("Diana");
        b4.setStatus("Concluído");
        b4.setValor(15);

        PagamentoResponse b5 = new PagamentoResponse();
        b5.setPedido("Eduardo");
        b5.setStatus("Pendente");
        b5.setValor(22);

        pagamentosList.add(b1);
        pagamentosList.add(b2);
        pagamentosList.add(b3);
        pagamentosList.add(b4);
        pagamentosList.add(b5);

        String pagamentos = objectMapper.writeValueAsString(pagamentosList);
        return ResponseEntity.ok(pagamentos);
    }
}
