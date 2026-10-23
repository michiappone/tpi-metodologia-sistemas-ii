package com.utn.gestion_pedidos.config;

import com.utn.gestion_pedidos.dtos.categoria.CategoriaCreate;
import com.utn.gestion_pedidos.dtos.detallePedido.DetallePedidoCreate;
import com.utn.gestion_pedidos.dtos.pedido.PedidoEdit;
import com.utn.gestion_pedidos.dtos.producto.ProductoCreate;
import com.utn.gestion_pedidos.dtos.usuario.UsuarioCreate;

import com.utn.gestion_pedidos.entities.Categoria;
import com.utn.gestion_pedidos.entities.Pedido;
import com.utn.gestion_pedidos.entities.Producto;
import com.utn.gestion_pedidos.entities.Usuario;

import com.utn.gestion_pedidos.enums.Estado;
import com.utn.gestion_pedidos.enums.FormaPago;
import com.utn.gestion_pedidos.enums.Rol;

import com.utn.gestion_pedidos.services.CategoriaService;
import com.utn.gestion_pedidos.services.DetallePedidoService;
import com.utn.gestion_pedidos.services.PedidoService;
import com.utn.gestion_pedidos.services.ProductoService;
import com.utn.gestion_pedidos.services.UsuarioService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final DetallePedidoService detallePedidoService;

    public DataLoader(
            CategoriaService categoriaService,
            ProductoService productoService,
            UsuarioService usuarioService,
            PedidoService pedidoService,
            DetallePedidoService detallePedidoService
    ) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
        this.detallePedidoService = detallePedidoService;
    }

    @Override
    public void run(String... args) {

        // =========================
        // 3 CATEGORIAS
        // =========================

        Categoria tecnologia = categoriaService.crear(
                new CategoriaCreate(
                        "Tecnologia",
                        "Productos tecnologicos"
                )
        );

        Categoria hogar = categoriaService.crear(
                new CategoriaCreate(
                        "Hogar",
                        "Productos para el hogar"
                )
        );

        Categoria indumentaria = categoriaService.crear(
                new CategoriaCreate(
                        "Indumentaria",
                        "Ropa y accesorios"
                )
        );


        // =========================
        // 10 PRODUCTOS
        // =========================

        Producto p1 = productoService.crear(
                new ProductoCreate(
                        "Notebook",
                        900000.0,
                        "Notebook para uso general",
                        10,
                        "notebook.jpg",
                        true,
                        tecnologia.getId()
                )
        );

        Producto p2 = productoService.crear(
                new ProductoCreate(
                        "Mouse",
                        25000.0,
                        "Mouse inalambrico",
                        30,
                        "mouse.jpg",
                        true,
                        tecnologia.getId()
                )
        );

        Producto p3 = productoService.crear(
                new ProductoCreate(
                        "Teclado",
                        45000.0,
                        "Teclado mecanico",
                        20,
                        "teclado.jpg",
                        true,
                        tecnologia.getId()
                )
        );

        Producto p4 = productoService.crear(
                new ProductoCreate(
                        "Monitor",
                        250000.0,
                        "Monitor 24 pulgadas",
                        15,
                        "monitor.jpg",
                        true,
                        tecnologia.getId()
                )
        );

        Producto p5 = productoService.crear(
                new ProductoCreate(
                        "Lampara",
                        18000.0,
                        "Lampara de escritorio",
                        25,
                        "lampara.jpg",
                        true,
                        hogar.getId()
                )
        );

        Producto p6 = productoService.crear(
                new ProductoCreate(
                        "Silla",
                        120000.0,
                        "Silla de escritorio",
                        12,
                        "silla.jpg",
                        true,
                        hogar.getId()
                )
        );

        Producto p7 = productoService.crear(
                new ProductoCreate(
                        "Mesa",
                        180000.0,
                        "Mesa para comedor",
                        8,
                        "mesa.jpg",
                        true,
                        hogar.getId()
                )
        );

        Producto p8 = productoService.crear(
                new ProductoCreate(
                        "Remera",
                        30000.0,
                        "Remera basica",
                        50,
                        "remera.jpg",
                        true,
                        indumentaria.getId()
                )
        );

        Producto p9 = productoService.crear(
                new ProductoCreate(
                        "Buzo",
                        60000.0,
                        "Buzo de algodon",
                        30,
                        "buzo.jpg",
                        true,
                        indumentaria.getId()
                )
        );

        Producto p10 = productoService.crear(
                new ProductoCreate(
                        "Pantalon",
                        55000.0,
                        "Pantalon deportivo",
                        25,
                        "pantalon.jpg",
                        true,
                        indumentaria.getId()
                )
        );


        // =========================
        // 2 USUARIOS
        // =========================

        Usuario usuario1 = usuarioService.crear(
                new UsuarioCreate(
                        "Juan",
                        "Perez",
                        "juan@gmail.com",
                        "3511111111",
                        "1234",
                        Rol.USUARIO
                )
        );

        Usuario usuario2 = usuarioService.crear(
                new UsuarioCreate(
                        "Ana",
                        "Gomez",
                        "ana@gmail.com",
                        "3512222222",
                        "5678",
                        Rol.ADMIN
                )
        );


        // =========================
        // 3 PEDIDOS
        // =========================

        Pedido pedido1 = pedidoService.crear(
                new PedidoEdit(
                        LocalDate.now(),
                        Estado.PENDIENTE,
                        FormaPago.TARJETA,
                        usuario1.getId()
                )
        );

        Pedido pedido2 = pedidoService.crear(
                new PedidoEdit(
                        LocalDate.now(),
                        Estado.CONFIRMADO,
                        FormaPago.TRANSFERENCIA,
                        usuario1.getId()
                )
        );

        Pedido pedido3 = pedidoService.crear(
                new PedidoEdit(
                        LocalDate.now(),
                        Estado.PENDIENTE,
                        FormaPago.EFECTIVO,
                        usuario2.getId()
                )
        );


        // =========================
        // DETALLES DE LOS PEDIDOS
        // mínimo 2 por pedido
        // =========================

        detallePedidoService.agregarDetalle(
                pedido1.getId(),
                new DetallePedidoCreate(1, p1.getId())
        );

        detallePedidoService.agregarDetalle(
                pedido1.getId(),
                new DetallePedidoCreate(2, p2.getId())
        );


        detallePedidoService.agregarDetalle(
                pedido2.getId(),
                new DetallePedidoCreate(1, p4.getId())
        );

        detallePedidoService.agregarDetalle(
                pedido2.getId(),
                new DetallePedidoCreate(2, p5.getId())
        );


        detallePedidoService.agregarDetalle(
                pedido3.getId(),
                new DetallePedidoCreate(2, p8.getId())
        );

        detallePedidoService.agregarDetalle(
                pedido3.getId(),
                new DetallePedidoCreate(1, p9.getId())
        );

        System.out.println("=================================");
        System.out.println("DATOS CARGADOS CORRECTAMENTE");
        System.out.println("=================================");
    }
}