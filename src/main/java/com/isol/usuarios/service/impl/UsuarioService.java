package com.isol.usuarios.service.impl;

import com.isol.usuarios.entity.Usuario;
import com.isol.usuarios.exceptions.ResponseNoContent;
import com.isol.usuarios.exceptions.ResponseNotFound;
import com.isol.usuarios.exceptions.ResponseServerError;
import com.isol.usuarios.mapper.impl.UsuarioDTOInToUsuario;
import com.isol.usuarios.mapper.impl.UsuarioDTOInToUsuarioUpdate;
import com.isol.usuarios.mapper.impl.UsuarioInToUsuarioDTO;
import com.isol.usuarios.model.ResponseSave;
import com.isol.usuarios.model.UsuarioDTO;
import com.isol.usuarios.repository.UsuarioRepository;
import com.isol.usuarios.service.IUsuarioService;
import com.isol.usuarios.utils.UsuarioConstantes;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioDTOInToUsuario mapperSave;
    private final UsuarioInToUsuarioDTO mapperRead;
    private final UsuarioDTOInToUsuarioUpdate mapperUpdate;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioDTOInToUsuario mapperSave,
                          UsuarioInToUsuarioDTO mapperRead,
                          UsuarioDTOInToUsuarioUpdate mapperUpdate) {
        this.usuarioRepository = usuarioRepository;
        this.mapperSave = mapperSave;
        this.mapperRead = mapperRead;
        this.mapperUpdate = mapperUpdate;
    }

    @Override
    public List<UsuarioDTO> readAll() throws ResponseNoContent {
        try {
            List<Usuario> usuarioList = usuarioRepository.findAll()
                    .stream()
                    .filter(usuario ->
                            usuario.getIsActive()!= UsuarioConstantes.FILTER && usuario.getIsActive()!=null)
                    .toList();

            if (usuarioList.isEmpty()){
                log.error(UsuarioConstantes.NO_CONTENT_LOG);
                throw new ResponseNoContent(UsuarioConstantes.NO_CONTENT_MSG);
            }else {
                return usuarioList.stream().map(usuario -> {
                    UsuarioDTO usuarioDTO = mapperRead.map(usuario);
                    log.info(UsuarioConstantes.SUCCESS_LOG);
                    return usuarioDTO;
                }).toList();
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }

    @Override
    public UsuarioDTO readById(Long id) throws ResponseNotFound {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (usuarioOptional.isEmpty() || usuarioOptional.get()
                    .getIsActive()==null || usuarioOptional.get()
                    .getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                UsuarioDTO usuarioDTO = mapperRead.map(usuario);
                log.info(UsuarioConstantes.SUCCESS_LOG);
                return usuarioDTO;
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }

    @Override
    @Transactional
    public ResponseSave insert(UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = mapperSave.map(usuarioDTO);
            log.info(UsuarioConstantes.SUCCESS_LOG);
            usuarioRepository.save(usuario);
            ResponseSave responseSave  = new ResponseSave();
            responseSave.setEmail(usuario.getEmail());
            responseSave.setMensaje(UsuarioConstantes.CREATED_MSG);
            return responseSave;

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }

    @Override
    @Transactional
    public ResponseSave update(Long id, UsuarioDTO usuarioDTO) throws ResponseNotFound {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (usuarioOptional.isEmpty() || usuarioOptional.get()
                    .getIsActive()==null || usuarioOptional.get()
                    .getIsActive()==UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = mapperUpdate.map(usuarioDTO);
                log.info(UsuarioConstantes.SUCCESS_LOG);
                usuarioRepository.save(usuario);
                ResponseSave responseSave = new ResponseSave();
                responseSave.setEmail(usuario.getEmail());
                responseSave.setMensaje(UsuarioConstantes.UPDATED_MSG);
                return responseSave;
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }

    @Override
    @Transactional
    public ResponseSave deleteById(Long id) throws ResponseNotFound {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (usuarioOptional.isEmpty() || usuarioOptional.get()
                    .getIsActive()==null || usuarioOptional.get()
                    .getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                usuario.setIsActive(UsuarioConstantes.FILTER);
                usuario.setFechaModificacion(LocalDate.now());
                usuarioRepository.save(usuario);
                ResponseSave responseSave = new ResponseSave();
                responseSave.setEmail(usuario.getEmail());
                responseSave.setMensaje(UsuarioConstantes.DELETED_MSG);
                log.info(UsuarioConstantes.SUCCESS_LOG);
                return responseSave;
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }

    @Override
    public UsuarioDTO findByEmail(String email) throws ResponseNotFound {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmailAndIsActiveTrue(email);

            if (usuarioOptional.isEmpty() || usuarioOptional.get()
                    .getIsActive()==null || usuarioOptional.get()
                    .getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                UsuarioDTO usuarioDTO = mapperRead.map(usuario);
                log.info(UsuarioConstantes.SUCCESS_LOG);
                return usuarioDTO;
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }
}
