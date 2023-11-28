package com.pontoperfeito.pontoperfeito.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;  // Assuming you have a 'Cliente' class

    private Float valor;

    @CreationTimestamp
    private Date dataPedido;  // Use creation timestamp for order date

    private Date estimativaEntrega;
    private Date dataEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @ManyToMany
    @JoinTable(
      name = "pedido_item", 
      joinColumns = @JoinColumn(name = "pedido_id"), 
      inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> itens = new HashSet<>();
}
