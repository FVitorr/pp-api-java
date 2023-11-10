package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class PedidoModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private Long cliente_id;
  private Long item_id;
  private String observacao;
  private Date previsao_entrega;
  private float valor_total;
  private int situacao; // 1 - pronto -- 2 - pendente

  public PedidoModelo() {
  };

  // Construtor completo
  public PedidoModelo(Long clienteId, Long itemId, String observacao, Date previsaoEntrega, float valorTotal,
      int situacao) {
    this.cliente_id = clienteId;
    this.item_id = itemId;
    this.observacao = observacao;
    this.previsao_entrega = previsaoEntrega;
    this.valor_total = valorTotal;
    this.situacao = situacao;
  }

  public List<PedidoModelo> obterItens() {
    ArrayList<PedidoModelo> itens = new ArrayList<>();

    PedidoModelo b1 = new PedidoModelo(1L, 101L, 201L, "Observações do pedido", new Date(), 155f, 1);
    PedidoModelo b2 = new PedidoModelo(1L, 101L, 201L, "Observações do pedido", new Date(), 155f, 1);
    PedidoModelo b3 = new PedidoModelo(1L, 101L, 201L, "Observações do pedido", new Date(), 155f, 1);
    PedidoModelo b4 = new PedidoModelo(1L, 101L, 201L, "Observações do pedido", new Date(), 155f, 1);
    PedidoModelo b5 = new PedidoModelo(1L, 101L, 201L, "Observações do pedido", new Date(), 155f, 1);

    itens.add(b1);
    itens.add(b2);
    itens.add(b3);
    itens.add(b4);
    itens.add(b5);

    return itens;
  }
}