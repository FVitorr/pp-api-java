package com.pontoperfeito.pontoperfeito.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Initializer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void initialize() {
        ClienteRepositorio clienteRepositorio = applicationContext.getBean(ClienteRepositorio.class);
        clienteRepositorio.seedClientes();
        ItemRepositorio itemRepositorio = applicationContext.getBean(ItemRepositorio.class);
        itemRepositorio.seedItens();
        PedidoRepositorio pedidoRepositorio = applicationContext.getBean(PedidoRepositorio.class);
        pedidoRepositorio.seedPedidos();
        ItemPedidoRepositorio itemPedidoRepositorio = applicationContext.getBean(ItemPedidoRepositorio.class);
        itemPedidoRepositorio.seedItensPedido();

    }
}
