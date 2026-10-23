package com.utn.gestion_pedidos.entities;

import com.utn.gestion_pedidos.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BasePersistenciaTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void asignarFechaCreacionAutomaticamente() {
        Categoria categoria = Categoria.builder()
                .nombre("Prueba")
                .descripcion("Categoria de prueba")
                .eliminado(false)
                .build();

        Categoria guardada =
                categoriaRepository.saveAndFlush(categoria);

        assertNotNull(guardada.getCreatedAt());
    }
}
