package com.isol.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombreUsuario;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
}
