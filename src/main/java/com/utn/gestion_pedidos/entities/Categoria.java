package com.utn.gestion_pedidos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria extends Base {

    @Column(nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @ToString.Exclude
    @OneToMany(mappedBy = "categoria")
    @Builder.Default
    private Set<Producto> productos = new HashSet<>();
}