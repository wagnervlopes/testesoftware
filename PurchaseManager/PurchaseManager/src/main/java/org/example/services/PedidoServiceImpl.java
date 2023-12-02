package org.example.services;

import lombok.Getter;
import org.example.models.Pedido;
import org.example.models.Produto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoServiceImpl implements PedidoService {

    private List<Pedido> pedidos;

    public PedidoServiceImpl() {
        this.pedidos = new ArrayList<>();
    }

    @Override
    public Pedido cadastrarPedido(Produto produto, Pedido pedido) {
        if (existeNumeroPedido(pedido.getNumeroPedido())) {
            throw new RuntimeException("Número do pedido já existe. Escolha outro número.");
        }

        pedido.setProduto(produto);
        try {
            pedido.calcularValorFinal();
            pedidos.add(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    @Override
    public void removerPedido(Pedido pedido) {
        if (pedidos != null) {
            pedidos.remove(pedido);
        }
    }


    @Override
    public Pedido alterarPedido(Pedido pedido) {
        if (pedidos != null) {
            for (Pedido pedidoExistente : pedidos) {
                if (pedidoExistente.getNumeroPedido() == pedido.getNumeroPedido()) {
                    pedidoExistente.setDescricao(pedido.getDescricao());
                    pedidoExistente.setProduto(pedido.getProduto());
                    pedidoExistente.setQuantidade(pedido.getQuantidade());

                    return pedidoExistente; // Retorna o pedido alterado
                }
            }
        }
        return null; // Retorna null se o pedido não foi encontrado
    }

    @Override
    public List<Pedido> getTodosPedidos() {
        return pedidos;
    }


    private boolean existeNumeroPedido(int numeroPedido) {
        return pedidos.stream().anyMatch(p -> p.getNumeroPedido() == numeroPedido);
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
