package com.pontoperfeito.pontoperfeito.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pontoperfeito.pontoperfeito.model.PagamentoModelo;

@RestController
public class ClienteControle {

    // public class Produto {
    //     public int id;
    //     public String nome;
    //     public double preco;

    //     // Getters e Setters
    // }

    // Produto ok = new Produto();
    // ok.id = 1;
    // ok.nome = 'Breno';
    // ok.preco = 14.90;


    @GetMapping("/testes")
    @CrossOrigin(origins = "http://localhost:5173")
    public String rota(){
        PagamentoModelo a = new PagamentoModelo();
        ObjectMapper objectMapper = new ObjectMapper();
        a.setId(1l);
        a.setPedido_id(1151515l);
        a.setPaid_at(new Date());
        a.setValor_total(50);
        a.setSituacao(1);
        String pagamentoJson = objectMapper.writeValueAsString(a);
        // return ResponseEntity.ok(pagamentoJson);
            return pagamentoJson;

      }
}