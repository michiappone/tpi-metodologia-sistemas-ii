package com.utn.gestion_pedidos.dtos.detallePedido;

import com.utn.gestion_pedidos.entities.DetallePedido;

public record DetallePedidoDto(
        Long id,
        int cantidad,
        Double subtotal,
        Long productoId
) {

    public DetallePedidoDto(DetallePedido detallePedido) {
        this(
                detallePedido.getId(),
                detallePedido.getCantidad(),
                detallePedido.getSubtotal(),
                detallePedido.getProducto() != null
                        ? detallePedido.getProducto().getId()
                        : null
        );
    }
}