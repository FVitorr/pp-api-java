package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.Pedido;
import com.pontoperfeito.pontoperfeito.model.StatusPagamento;
import com.pontoperfeito.pontoperfeito.model.StatusPedido;
import com.pontoperfeito.pontoperfeito.model.Cliente;
import com.pontoperfeito.pontoperfeito.model.Item;
import com.pontoperfeito.pontoperfeito.model.ItemPedido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PedidoRepositorio {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PedidoRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pedido> buscarClientesPorNome(String nome) {
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        String parametroLike = "%" + nome + "%";
        List<Pedido> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pedido.class), parametroLike);

        // Verifica se a lista de resultados está vazia e retorna um array vazio se for
        // o caso
        return resultados.isEmpty() ? new ArrayList<>() : resultados;
    }

    public List<Pedido> listarPedidos() {
        String sql = "SELECT pedidos.*, " +
                "GROUP_CONCAT(CONCAT(itens.id, ' - ', itens.valor, ' - ', itens.nome) ORDER BY itens.id) AS itens_do_pedido, "
                +
                "clientes.* " +
                "FROM pedidos " +
                "INNER JOIN clientes ON clientes.id = pedidos.id_cliente " +
                "LEFT JOIN itens_pedido ON itens_pedido.id_pedido = pedidos.id " +
                "LEFT JOIN itens ON itens.id = itens_pedido.id_item " +
                "GROUP BY pedidos.id;";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
            Long pedidoId = resultSet.getLong("id");

            Pedido pedido = new Pedido();
            pedido.setId(pedidoId);
            pedido.setEstimativa_entrega(resultSet.getDate("estimativa_entrega"));
            pedido.setData_entrega(resultSet.getDate("data_entrega"));
            pedido.setStatus_pedido(StatusPedido.valueOfm (resultSet.getString("status_pedido")));
            pedido.setStatus_pagamento(StatusPagamento.valueOf(resultSet.getString("status_pagamento")));

            Cliente cliente = new Cliente();
            cliente.setNome(resultSet.getString("nome"));

            pedido.setCliente(cliente);

            String itensConcatenados = resultSet.getString("itens_do_pedido");
            if (itensConcatenados != null) {
                Set<Item> itens = Arrays.stream(itensConcatenados.split(","))
                        .map(itemString -> {
                            String[] itemInfo = itemString.split(" - ");
                            if (itemInfo.length >= 3) {
                                return new Item(itemInfo[2], Float.parseFloat(itemInfo[1]), itemInfo[0]);
                            } else {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull) // Filtra itens nulos, se houver
                        .collect(Collectors.toSet());
    
                pedido.setItens(itens);
            }

            return pedido;
        });
    }

    public Pedido buscarPedidoPorId(Long id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Pedido.class), id);
    }

    public void criarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (id_cliente, data_pedido, estimativa_entrega, data_entrega, status_pedido, status_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                pedido.getCliente().getId(),
                pedido.getData_pedido(),
                pedido.getEstimativa_entrega(),
                pedido.getData_entrega(),
                pedido.getStatus_pedido(),
                pedido.getStatus_pagamento());
        // Agora você precisa obter o ID gerado para associar aos itens e realizar o
        // mesmo para a tabela itens_pedido
    }

    public void atualizarPedido(Pedido pedido) {
        String sql = "UPDATE pedidos SET id_cliente = ?, data_pedido = ?, estimativa_entrega = ?, data_entrega = ?, status_pedido = ?, status_pagamento = ? WHERE id = ?";
        jdbcTemplate.update(
                sql,
                pedido.getCliente().getId(),
                pedido.getData_pedido(),
                pedido.getEstimativa_entrega(),
                pedido.getData_entrega(),
                pedido.getStatus_pedido(),
                pedido.getStatus_pagamento(),
                pedido.getId());
        // Aqui você também precisará atualizar os itens na tabela itens_pedido conforme
        // necessário
    }

    public void excluirPedido(Long id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        // Além disso, você pode precisar excluir os registros correspondentes na tabela
        // itens_pedido
    }
}
