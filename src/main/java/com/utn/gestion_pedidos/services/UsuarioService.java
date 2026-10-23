package com.utn.gestion_pedidos.services;

import com.utn.gestion_pedidos.dtos.usuario.UsuarioCreate;
import com.utn.gestion_pedidos.entities.Usuario;
import com.utn.gestion_pedidos.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crear(UsuarioCreate dto) {

        Usuario usuario = Usuario.builder()
                .nombre(dto.nombre())
                .apellido(dto.apellido())
                .email(dto.email())
                .celular(dto.celular())
                .contraseña(dto.contraseña())
                .rol(dto.rol())
                .eliminado(false)
                .createdAt(LocalDateTime.now())
                .build();

        return usuarioRepository.save(usuario);
    }
}