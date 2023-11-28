package com.pontoperfeito.pontoperfeito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.pontoperfeito.pontoperfeito.model.Cliente;
import com.pontoperfeito.pontoperfeito.model.Item;
import com.pontoperfeito.pontoperfeito.repositories.ClienteRepositorio;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteController(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

        @GetMapping("/clientes/busca/{nome}")
        public ResponseEntity<List<Cliente>> buscarClientesPorNome(@PathVariable String nome) {
        List<Cliente> clientes = clienteRepositorio.buscarClientesPorNome(nome);

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        clienteRepositorio.criarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        clienteRepositorio.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteRepositorio.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
