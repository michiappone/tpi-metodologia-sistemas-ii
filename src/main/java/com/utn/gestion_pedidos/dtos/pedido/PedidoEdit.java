package com.utn.gestion_pedidos.dtos.pedido;

import com.utn.gestion_pedidos.enums.Estado;
import com.utn.gestion_pedidos.enums.FormaPago;

import java.time.LocalDate;

public record PedidoEdit(
        LocalDate fecha,
        Estado estado,
        FormaPago formaPago,
        Long usuarioId
) {
}