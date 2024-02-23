package com.citi.api.excepcion;

import java.time.ZonedDateTime;

public class RespuestaError {
    private int estado;
    private String mensaje;    
    private ZonedDateTime tiempo;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ZonedDateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(ZonedDateTime tiempo) {
        this.tiempo = tiempo;
    }
}
