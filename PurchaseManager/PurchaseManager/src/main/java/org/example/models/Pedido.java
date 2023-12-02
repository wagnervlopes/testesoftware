package org.example.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pedido {
    private int numeroPedido;
    private String descricao;
    private Produto produto;
    private int quantidade;
    private double valorFinal;

    public Pedido(int numeroPedido, String descricao, Produto produto, int quantidade) {
        this.numeroPedido = numeroPedido;
        this.descricao = descricao;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void calcularValorFinal() throws Exception {
        if (produto == null) throw new Exception("Produto não informado");

        valorFinal = produto.getPreco() * quantidade;
    }
}
