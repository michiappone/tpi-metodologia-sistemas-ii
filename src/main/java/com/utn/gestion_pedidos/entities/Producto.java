package com.utn.gestion_pedidos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos")
public class Producto extends Base {

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private int stock;

    @Column(length = 255)
    private String imagen;

    @Column(nullable = false)
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @ToString.Exclude
    private Categoria categoria;
}