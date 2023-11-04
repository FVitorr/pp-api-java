package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import java.util.Date;


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

}