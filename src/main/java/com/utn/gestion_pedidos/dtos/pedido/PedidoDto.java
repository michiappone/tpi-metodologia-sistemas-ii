package com.utn.gestion_pedidos.dtos.pedido;

import com.utn.gestion_pedidos.dtos.detallePedido.DetallePedidoDto;
import com.utn.gestion_pedidos.entities.Pedido;
import com.utn.gestion_pedidos.enums.Estado;
import com.utn.gestion_pedidos.enums.FormaPago;

import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId,
        List<DetallePedidoDto> detalles
) {

    public PedidoDto(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                pedido.getUsuario() != null
                        ? pedido.getUsuario().getId()
                        : null,
                pedido.getDetalles() != null
                        ? pedido.getDetalles()
                          .stream()
                          .map(DetallePedidoDto::new)
                          .toList()
                        : List.of()
        );
    }
}