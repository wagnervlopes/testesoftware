package org.example.util;

import org.example.models.Pedido;

public interface PedidoCalc {
    void aplicarDesconto(Pedido pedido, double percentualDesconto);
}
