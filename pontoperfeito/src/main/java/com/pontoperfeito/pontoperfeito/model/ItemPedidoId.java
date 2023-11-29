package com.pontoperfeito.pontoperfeito.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ItemPedidoId implements Serializable {

    private Long pedido;
    private Long item;

}
