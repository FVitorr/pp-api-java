package com.pontoperfeito.pontoperfeito.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.Item;

import java.util.List;

@Repository
public class ItemRepositorio {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> listarItens() {
        String sql = "SELECT * FROM itens";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class));
    }

    public void criarItem(Item novoItem) {

        jdbcTemplate.update("INSERT INTO itens (nome, valor, descricao) VALUES (?, ?, ?)",
                novoItem.getNome(), novoItem.getValor(), novoItem.getDescricao());
    }

    public Item atualizarItem(Long id, Item itemAtualizado) {
        jdbcTemplate.update("UPDATE itens SET nome=?, valor=?, descricao=? WHERE id=?",
                itemAtualizado.getNome(), itemAtualizado.getValor(), itemAtualizado.getDescricao(), id);
        return encontrarItemPorId(id);
    }

    public void excluirItem(Long id) {
        jdbcTemplate.update("DELETE FROM itens WHERE id=?", id);
    }

    public Item encontrarItemPorId(Long id) {
        String sql = "SELECT * FROM itens WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Item.class), id);
    }
}
