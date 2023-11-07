package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PagamentoResponse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String pedido;
  private float valor; //FK
  private String status;

  public PagamentoResponse(String pedido, float valor, String status) {
    this.pedido = pedido;
    this.valor = valor;
    this.status = status;
  }

  public PagamentoResponse() {}

  public List<PagamentoResponse> obterPagamentos() {
    ArrayList<PagamentoResponse> pagamentosList = new ArrayList<>();

    PagamentoResponse b1 = new PagamentoResponse("Breno", 17.0f, "Pendente");
    PagamentoResponse b2 = new PagamentoResponse("Alice", 25.0f, "Concluído");
    PagamentoResponse b3 = new PagamentoResponse("Carlos", 30.0f, "Pendente");
    PagamentoResponse b4 = new PagamentoResponse("Diana", 15.0f, "Concluído");
    PagamentoResponse b5 = new PagamentoResponse("Eduardo", 22.0f, "Pendente");

    pagamentosList.add(b1);
    pagamentosList.add(b2);
    pagamentosList.add(b3);
    pagamentosList.add(b4);
    pagamentosList.add(b5);

    return pagamentosList;
  }
}
