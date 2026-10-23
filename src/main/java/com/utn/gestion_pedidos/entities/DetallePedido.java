package com.utn.gestion_pedidos.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "detalles_pedido")
public class DetallePedido extends Base {

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private Double subtotal;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public void calcularSubtotal() {
        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = producto.getPrecio() * cantidad;
        } else {
            this.subtotal = 0.0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DetallePedido otro)) {
            return false;
        }

        return getId() != null
                && getId().equals(otro.getId());
    }

    @Override
    public int hashCode() {
        return DetallePedido.class.hashCode();
    }

}