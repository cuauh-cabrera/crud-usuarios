package com.isol.usuarios.exceptions;

import com.isol.usuarios.utils.UsuarioConstantes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseNotFound extends Exception{
    private String mensaje = UsuarioConstantes.NOT_FOUND_MSG;
    private String Error = UsuarioConstantes.NOT_FOUND_LOG;

    public ResponseNotFound(){
    }

    public ResponseNotFound(String Error){
        super(Error,null,true,false);
    }
}
