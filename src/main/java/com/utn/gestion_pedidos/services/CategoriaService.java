package com.utn.gestion_pedidos.services;

import com.utn.gestion_pedidos.dtos.categoria.CategoriaCreate;
import com.utn.gestion_pedidos.entities.Categoria;
import com.utn.gestion_pedidos.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria crear(CategoriaCreate dto) {

        Categoria categoria = Categoria.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .eliminado(false)
                .build();

        return categoriaRepository.save(categoria);
    }
}