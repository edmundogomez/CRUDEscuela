package com.citi.api.dto;

public class EliminarDTO {
    private String mensaje;

    public EliminarDTO(){}

    public EliminarDTO(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    } 

}
