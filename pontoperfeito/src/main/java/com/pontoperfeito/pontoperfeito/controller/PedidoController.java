package com.pontoperfeito.pontoperfeito.controller;

import com.pontoperfeito.pontoperfeito.model.Cliente;
import com.pontoperfeito.pontoperfeito.model.Item;
import com.pontoperfeito.pontoperfeito.model.Pedido;
import com.pontoperfeito.pontoperfeito.model.PedidoParams;
import com.pontoperfeito.pontoperfeito.repositories.ClienteRepositorio;
import com.pontoperfeito.pontoperfeito.repositories.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    private final PedidoRepositorio pedidoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public PedidoController(PedidoRepositorio pedidoRepositorio, ClienteRepositorio clienteRepositorio) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.clienteRepositorio = clienteRepositorio;
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
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoParams pedido) {
        // Criar o pedido no banco de dados
        Cliente cliente = clienteRepositorio.encontrarClientePorId(pedido.getId_cliente());
        Pedido newPedido = new Pedido();
        newPedido.setCliente(cliente);
        // newPedido.setData_entrega(null);
        // newPedido.setData_pedido(null);
        newPedido.setObservacao(pedido.getObservacoes());
        newPedido.setEstimativa_entrega(pedido.getEstimativa_entrega());
        newPedido.setStatus_pagamento(pedido.getStatus_pagamento());
        newPedido.setStatus_pedido(pedido.getStatus_pedido());

        Set<Long> itensSet = new HashSet<Long>(pedido.getItens());

        Pedido r_Pedido = pedidoRepositorio.criarPedido(newPedido);

        pedidoRepositorio.associarItensAoPedido(r_Pedido.getId(), itensSet);

        // Retornar o pedido com os itens associados
        return ResponseEntity.ok(r_Pedido);
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> editarPedido(@PathVariable Long id, @RequestBody PedidoParams pedidoAtualizado) {

        Pedido pedidoExists = pedidoRepositorio.buscarPedidoPorId(id);

        Cliente clienteExists = pedidoExists.getCliente();

        Pedido newPedido = new Pedido();
        newPedido.setCliente(clienteExists);
        newPedido.setData_entrega(pedidoAtualizado.getData_entrega());
        newPedido.setObservacao(pedidoAtualizado.getObservacoes());
        // newPedido.setData_pedido(null);
        newPedido.setEstimativa_entrega(pedidoAtualizado.getEstimativa_entrega());
        newPedido.setStatus_pagamento(pedidoAtualizado.getStatus_pagamento());
        newPedido.setStatus_pedido(pedidoAtualizado.getStatus_pedido());

        Set<Long> itensSet = new HashSet<Long>(pedidoAtualizado.getItens());

        if (pedidoExists != null) {
            Pedido pedido = pedidoRepositorio.atualizarPedido(id, newPedido);

            pedidoRepositorio.atualizarItensDoPedido(id, itensSet);

            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoRepositorio.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedidos/filtrar")
public ResponseEntity<List<Pedido>> filtrarPedidos(
        @RequestParam(required = false) String nomeCliente,
        @RequestParam(required = false) String statusPagamento,
        @RequestParam(required = false) String statusPedido,
        @RequestParam(required = false) Date dataInicial,
        @RequestParam(required = false) Date dataFinal) {

        // Converte as strings de data para objetos java.sql.Date, se fornecidas
        // java.sql.Date dataInicialSql = (dataInicial != null && !dataInicial.isEmpty()) ? java.sql.Date.valueOf(dataInicial) : null;
        // java.sql.Date dataFinalSql = (dataFinal != null && !dataFinal.isEmpty()) ? java.sql.Date.valueOf(dataFinal) : null;

        // Chama a função do repositório com os parâmetros fornecidos
        List<Pedido> pedidosFiltrados = pedidoRepositorio.buscarPedidosPorFiltros(nomeCliente, statusPagamento, statusPedido, dataInicial, dataFinal);

        return ResponseEntity.ok(pedidosFiltrados);
}
}
