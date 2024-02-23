package com.citi.api.excepcion;

public class NoEncontradoExcepcion extends RuntimeException{
    public NoEncontradoExcepcion(String mensaje){
        super(mensaje);
    }

    public NoEncontradoExcepcion(String mensaje, Throwable causa){
        super(mensaje,causa);
    }
}