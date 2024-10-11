package com.isol.usuarios.service;

import com.isol.usuarios.exceptions.ResponseNoContent;
import com.isol.usuarios.exceptions.ResponseNotFound;
import com.isol.usuarios.model.ResponseSave;
import com.isol.usuarios.model.UsuarioDTO;

import java.util.List;

public interface IUsuarioService {
    public List<UsuarioDTO> readAll() throws ResponseNoContent;

    public UsuarioDTO readById(Long id) throws ResponseNotFound;

    public ResponseSave insert(UsuarioDTO usuarioDTO);

    public ResponseSave update(Long id,UsuarioDTO usuarioDTO) throws ResponseNotFound;

    public ResponseSave deleteById(Long id) throws ResponseNotFound;

    public UsuarioDTO findByEmail(String email) throws ResponseNotFound;

}
