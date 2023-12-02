package org.example.services;

import org.example.models.Pedido;
import org.example.models.Produto;
import org.example.util.PedidoCalcImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PedidoServiceTest {
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
    public void testCadastrarPedidoSize() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        pedidoService.cadastrarPedido(produto, pedido);
        assertEquals(1, pedidoService.getPedidos().size());
    }

    @Test
    public void testCadastrorPedido(){
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido resultado = pedidoService.cadastrarPedido(produto, pedido);
        assertEquals(resultado, pedidoService.getPedidos().get(0));
    }

    @Test(expected = RuntimeException.class)
    public void testCadastroPedidoJaExistente(){
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        pedidoService.cadastrarPedido(produto, pedido);
        pedidoService.cadastrarPedido(produto, pedido);
    }

    @Test
    public void testCadastrarPedidoNumeroExistente() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido pedidoExistente = pedidoService.cadastrarPedido(produto, pedido);

        try {
            pedidoService.cadastrarPedido(produto, pedidoExistente);
        } catch (RuntimeException e) {
            assertEquals("Número do pedido já existe. Escolha outro número.", e.getMessage());
        }
    }

    @Test
    public void testRemoverPedidoExistente() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido pedidoRemover = pedidoService.cadastrarPedido(produto, pedido);

        // Verificar que o pedido foi adicionado corretamente antes de remover
        assertEquals(1, pedidoService.getPedidos().size());

        // Remover o pedido
        pedidoService.removerPedido(pedidoRemover);

        // Verificar que o pedido foi removido com sucesso
        assertEquals(0, pedidoService.getPedidos().size());
    }

    @Test
    public void testRemoverPedidoInexistente() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido pedidoExistente = pedidoService.cadastrarPedido(produto, pedido);

        // Criar um pedido que não foi adicionado
        Pedido pedidoInexistente = new Pedido(2, "Outro Pedido", produto, 1);

        // Verificar que o pedido existente está na lista antes de remover
        assertEquals(1, pedidoService.getPedidos().size());

        // Tentar remover um pedido que não foi adicionado (não deve causar alterações)
        pedidoService.removerPedido(pedidoInexistente);

        // Verificar que o pedido existente ainda está na lista
        assertEquals(1, pedidoService.getPedidos().size());
    }

    @Test
    public void testRemoverPedidoListaVazia() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();

        // Verificar que a lista está vazia antes de tentar remover
        assertEquals(0, pedidoService.getPedidos().size());

        // Tentar remover de uma lista vazia (não deve causar alterações)
        pedidoService.removerPedido(pedido);

        // Verificar que a lista ainda está vazia
        assertEquals(0, pedidoService.getPedidos().size());
    }

    @Test
    public void testAlterarPedidoExistente() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido pedidoOriginal = pedidoService.cadastrarPedido(produto, pedido);

        // Criar um pedido com as alterações desejadas
        Pedido pedidoAlterado = new Pedido(2, "Nova Descrição", produto, 2);

        // Chamar o método a ser testado
        Pedido resultado = pedidoService.alterarPedido(pedidoAlterado);

        // Verificar se o pedido original foi substituído na lista
        assertNull(resultado);
    }

    @Test
    public void testAlterarPedidoInexistente() {
        PedidoServiceImpl pedidoService = new PedidoServiceImpl();
        Pedido pedidoOriginal = pedidoService.cadastrarPedido(produto, pedido);

        // Criar um pedido com o mesmo número, mas com outras informações
        Pedido pedidoAlterado = new Pedido(pedidoOriginal.getNumeroPedido(), "Nova Descrição", produto, 2);

        // Chamar o método a ser testado
        Pedido resultado = pedidoService.alterarPedido(pedidoAlterado);

        // Verificar se o pedido original não foi alterado na lista
        assertEquals(pedidoOriginal, pedidoService.getPedidos().get(0));
    }

}
