package org.example.util;

import org.example.models.Pedido;
import org.example.models.Produto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PedidoCalcImplTest {

    public Produto produto;
    public Pedido pedido;
    public PedidoCalcImpl pedidoCalc;

    @Before
    public void start(){
        pedidoCalc = new PedidoCalcImpl();
        produto = new Produto(7799.00, "iPhone 14 Pro Max");
        pedido = new Pedido(1, "Apple Iphone 14 pro max, 256Gb, Branco.", produto, 1);
    }

    @Test
    public void aplicarDescontoTest() throws Exception {
        pedido.calcularValorFinal();

        pedidoCalc.aplicarDesconto(pedido, 20.00);

        assertEquals(6239.20, pedido.getValorFinal(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAplicarDescontoComPercentualNegativo() throws Exception {
        pedido.calcularValorFinal();
        double percentualDescontoNegativo = -10.0;

        pedidoCalc.aplicarDesconto(pedido, percentualDescontoNegativo);
    }

    @Test
    public void testAplicarDescontoComPercentualMaximo() throws Exception {
        pedido.calcularValorFinal();
        pedidoCalc.aplicarDesconto(pedido, 100.0);
        assertEquals(0.0, pedido.getValorFinal(), 0.001);
    }

    @Test
    public void testAplicarDescontoComPercentualZero() throws Exception {
        pedido.calcularValorFinal();
        pedidoCalc.aplicarDesconto(pedido, 0.0);
        assertEquals(7799.0, pedido.getValorFinal(), 0.001);
    }
}
