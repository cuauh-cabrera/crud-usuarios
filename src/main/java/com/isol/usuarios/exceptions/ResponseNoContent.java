package com.isol.usuarios.exceptions;

import com.isol.usuarios.utils.UsuarioConstantes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResponseNoContent extends Exception{
    private String mensaje = UsuarioConstantes.NO_CONTENT_MSG;
    private String Error = UsuarioConstantes.NO_CONTENT_LOG;

    public ResponseNoContent(){
    }

    public  ResponseNoContent(String Error){
        super(Error,null,true,false);
    }
}
