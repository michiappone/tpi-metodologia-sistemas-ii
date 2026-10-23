package com.utn.gestion_pedidos.dtos.usuario;

import com.utn.gestion_pedidos.entities.Usuario;
import com.utn.gestion_pedidos.enums.Rol;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String email,
        String celular,
        Rol rol
) {

    public UsuarioDto(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getRol()
        );
    }
}