package com.isol.usuarios.controller;

import com.isol.usuarios.exceptions.ResponseNoContent;
import com.isol.usuarios.exceptions.ResponseNotFound;
import com.isol.usuarios.model.ResponseSave;
import com.isol.usuarios.model.UsuarioDTO;
import com.isol.usuarios.service.impl.UsuarioService;
import com.isol.usuarios.utils.UsuarioConstantes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = UsuarioConstantes.DOC_READ_ALL_SUM, description = UsuarioConstantes.DOC_READ_ALL_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = UsuarioConstantes.SUCCESS_MSG),
            @ApiResponse(responseCode = "404",description = UsuarioConstantes.NO_CONTENT_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios")
    public List<UsuarioDTO> readAll() throws ResponseNoContent {
        return usuarioService.readAll();
    }


    @Operation(summary =UsuarioConstantes.DOC_READ_BY_ID_SUM, description = UsuarioConstantes.DOC_READ_BY_ID_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = UsuarioConstantes.SUCCESS_MSG),
            @ApiResponse(responseCode = "404",description = UsuarioConstantes.NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/{id}")
    public UsuarioDTO readById(@PathVariable Long id) throws ResponseNotFound {
        return usuarioService.readById(id);
    }


    @Operation(summary = UsuarioConstantes.DOC_INSERT_SUM, description = UsuarioConstantes.DOC_INSERT_DEC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = UsuarioConstantes.CREATED_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/usuarios")
    public ResponseSave insert(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.insert(usuarioDTO);
    }


    @Operation(summary = UsuarioConstantes.DOC_UPDATE_SUM, description = UsuarioConstantes.DOC_UPDATE_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = UsuarioConstantes.CREATED_MSG),
            @ApiResponse(responseCode = "404",description = UsuarioConstantes.NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/usuarios/{id}")
    public ResponseSave update(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) throws ResponseNotFound {
        usuarioDTO.setId(id);
     return usuarioService.update(id,usuarioDTO);
    }


    @Operation(summary = UsuarioConstantes.DOC_DELETE_SUM, description = UsuarioConstantes.DOC_DELETE_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = UsuarioConstantes.DELETED_MSG),
            @ApiResponse(responseCode = "404",description = UsuarioConstantes.NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/usuarios/{id}")
    public ResponseSave deleteById(@PathVariable("id") Long id) throws ResponseNotFound {
        return usuarioService.deleteById(id);
    }


    @Operation(summary = UsuarioConstantes.DOC_FIND_BY_EMAIL_SUM, description = UsuarioConstantes.DOC_FIND_BY_EMAIL_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = UsuarioConstantes.SUCCESS_MSG),
            @ApiResponse(responseCode = "404",description = UsuarioConstantes.NOT_FOUND_MSG),
            @ApiResponse(responseCode = "500",description = UsuarioConstantes.SERVER_ERROR_MSG)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/email")
    public UsuarioDTO findByEmail(@RequestParam("email") String email) throws ResponseNotFound {
        return usuarioService.findByEmail(email);
    }


}
