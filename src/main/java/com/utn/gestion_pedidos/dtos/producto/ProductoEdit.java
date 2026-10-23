package com.utn.gestion_pedidos.dtos.producto;

public record ProductoEdit(
        String nombre,
        Double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,
        Long categoriaId
) {
}