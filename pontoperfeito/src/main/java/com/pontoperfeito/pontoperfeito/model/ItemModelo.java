package com.pontoperfeito.pontoperfeito.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "itens")
@Getter
@Setter
public class ItemModelo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String nome;
  private Float valor;
  private String descricao;

  public ItemModelo() {
  };

  public ItemModelo(String nome, Float valor, String descricao) {
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
  };

  
  public List<ItemModelo> obterItens() {
    ArrayList<ItemModelo> itens = new ArrayList<>();

    ItemModelo b1 = new ItemModelo("Bainha", 17.0f, "Pendente");
    ItemModelo b2 = new ItemModelo("Barra", 25.0f, "Concluído");
    ItemModelo b3 = new ItemModelo("Botão", 30.0f, "Pendente");
    ItemModelo b4 = new ItemModelo("Ilhois", 15.0f, "Concluído");
    ItemModelo b5 = new ItemModelo("Linha", 22.0f, "Pendente");

    itens.add(b1);
    itens.add(b2);
    itens.add(b3);
    itens.add(b4);
    itens.add(b5);

    return itens;
  }
}