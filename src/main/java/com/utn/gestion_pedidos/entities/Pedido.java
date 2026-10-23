package com.utn.gestion_pedidos.entities;

import com.utn.gestion_pedidos.enums.Estado;
import com.utn.gestion_pedidos.enums.FormaPago;
import com.utn.gestion_pedidos.interfaces.Calculable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido extends Base implements Calculable {

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPago formaPago;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private Set<DetallePedido> detalles = new HashSet<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void addDetallePedido(DetallePedido detalle) {

        if (this.detalles == null) {
            this.detalles = new HashSet<>();
        }

        detalle.setPedido(this);
        detalle.calcularSubtotal();
        this.detalles.add(detalle);

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {

        if (this.detalles == null) {
            return null;
        }

        for (DetallePedido detalle : detalles) {

            if (detalle.getProducto() != null
                    && detalle.getProducto().equals(producto)) {

                return detalle;
            }
        }

        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {

        DetallePedido detalleEncontrado =
                findDetallePedidoByProducto(producto);

        if (detalleEncontrado != null) {

            this.detalles.remove(detalleEncontrado);
            detalleEncontrado.setPedido(null);

            calcularTotal();
        }
    }


    @Override
    public void calcularTotal() {
        if (this.detalles == null) {
            this.total = 0.0;
            return;
        }

        double suma = 0.0;

        for (DetallePedido detalle : this.detalles) {
            if (detalle.getSubtotal() != null) {
                suma += detalle.getSubtotal();
            }
        }

        this.total = suma;
    }
    
    public int contarItems() {

        return Optional.ofNullable(this.detalles)
                .map(detalles ->
                        detalles.stream()
                                .mapToInt(DetallePedido::getCantidad)
                                .sum()
                )
                .orElse(0);
    }
}