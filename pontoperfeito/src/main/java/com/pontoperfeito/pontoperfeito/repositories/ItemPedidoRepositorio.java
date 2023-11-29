package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.ItemPedido;
import com.pontoperfeito.pontoperfeito.model.ItemPedidoId;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemPedidoRepositorio {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemPedidoRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
