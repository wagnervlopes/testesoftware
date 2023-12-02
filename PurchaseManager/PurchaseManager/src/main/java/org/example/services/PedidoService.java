package org.example.services;

import org.example.models.Pedido;
import org.example.models.Produto;

import java.util.List;

public interface PedidoService {
    Pedido cadastrarPedido(Produto produto, Pedido pedido);
    void removerPedido(Pedido pedido);
    Pedido alterarPedido(Pedido pedido);

    List<Pedido> getTodosPedidos();
}
