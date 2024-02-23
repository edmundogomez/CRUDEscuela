package com.citi.api.dto;

import java.util.List;

public class EstudianteCompletoDTO {
    private int id;
    private String nombre; 
    private String apellidos;
    private float promedio;
    private List<MateriasDTO> materias;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public float getPromedio() {
        return promedio;
    }
    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
    public List<MateriasDTO> getMaterias() {
        return materias;
    }
    public void setMaterias(List<MateriasDTO> materias) {
        this.materias = materias;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


}
