package org.example.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PedidoTest {
    public Produto produto;
    public Pedido pedido;

    @Before
    public void start(){
        produto = new Produto(7799.00, "iPhone 14 Pro Max");
        pedido = new Pedido(1, "Apple Iphone 14 pro max, 256Gb, Branco.", produto, 1);
    }

    @Test
    public void calcularValorFinalTest() throws Exception {
        pedido.calcularValorFinal();

        assertEquals(7799.00 ,pedido.getValorFinal(), 0.001);
    }

    @Test
    public void calcularValorFinalQuantidadeDoisTest() throws Exception {
        pedido.setQuantidade(2);
        pedido.calcularValorFinal();

        assertEquals(7799.00 * 2 ,pedido.getValorFinal(), 0.001);
    }

    @Test(expected = Exception.class)
    public void calcularValorFinalProdutoNull() throws Exception {
        pedido.setProduto(null);
        pedido.calcularValorFinal();
    }
}
