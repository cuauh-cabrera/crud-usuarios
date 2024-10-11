package com.isol.usuarios.exceptions;

import com.isol.usuarios.utils.UsuarioConstantes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResponseServerError extends RuntimeException{
    private String mensaje = UsuarioConstantes.SERVER_ERROR_MSG;
    private String Error = UsuarioConstantes.SERVER_ERROR_LOG;

    public ResponseServerError(){
    }

    public ResponseServerError(String Error){
        super(Error,null,true,false);
    }
}
