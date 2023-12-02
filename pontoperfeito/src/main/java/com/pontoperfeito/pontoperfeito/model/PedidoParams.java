package com.pontoperfeito.pontoperfeito.model;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
public class PedidoParams {

    private long id_cliente;
    private ArrayList<Long> itens;

    @CreationTimestamp
    private Date data_pedido; 

    private Date estimativa_entrega;

    private Date data_entrega;
    private String observacoes;

    @Enumerated(EnumType.STRING)
    private StatusPedido status_pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status_pagamento;
}
