package com.citi.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.citi.api.dto.MateriasDTO;
import com.citi.api.entidad.EstudianteMateria;

import jakarta.transaction.Transactional;

public interface EstudianteMateriaRepositorio extends JpaRepository<EstudianteMateria,Integer>{

    //@Query("select em from EstudianteMateria em JOIN FETCH em.materia where em.estudiante.id = ?1")
    //List<EstudianteMateria> findMateriasByEstudianteId(int estudianteId);

    @Query("select new com.citi.api.dto.MateriasDTO(em.materia,em.promedio) from EstudianteMateria em JOIN em.materia where em.estudiante.id = ?1")
    List<MateriasDTO> findMateriasByEstudianteId(int estudianteId);

    @Query("select em from EstudianteMateria em JOIN FETCH em.estudiante where em.estudiante.id = ?1 and em.materia.id = ?2")
    EstudianteMateria findByEstudianteIdAndMateriaId(int estudianteId,int materiaId);

    @Modifying
    @Transactional
    @Query("delete from EstudianteMateria em where em.materia.id = ?1")
    void  deleteByMateriaId(int materiaId);
}
