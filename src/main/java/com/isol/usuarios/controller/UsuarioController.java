package com.isol.usuarios.controller;

import com.isol.usuarios.exceptions.ResponseNoContent;
import com.isol.usuarios.exceptions.ResponseNotFound;
import com.isol.usuarios.model.ResponseSave;
import com.isol.usuarios.model.UsuarioDTO;
import com.isol.usuarios.service.impl.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios")
    public List<UsuarioDTO> readAll() throws ResponseNoContent {
        return usuarioService.readAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/{id}")
    public UsuarioDTO readById(@PathVariable Long id) throws ResponseNotFound {
        return usuarioService.readById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/usuarios")
    public ResponseSave insert(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.insert(usuarioDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/usuarios/{id}")
    public ResponseSave update(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) throws ResponseNotFound {
        usuarioDTO.setId(id);
     return usuarioService.update(id,usuarioDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/usuarios/{id}")
    public ResponseSave deleteById(@PathVariable("id") Long id) throws ResponseNotFound {
        return usuarioService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/email")
    public UsuarioDTO findByEmail(@RequestParam("email") String email) throws ResponseNotFound {
        return usuarioService.findByEmail(email);
    }


}
