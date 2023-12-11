package com.pontoperfeito.pontoperfeito.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.Item;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositorio {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void seedItens() {
    String query = "SELECT COUNT(*) FROM itens";
    int rowCount = jdbcTemplate.queryForObject(query, Integer.class);

    if (rowCount == 0) {

        String sql = "INSERT INTO itens (id, nome, valor, descricao) VALUES " +
                "(1, 'Bainha de calça', 15.00, 'Serviço de bainha em calças')," +
                "(2, 'Ajuste de manga', 20.00, 'Serviço de ajuste em mangas de camisas')," +
                "(3, 'Remendo em jeans', 25.00, 'Serviço de remendo em peças de jeans')," +
                "(4, 'Barra de saia', 18.00, 'Serviço de barra em saias')," +
                "(5, 'Ajuste de blazer', 30.00, 'Serviço de ajuste em blazers')," +
                "(6, 'Costura de botão', 10.00, 'Serviço de costura para reposição de botões')," +
                "(7, 'Customização de vestido', 40.00, 'Serviço de customização em vestidos')," +
                "(8, 'Reparo em zíper', 22.00, 'Serviço de reparo em zíper de bolsas')," +
                "(9, 'Ajuste em uniforme', 28.00, 'Serviço de ajuste em uniformes escolares')," +
                "(10, 'Costura de lençol', 12.00, 'Serviço de costura em lençóis')";
    
        jdbcTemplate.execute(sql);
        }
    }

    public List<Item> buscarItensPorNome(String nome) {
        String sql = "SELECT * FROM itens WHERE nome LIKE ?";
        String parametroLike = "%" + nome + "%";
        List<Item> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class), parametroLike);

    // Verifica se a lista de resultados está vazia e retorna um array vazio se for o caso
    return resultados.isEmpty() ? new ArrayList<>() : resultados;
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
