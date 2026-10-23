package com.utn.gestion_pedidos.dtos.producto;
import com.utn.gestion_pedidos.entities.Producto;

public record ProductoDto(
        Long id,
        String nombre,
        Double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,
        Long categoriaId
) {

    public ProductoDto(Producto producto) {
        this(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getImagen(),
                producto.isDisponible(),
                producto.getCategoria() != null
                        ? producto.getCategoria().getId()
                        : null
        );
    }
}