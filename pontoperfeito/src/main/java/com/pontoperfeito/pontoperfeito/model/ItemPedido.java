package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens_pedido")
@IdClass(ItemPedidoId.class)
@Getter
@Setter
public class ItemPedido {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    private int quantidade;
}
