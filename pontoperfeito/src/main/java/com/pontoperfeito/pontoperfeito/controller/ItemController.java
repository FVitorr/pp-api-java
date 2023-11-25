package com.pontoperfeito.pontoperfeito.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.pontoperfeito.pontoperfeito.model.Item;
import com.pontoperfeito.pontoperfeito.repositories.ItemRepositorio;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {
    private final ItemRepositorio itemRepositorio;

    @Autowired
    public ItemController(ItemRepositorio itemRepositorio) {
        this.itemRepositorio = itemRepositorio;
    }

    @GetMapping("/itens")
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemRepositorio.listarItens();
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/itens")
    public ResponseEntity<Item> criarItem(@RequestBody Item item) {
        itemRepositorio.criarItem(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/itens/{id}")
    public ResponseEntity<Item> editarItem(@PathVariable Long id, @RequestBody Item itemAtualizado) {
        Item item = itemRepositorio.atualizarItem(id, itemAtualizado);

        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/itens/{id}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long id) {
        itemRepositorio.excluirItem(id);
        return ResponseEntity.noContent().build();
    }
}
