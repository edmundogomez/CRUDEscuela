package com.citi.api.dto;

import com.citi.api.entidad.Materia;

public class MateriasDTO {
    private Materia materia;
    private float promedio;

    public MateriasDTO(Materia materia,float promedio){
        this.promedio = promedio;
        this.materia = materia;
    }

    public float getPromedio() {
        return promedio;
    }

    public Materia getMateria() {
        return materia;
    }
}
