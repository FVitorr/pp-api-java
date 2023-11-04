package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import java.util.Date;

// @Entity
// @Table(name = "pagamentos")
@Getter
@Setter

public class PagamentoModelo {
  // @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long pedido_id; //FK
  private Date paid_at;
  private float valor_total;
  private int situacao; // 1 - pago -- 2 - pendente -- 3 - concluido

}