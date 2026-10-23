package com.utn.gestion_pedidos.dtos.producto;

public record ProductoCreate(
        String nombre,
        Double precio,
        String descripcion,
        int stock,
        String imagen,
        boolean disponible,
        Long categoriaId
) {
}