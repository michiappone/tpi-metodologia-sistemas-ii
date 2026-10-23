package com.utn.gestion_pedidos.services;

import com.utn.gestion_pedidos.dtos.detallePedido.DetallePedidoCreate;
import com.utn.gestion_pedidos.entities.DetallePedido;
import com.utn.gestion_pedidos.entities.Pedido;
import com.utn.gestion_pedidos.entities.Producto;
import com.utn.gestion_pedidos.repositories.PedidoRepository;
import com.utn.gestion_pedidos.repositories.ProductoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DetallePedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public DetallePedidoService(
            PedidoRepository pedidoRepository,
            ProductoRepository productoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public DetallePedido agregarDetalle(
            Long pedidoId,
            DetallePedidoCreate dto
    ) {

        Pedido pedido = pedidoRepository
                .findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepository
                .findById(dto.productoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        DetallePedido detalle = DetallePedido.builder()
                .cantidad(dto.cantidad())
                .producto(producto)
                .subtotal(0.0)
                .eliminado(false)
                .build();

        pedido.addDetallePedido(detalle);

        pedidoRepository.save(pedido);

        return detalle;
    }
}