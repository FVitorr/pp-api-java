package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.Cliente;
import com.pontoperfeito.pontoperfeito.model.Item;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepositorio {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClienteRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public List<Cliente> buscarClientesPorNome(String nome) {
        String sql = "SELECT * FROM clientes WHERE nome LIKE ?";
        String parametroLike = "%" + nome + "%";
        List<Cliente> resultados = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cliente.class), parametroLike);

    // Verifica se a lista de resultados está vazia e retorna um array vazio se for o caso
    return resultados.isEmpty() ? new ArrayList<>() : resultados;
}

public void seedClientes() {
    String query = "SELECT COUNT(*) FROM clientes";
    int rowCount = jdbcTemplate.queryForObject(query, Integer.class);

    if (rowCount == 0) {
    String sql = "INSERT INTO clientes (id, nome, rua, bairro, numero_endereco, cidade, estado, telefone, email) VALUES " +
            "(1, 'Cliente 1', 'Rua 1', 'Bairro 1', '123', 'Cidade 1', 'Estado 1', '1112223331', 'cliente1@email.com')," +
            "(2, 'Cliente 2', 'Rua 2', 'Bairro 2', '124', 'Cidade 2', 'Estado 2', '1112223332', 'cliente2@email.com')," +
            "(3, 'Cliente 3', 'Rua 3', 'Bairro 3', '125', 'Cidade 3', 'Estado 3', '1112223333', 'cliente3@email.com')," +
            "(4, 'Cliente 4', 'Rua 4', 'Bairro 4', '126', 'Cidade 4', 'Estado 4', '1112223334', 'cliente4@email.com')," +
            "(5, 'Cliente 5', 'Rua 5', 'Bairro 5', '127', 'Cidade 5', 'Estado 5', '1112223335', 'cliente5@email.com')," +
            "(6, 'Cliente 6', 'Rua 6', 'Bairro 6', '128', 'Cidade 6', 'Estado 6', '1112223336', 'cliente6@email.com')," +
            "(7, 'Cliente 7', 'Rua 7', 'Bairro 7', '129', 'Cidade 7', 'Estado 7', '1112223337', 'cliente7@email.com')," +
            "(8, 'Cliente 8', 'Rua 8', 'Bairro 8', '130', 'Cidade 8', 'Estado 8', '1112223338', 'cliente8@email.com')," +
            "(9, 'Cliente 9', 'Rua 9', 'Bairro 9', '131', 'Cidade 9', 'Estado 9', '1112223339', 'cliente9@email.com')," +
            "(10, 'Cliente 10', 'Rua 10', 'Bairro 10', '132', 'Cidade 10', 'Estado 10', '11122233310', 'cliente10@email.com')";

    jdbcTemplate.execute(sql);
    }
}

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cliente.class));
    }

    public void criarCliente(Cliente novoCliente) {
        jdbcTemplate.update("INSERT INTO clientes (nome, rua, bairro, numero_endereco, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                novoCliente.getNome(), novoCliente.getRua(), novoCliente.getBairro(), novoCliente.getNumero_endereco(),
                novoCliente.getCidade(), novoCliente.getEstado(), novoCliente.getTelefone(), novoCliente.getEmail());
    }

    public void atualizarCliente(Long id, Cliente clienteAtualizado) {
        jdbcTemplate.update("UPDATE clientes SET nome=?, rua=?, bairro=?, numero_endereco=?, cidade=?, estado=?, telefone=?, email=? WHERE id=?",
                clienteAtualizado.getNome(), clienteAtualizado.getRua(), clienteAtualizado.getBairro(), clienteAtualizado.getNumero_endereco(),
                clienteAtualizado.getCidade(), clienteAtualizado.getEstado(), clienteAtualizado.getTelefone(), clienteAtualizado.getEmail(), id);
    }

    public void excluirCliente(Long id) {
        jdbcTemplate.update("DELETE FROM clientes WHERE id=?", id);
    }

    public Cliente encontrarClientePorId(Long id) {
        String sql = "SELECT * FROM clientes WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cliente.class), id);
    }
}
