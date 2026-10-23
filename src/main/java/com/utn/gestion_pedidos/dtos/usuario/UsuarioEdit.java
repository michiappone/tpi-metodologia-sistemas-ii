package com.utn.gestion_pedidos.dtos.usuario;

import com.utn.gestion_pedidos.enums.Rol;

public record UsuarioEdit(
        String nombre,
        String apellido,
        String email,
        String celular,
        Rol rol
) {
}