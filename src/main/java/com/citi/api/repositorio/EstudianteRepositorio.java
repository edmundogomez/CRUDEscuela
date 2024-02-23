package com.citi.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citi.api.entidad.Estudiante;

public interface EstudianteRepositorio extends JpaRepository<Estudiante,Integer>{

}
