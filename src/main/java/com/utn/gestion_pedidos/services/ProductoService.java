package com.utn.gestion_pedidos.services;

import com.utn.gestion_pedidos.dtos.producto.ProductoCreate;
import com.utn.gestion_pedidos.entities.Categoria;
import com.utn.gestion_pedidos.entities.Producto;
import com.utn.gestion_pedidos.repositories.CategoriaRepository;
import com.utn.gestion_pedidos.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto crear(ProductoCreate dto) {

        Categoria categoria = categoriaRepository
                .findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        Producto producto = Producto.builder()
                .nombre(dto.nombre())
                .precio(dto.precio())
                .descripcion(dto.descripcion())
                .stock(dto.stock())
                .imagen(dto.imagen())
                .disponible(dto.disponible())
                .categoria(categoria)
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .build();

        return productoRepository.save(producto);
    }
}