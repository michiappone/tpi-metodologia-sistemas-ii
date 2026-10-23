package com.utn.gestion_pedidos.services;

import com.utn.gestion_pedidos.dtos.pedido.PedidoEdit;
import com.utn.gestion_pedidos.entities.Pedido;
import com.utn.gestion_pedidos.entities.Usuario;
import com.utn.gestion_pedidos.repositories.PedidoRepository;
import com.utn.gestion_pedidos.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Pedido crear(PedidoEdit dto) {

        Usuario usuario = usuarioRepository
                .findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = Pedido.builder()
                .fecha(dto.fecha())
                .estado(dto.estado())
                .formaPago(dto.formaPago())
                .total(0.0)
                .usuario(usuario)
                .eliminado(false)
                .build();

        return pedidoRepository.save(pedido);
    }
}