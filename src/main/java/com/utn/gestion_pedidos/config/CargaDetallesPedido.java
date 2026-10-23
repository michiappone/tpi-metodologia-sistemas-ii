package com.utn.gestion_pedidos.config;

import com.utn.gestion_pedidos.dtos.detallePedido.DetallePedidoCreate;

public record CargaDetallesPedido(
        Long pedidoId,
        DetallePedidoCreate primerDetalle,
        DetallePedidoCreate segundoDetalle
) {
}
