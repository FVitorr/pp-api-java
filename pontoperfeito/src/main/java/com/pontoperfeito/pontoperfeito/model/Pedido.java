package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

            // @JoinTable(
    //  name = "clientes", 
    //  joinColumns = @JoinColumn(name = "id_cliente"))

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @CreationTimestamp
    private Date data_pedido; 

    private Date estimativa_entrega;
    private Date data_entrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status_pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status_pagamento;

    @ManyToMany
    @JoinTable(
      name = "itens_pedido", 
      joinColumns = @JoinColumn(name = "id_pedido"), 
      inverseJoinColumns = @JoinColumn(name = "id_item"))
    private Set<Item> itens = new HashSet<>();

}