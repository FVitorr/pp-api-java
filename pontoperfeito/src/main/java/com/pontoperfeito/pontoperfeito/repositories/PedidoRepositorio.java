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

import java.util.ArrayList;
import java.util.List;

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

        // Verifica se a lista de resultados está vazia e retorna um array vazio se for o caso
        return resultados.isEmpty() ? new ArrayList<>() : resultados;
    }

public List<Pedido> listarPedidos() {
    String sql = "SELECT pedidos.id AS id_pedido, " +
    "GROUP_CONCAT(CONCAT(itens.id, ' - ', itens.valor, ' - ', itens.nome) "+ 
    "ORDER BY itens.id) AS itens_do_pedido, " +
    "clientes.* FROM pedidos " +
    "INNER JOIN clientes ON clientes.id = pedidos.id_cliente " +
    "INNER JOIN itens_pedido ON itens_pedido.id_pedido = pedidos.id " +
    "INNER JOIN itens ON itens.id = itens_pedido.id_item " +
    "GROUP BY pedidos.id";

    // SELECT pedidos.*, clientes.* ,itens_pedido.* , itens.* FROM pedidos 
    // INNER JOIN clientes ON clientes.id = pedidos.id_cliente 
    // INNER JOIN itens_pedido ON itens_pedido.id_pedido = pedidos.id
    // INNER JOIN itens ON itens.id = itens_pedido.id_item

    return jdbcTemplate.query(sql, (resultSet, rowNum) -> {
        Pedido pedido = new Pedido();
        pedido.setId(resultSet.getLong("id"));
        //pedido.setData_pedido(resultSet.getDate("data_pedido"));
        pedido.setEstimativa_entrega(resultSet.getDate("estimativa_entrega"));
        pedido.setData_entrega(resultSet.getDate("data_entrega"));
        pedido.setStatus_pedido(StatusPedido.valueOf(resultSet.getString("status_pedido")));
        pedido.setStatus_pagamento(StatusPagamento.valueOf(resultSet.getString("status_pagamento")));

        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong("id_cliente"));
        cliente.setNome(resultSet.getString("nome"));
        cliente.setRua(resultSet.getString("rua"));
        cliente.setBairro(resultSet.getString("bairro"));
        cliente.setNumero_endereco(resultSet.getString("numero_endereco"));
        cliente.setCidade(resultSet.getString("cidade"));
        cliente.setEstado(resultSet.getString("estado"));
        cliente.setTelefone(resultSet.getString("telefone"));
        cliente.setEmail(resultSet.getString("email"));

        pedido.setCliente(cliente);

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
                pedido.getStatus_pagamento()
        );
        // Agora você precisa obter o ID gerado para associar aos itens e realizar o mesmo para a tabela itens_pedido
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
                pedido.getId()
        );
        // Aqui você também precisará atualizar os itens na tabela itens_pedido conforme necessário
    }

    public void excluirPedido(Long id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        // Além disso, você pode precisar excluir os registros correspondentes na tabela itens_pedido
    }
}
