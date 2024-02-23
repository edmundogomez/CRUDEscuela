package com.citi.api.entidad;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "promedio")
    private float promedio;

    @OneToMany( cascade = CascadeType.REMOVE, 
                fetch = FetchType.LAZY,
                mappedBy = "estudiante")
    @JsonIgnore
    @JsonManagedReference
    private List<EstudianteMateria> materias;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public float getPromedio() {
        return promedio;
    }

    public void setPromedio(float grade) {
        this.promedio = grade;
    }

    public List<EstudianteMateria> getMaterias() {
        return materias;
    }

    public void setMaterias(List<EstudianteMateria> subjects) {
        this.materias = subjects;
    }

    public void agregarMateria(EstudianteMateria materia){
        if(materias == null){
            materias = new ArrayList<>();
        }

        materias.add(materia);

        materia.setEstudiante(this);
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String subname) {
        this.apellidos = subname;
    }


}
