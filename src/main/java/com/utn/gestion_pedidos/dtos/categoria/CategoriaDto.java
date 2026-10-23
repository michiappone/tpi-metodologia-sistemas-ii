package com.utn.gestion_pedidos.dtos.categoria;

import com.utn.gestion_pedidos.entities.Categoria;

public record CategoriaDto(
        Long id,
        String nombre,
        String descripcion
) {

    public CategoriaDto(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}