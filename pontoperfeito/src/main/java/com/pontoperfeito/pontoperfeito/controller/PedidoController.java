package com.pontoperfeito.pontoperfeito.controller;

import com.pontoperfeito.pontoperfeito.model.Pedido;
import com.pontoperfeito.pontoperfeito.repositories.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    private final PedidoRepositorio pedidoRepositorio;

    @Autowired
    public PedidoController(PedidoRepositorio pedidoRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
    }

    @GetMapping("/pedidos/busca/{nome}")
    public ResponseEntity<List<Pedido>> buscarClientesPorNome(@PathVariable String nome) {
        List<Pedido> pedidos = pedidoRepositorio.buscarClientesPorNome(nome);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoRepositorio.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        Pedido pedido = pedidoRepositorio.buscarPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        pedidoRepositorio.criarPedido(pedido);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> editarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepositorio.buscarPedidoPorId(id);

        if (pedido != null) {
            pedidoRepositorio.atualizarPedido(pedidoAtualizado);
            return ResponseEntity.ok(pedidoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoRepositorio.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
