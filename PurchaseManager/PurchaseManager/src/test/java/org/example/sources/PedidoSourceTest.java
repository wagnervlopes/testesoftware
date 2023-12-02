package org.example.sources;

import org.example.models.Pedido;
import org.example.models.Produto;
import org.example.services.PedidoService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PedidoSourceTest {

    private PedidoService pedidoService;
    private PedidoSource pedidoSource;

    @Before
    public void setUp() {
        pedidoService = mock(PedidoService.class);
        pedidoSource = new PedidoSource(pedidoService);
    }

    @Test
    public void testCadastrarPedido() {
        Produto produto = new Produto(50.0, "Produto A");
        Pedido pedido = new Pedido(1, "Pedido 1", produto, 1);

        when(pedidoService.cadastrarPedido(produto, pedido)).thenReturn(pedido);

        Pedido resultado = pedidoSource.cadastrarPedido(produto, pedido);

        assertEquals(pedido, resultado);
        Mockito.verify(pedidoService, times(1)).cadastrarPedido(produto, pedido);
    }

    @Test
    public void testAlterarPedido() {
        Produto produto = new Produto(50.0, "Produto A");
        Pedido pedido = new Pedido(1, "Pedido 1", produto, 1);

        when(pedidoService.alterarPedido(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoSource.alterarPedido(pedido);

        assertEquals(pedido, resultado);
        Mockito.verify(pedidoService, times(1)).alterarPedido(pedido);
    }

    @Test
    public void testRemoverPedido() {
        Produto produto = new Produto(50.0, "Produto A");
        Pedido pedido = new Pedido(1, "Pedido 1", produto, 1);

        pedidoSource.removerPedido(pedido);

        Mockito.verify(pedidoService, times(1)).removerPedido(pedido);
    }

    @Test
    public void testObterTodosPedidos() {
        Produto produto = new Produto(50.0, "Produto A");
        List<Pedido> pedidos = Arrays.asList(
            new Pedido(1, "Pedido 1", produto, 1),
            new Pedido(2, "Pedido 2", produto, 1)
        );

        when(pedidoService.getTodosPedidos()).thenReturn(pedidos);

        List<Pedido> resultado = pedidoSource.obterTodosPedidos();

        assertEquals(pedidos, resultado);
        Mockito.verify(pedidoService, times(1)).getTodosPedidos();
    }
}
