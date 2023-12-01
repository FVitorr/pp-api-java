package com.pontoperfeito.pontoperfeito.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String rua;
  private String bairro;
  private String numero_endereco;
  private String cidade;
  private String estado;
  private String telefone;
  private String email;

  public Cliente() {
  }

  public Cliente(int id) {
    this.id = (long) id;
}

  public Cliente(String nome, String rua, String bairro, String numero_endereco, String cidade, String estado, String telefone, String email) {
      this.nome = nome;
      this.rua = rua;
      this.bairro = bairro;
      this.numero_endereco = numero_endereco;
      this.cidade = cidade;
      this.estado = estado;
      this.telefone = telefone;
      this.email = email;
  }
}
