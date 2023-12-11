package com.pontoperfeito.pontoperfeito.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.ItemPedido;


import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemPedidoRepositorio {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemPedidoRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void seedItensPedido() {
        String query = "SELECT COUNT(*) FROM itens_pedido";
        int rowCount = jdbcTemplate.queryForObject(query, Integer.class);
    
        if (rowCount == 0) {
        String sql = "INSERT INTO itens_pedido (id, id_pedido, id_item, quantidade) VALUES " +
                "(1, 1, 1, 1)," +
                "(2, 1, 2, 1)," +
                "(3, 2, 3, 1)," +
                "(4, 2, 4, 1)," +
                "(5, 3, 5, 1)," +
                "(6, 4, 6, 1)," +
                "(7, 5, 7, 1)," +
                "(8, 6, 8, 1)," +
                "(9, 9, 9, 1)," +
                "(10, 8, 10, 1),"+
                "(11, 9, 7, 1),"+
                "(12, 10, 10, 1)";
        jdbcTemplate.execute(sql);
        }
    }

    public List<ItemPedido> listarItensPedido() {
        String sql = "SELECT * FROM itens_pedido";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItemPedido.class));
    }

    public ItemPedido buscarItemPedidoPorId(ItemPedido id) {
        String sql = "SELECT * FROM itens_pedido WHERE id_pedido = ? AND id_item = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ItemPedido.class), id.getPedido(), id.getItem());
    }

    public void criarItemPedido(ItemPedido itemPedido) {
        String sql = "INSERT INTO itens_pedido (id_pedido, id_item, quantidade) VALUES (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                itemPedido.getPedido().getId(),
                itemPedido.getItem().getId(),
                itemPedido.getQuantidade()
        );
    }

    public void atualizarItemPedido(ItemPedido itemPedido) {
        String sql = "UPDATE itens_pedido SET quantidade = ? WHERE id_pedido = ? AND id_item = ?";
        jdbcTemplate.update(
                sql,
                itemPedido.getQuantidade(),
                itemPedido.getPedido().getId(),
                itemPedido.getItem().getId()
        );
    }

    public void excluirItemPedido(ItemPedido id) {
        String sql = "DELETE FROM itens_pedido WHERE id_pedido = ? AND id_item = ?";
        jdbcTemplate.update(sql, id.getPedido(), id.getItem());
    }
}
