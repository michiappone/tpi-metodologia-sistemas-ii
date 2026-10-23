package com.utn.gestion_pedidos.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoTest {

    @Test
    void calcularTotalPedidoVacio() {
        Pedido pedido = Pedido.builder().build();

        pedido.calcularTotal();

        assertEquals(0.0, pedido.getTotal(), 0.001);
    }

    @Test
    void calcularTotalConProducto() {
        Producto producto = Producto.builder()
                .nombre("Teclado")
                .precio(1500.0)
                .build();

        DetallePedido detalle = DetallePedido.builder()
                .producto(producto)
                .cantidad(2)
                .build();

        Pedido pedido = Pedido.builder().build();
        pedido.addDetallePedido(detalle);

        assertEquals(3000.0, pedido.getTotal(), 0.001);
    }
}
