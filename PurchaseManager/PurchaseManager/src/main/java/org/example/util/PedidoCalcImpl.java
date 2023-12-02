package org.example.util;

import org.example.models.Pedido;

public class PedidoCalcImpl implements PedidoCalc{
    @Override
    public void aplicarDesconto(Pedido pedido, double percentualDesconto) {
        if (percentualDesconto < 0) throw new IllegalArgumentException("Percentual de desconto inválido");
        double desconto = pedido.getValorFinal() * (percentualDesconto/100);

        pedido.setValorFinal(pedido.getValorFinal() - desconto);
    }
}
