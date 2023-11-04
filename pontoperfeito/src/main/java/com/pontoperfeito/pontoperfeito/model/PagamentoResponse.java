package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.Getter;
import java.util.Date;

// @Entity
// @Table(name = "pagamentos")
@Getter
@Setter
public class PagamentoResponse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String pedido;
  private float valor; //FK
  private String status;
}