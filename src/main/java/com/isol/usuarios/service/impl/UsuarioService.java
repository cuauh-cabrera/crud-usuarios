package com.isol.usuarios.service.impl;

import com.isol.usuarios.entity.Usuario;
import com.isol.usuarios.exceptions.ResponseNoContent;
import com.isol.usuarios.exceptions.ResponseNotFound;
import com.isol.usuarios.exceptions.ResponseServerError;
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

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDTO> readAll() throws ResponseNoContent {
        try {
            List<Usuario> usuarioList = usuarioRepository.findAll().stream().filter(usuario -> usuario.getIsActive()!= UsuarioConstantes.FILTER && usuario.getIsActive()!=null).toList();

            if (usuarioList.isEmpty()){
                log.error(UsuarioConstantes.NO_CONTENT_LOG);
                throw new ResponseNoContent(UsuarioConstantes.NO_CONTENT_MSG);
            }else {
                    return usuarioList.stream().map(usuario -> {
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setId(usuario.getId());
                    usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
                    usuarioDTO.setApellidoPaterno(usuario.getApellidoPaterno());
                    usuarioDTO.setApellidoMaterno(usuario.getApellidoMaterno());
                    usuarioDTO.setEmail(usuario.getEmail());
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

            if (usuarioOptional.isEmpty() || usuarioOptional.get().getIsActive()==null || usuarioOptional.get().getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(usuario.getId());
                usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
                usuarioDTO.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioDTO.setApellidoMaterno(usuario.getApellidoMaterno());
                usuarioDTO.setEmail(usuario.getEmail());
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
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
            usuario.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioDTO.getApellidoMaterno());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setFechaCreacion(LocalDate.now());
            usuario.setFechaModificacion(LocalDate.now());
            usuario.setIsActive(true);
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

            if (usuarioOptional.isEmpty() || usuarioOptional.get().getIsActive()==null || usuarioOptional.get().getIsActive()==UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario1 = new Usuario();
                usuario1.setId(usuarioDTO.getId());
                usuario1.setNombreUsuario(usuarioDTO.getNombreUsuario());
                usuario1.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
                usuario1.setApellidoMaterno(usuarioDTO.getApellidoMaterno());
                usuario1.setEmail(usuarioDTO.getEmail());
                usuario1.setFechaCreacion(usuario1.getFechaCreacion());
                usuario1.setFechaModificacion(LocalDate.now());
                usuario1.setIsActive(true);
                usuarioRepository.save(usuario1);
                ResponseSave responseSave = new ResponseSave();
                responseSave.setEmail(usuario1.getEmail());
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

            if (usuarioOptional.isEmpty() || usuarioOptional.get().getIsActive()==null || usuarioOptional.get().getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                LocalDate currDate = LocalDate.now();
                usuario.setIsActive(UsuarioConstantes.FILTER);
                usuario.setFechaModificacion(currDate);
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

            if (usuarioOptional.isEmpty() || usuarioOptional.get().getIsActive()==null || usuarioOptional.get().getIsActive() == UsuarioConstantes.FILTER){
                log.error(UsuarioConstantes.NOT_FOUND_LOG);
                throw new ResponseNotFound(UsuarioConstantes.NOT_FOUND_MSG);
            }else {
                Usuario usuario = usuarioOptional.get();
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(usuario.getId());
                usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
                usuarioDTO.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioDTO.setApellidoMaterno(usuario.getApellidoMaterno());
                usuarioDTO.setEmail(usuario.getEmail());
                log.info(UsuarioConstantes.SUCCESS_LOG);
                return usuarioDTO;
            }

        }catch (ResponseServerError e){
            log.error(UsuarioConstantes.SERVER_ERROR_LOG);
            throw new ResponseServerError(e.getMensaje());
        }
    }
}
