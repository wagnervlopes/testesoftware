package org.example.sources;

import lombok.AllArgsConstructor;
import org.example.models.Pedido;
import org.example.models.Produto;
import org.example.services.PedidoService;

import java.util.List;

@AllArgsConstructor
public class PedidoSource {

    private final PedidoService pedidoService;

    public Pedido cadastrarPedido(Produto produto, Pedido pedido){
        return pedidoService.cadastrarPedido(produto, pedido);
    }

    public Pedido alterarPedido(Pedido pedido) {
        return pedidoService.alterarPedido(pedido);
    }

    public void removerPedido(Pedido pedido) {
        pedidoService.removerPedido(pedido);
    }

    public List<Pedido> obterTodosPedidos() {
        return pedidoService.getTodosPedidos();
    }
}
