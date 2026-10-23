package com.utn.gestion_pedidos.dtos.usuario;

import com.utn.gestion_pedidos.enums.Rol;

public record UsuarioCreate(
        String nombre,
        String apellido,
        String email,
        String celular,
        String contraseña,
        Rol rol
) {
}