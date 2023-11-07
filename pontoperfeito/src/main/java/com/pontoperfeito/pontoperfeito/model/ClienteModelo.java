package com.pontoperfeito.pontoperfeito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "clientes")
@Getter
@Setter
public class ClienteModelo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;
  private String nome;
  private String endereco;
  private String telefone;
  private String email;


  public ClienteModelo() {
  }

  public ClienteModelo(String nome, String endereco, String telefone, String email) {
      this.nome = nome;
      this.endereco = endereco;
      this.telefone = telefone;
      this.email = email;
  }
  
  public List<ClienteModelo> obterPagamentos() {
    ArrayList<ClienteModelo> clienteList = new ArrayList<>();

    ClienteModelo b1 = new ClienteModelo("Breno","Rua Test, 12,Formiga- MG","37717273","Breno@test.com");
    ClienteModelo b2 = new ClienteModelo("Joao","Rua Test, 13,Formiga- MG","37717273","Joao@test.com");
    ClienteModelo b3 = new ClienteModelo("Maria","Rua Test 14,Formiga- MG","37717273","Maria@test.com");
    ClienteModelo b4 = new ClienteModelo("Luana","Rua Test 15,Formiga- MG","37717273","Luana@test.com");
    ClienteModelo b5 = new ClienteModelo("Julia","Rua Test 16,Formiga- MG","37717273","Julia@test.com");

    clienteList.add(b1);
    clienteList.add(b2);
    clienteList.add(b3);
    clienteList.add(b4);
    clienteList.add(b5);

    return clienteList;
  }
}