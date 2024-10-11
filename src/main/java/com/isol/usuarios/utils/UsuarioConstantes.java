package com.isol.usuarios.utils;

public class UsuarioConstantes {
    public static final Boolean FILTER = false;
    public static final String SERVER_ERROR_LOG = "Error: Revisar la conexion a la base de datos y correcta implementacion de la capa Repository";
    public static final String SERVER_ERROR_MSG = "No es posible procesar la solicitud en este momento, intente mas tarde";
    public static final String NO_CONTENT_LOG = "Sin contenido";
    public static final String NO_CONTENT_MSG = "La consulta no arrojo ningun resultado";
    public static final String NOT_FOUND_LOG = "No encontrado";
    public static final String NOT_FOUND_MSG = "No fue posible encontrar el elemento solcitado";
    public static final String SUCCESS_LOG = "Operacion exitosa";
    public static final String SUCCESS_MSG = "Consulta exitosa";
    public static final String CREATED_MSG = "El usuario fue creado con exito";
    public static final String UPDATED_MSG = "El usuario fue actualizado con exito";
    public static final String DELETED_MSG = "El usuario fue eliminado con exito";
    public static final String REQUIRED_NOMBRE = "El campo nombre es obligatorio";
    public static final String REQUIRED_APELLIDO = "El campo apellido es obligatorio";
    public static final String REQUIRED_EMAIL = "El campo email es obligatorio";
    public static final String INVALID_EMAIL = "Por favor ingrese una direccion de correo valida";
    public static final String DOC_READ_ALL_SUM = "Obtiene todos los usuarios activos";
    public static final String DOC_READ_ALL_DESC = "Recupera una lista con todos los usuarios que cumplen la condicion isActive = true";
    public static final String DOC_READ_BY_ID_SUM = "Obtiene registros unicos por Id";
    public static final String DOC_READ_BY_ID_DESC="Recupera registros unicos en funcion del Id de cada usuario. El resultado es un regisro unico que cumple la condicion isActive = true";
    public static final String DOC_INSERT_SUM = "Crea registros individuales de nuevos usuarios";
    public static final String  DOC_INSERT_DEC = "Crea un registro nuevo de la entidad Usuario a partir de la informacion especificada en el cuerpo de la peticion. Su valor isActive por defecto es true";
    public static final String DOC_UPDATE_SUM = "Actualiza registros individuales de usuarios existentes";
    public static final String DOC_UPDATE_DESC = "Actualiza un registro existente de la entidad Usuario a partir de la informacion especificada en el cuerpo de la peticion. La eleccion del registro que sera actualizada se realiza a traves de la declaracion del Id como parte del PathVariable";
    public static final String DOC_DELETE_SUM = "Desactiva registros individuales de usuarios existentes";
    public static final String DOC_DELETE_DESC = "Realiza un borrado logico de registros individuales a traves de la definicion del Id como parte del PathVariable. La operacion logica define el valor del campo isActive=false";
    public static final String DOC_FIND_BY_EMAIL_SUM = "Consulta registros unicos de usuarios en funcion del campo email";
    public static final String DOC_FIND_BY_EMAIL_DESC = "Realiza una consulta de registros individuales usando como parametro de busqueda el campo email. El parametro de busqueda email es definido mediante query string. Los registros recuperados cumplen con la condicIon isActive= true";

}
