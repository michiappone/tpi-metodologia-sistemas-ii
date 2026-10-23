package com.utn.gestion_pedidos.entities;

import com.utn.gestion_pedidos.enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "usuarios")
public class Usuario extends Base {

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 30)
    private String celular;

    @ToString.Exclude
    @Column(name = "contrasena", nullable = false, length = 100)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();

    public void addPedido(Pedido pedido) {

        if (this.pedidos == null) {
            this.pedidos = new HashSet<>();
        }

        pedido.setUsuario(this);
        this.pedidos.add(pedido);
    }
}