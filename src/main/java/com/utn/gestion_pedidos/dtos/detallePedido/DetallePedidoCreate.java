package com.utn.gestion_pedidos.dtos.detallePedido;

public record DetallePedidoCreate(
        int cantidad,
        Long productoId
) {
}