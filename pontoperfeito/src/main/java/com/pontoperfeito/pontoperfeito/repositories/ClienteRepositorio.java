package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pontoperfeito.pontoperfeito.model.Cliente;

import java.util.List;

@Repository
public class ClienteRepositorio {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClienteRepositorio(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
