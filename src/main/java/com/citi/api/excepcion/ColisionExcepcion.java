package com.citi.api.excepcion;

public class ColisionExcepcion extends RuntimeException{
    public ColisionExcepcion(String mensaje){
        super(mensaje);
    }

    public ColisionExcepcion(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}

