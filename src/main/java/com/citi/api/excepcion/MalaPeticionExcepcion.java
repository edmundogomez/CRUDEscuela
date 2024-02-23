package com.citi.api.excepcion;

public class MalaPeticionExcepcion extends RuntimeException{
    public MalaPeticionExcepcion(String mensaje){
        super(mensaje);
    }

    public MalaPeticionExcepcion(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}
