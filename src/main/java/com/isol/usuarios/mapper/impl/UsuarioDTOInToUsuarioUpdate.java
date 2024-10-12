package com.isol.usuarios.mapper.impl;

import com.isol.usuarios.entity.Usuario;
import com.isol.usuarios.mapper.IMapper;
import com.isol.usuarios.model.UsuarioDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class UsuarioDTOInToUsuarioUpdate implements IMapper<UsuarioDTO, Usuario> {
    @Override
    public Usuario map(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioDTO.getApellidoMaterno());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setFechaCreacion(usuario.getFechaCreacion());
        usuario.setFechaModificacion(LocalDate.now());
        usuario.setIsActive(true);
        return usuario;
    }
}
