package com.citi.api.excepcion;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcepcionManejador {

    @ExceptionHandler(value = {NoEncontradoExcepcion.class})
    public ResponseEntity<Object> manejarNoEncontradoExcepcion(NoEncontradoExcepcion ex){
        RespuestaError error = new RespuestaError();

        error.setEstado(HttpStatus.NOT_FOUND.value());
        error.setMensaje(ex.getMessage());
        error.setTiempo(ZonedDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MalaPeticionExcepcion.class})
    public ResponseEntity<Object> manejarMalaPeticionExcepcion(MalaPeticionExcepcion ex){
        RespuestaError error = new RespuestaError();

        error.setEstado(HttpStatus.BAD_REQUEST.value());
        error.setMensaje(ex.getMessage());
        error.setTiempo(ZonedDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ColisionExcepcion.class})
    public ResponseEntity<Object> manejarColisionExcepcion(ColisionExcepcion ex){
        RespuestaError error = new RespuestaError();

        error.setEstado(HttpStatus.CONFLICT.value());
        error.setMensaje(ex.getMessage());
        error.setTiempo(ZonedDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
