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


    @Test
    void calcularTotalConDetallesNulos() {
        Pedido pedido = Pedido.builder().build();

        pedido.setDetalles(null);

        pedido.calcularTotal();

        assertEquals(0.0, pedido.getTotal(), 0.001);
    }


    @Test
    void calcularTotalConVariosProductos() {
        Producto producto1 = Producto.builder()
                .nombre("Mouse")
                .precio(2500.0)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Teclado")
                .precio(4000.0)
                .build();

        DetallePedido detalle1 = DetallePedido.builder()
                .producto(producto1)
                .cantidad(2)
                .build();

        DetallePedido detalle2 = DetallePedido.builder()
                .producto(producto2)
                .cantidad(1)
                .build();

        Pedido pedido = Pedido.builder().build();

        pedido.addDetallePedido(detalle1);
        pedido.addDetallePedido(detalle2);

        assertEquals(9000.0, pedido.getTotal(), 0.001);
    }


}
