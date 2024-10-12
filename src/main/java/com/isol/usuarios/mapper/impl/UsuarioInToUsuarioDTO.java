package com.isol.usuarios.mapper.impl;

import com.isol.usuarios.entity.Usuario;
import com.isol.usuarios.mapper.IMapper;
import com.isol.usuarios.model.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInToUsuarioDTO implements IMapper<Usuario, UsuarioDTO> {
    @Override
    public UsuarioDTO map(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioDTO.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }
}
