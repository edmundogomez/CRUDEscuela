package com.citi.api.servicio;

import java.util.List;

import com.citi.api.dto.EliminarDTO;
import com.citi.api.dto.EstudianteCompletoDTO;
import com.citi.api.dto.EstudianteDTO;
import com.citi.api.dto.EstudianteMateriaDTO;
import com.citi.api.dto.MateriasDTO;
import com.citi.api.entidad.Estudiante;
import com.citi.api.entidad.EstudianteMateria;

public interface EstudianteServicio {
    public List<Estudiante> obtenerEstudiantes();
    public Estudiante obtenerEstudiantePorId(int id);

    public EstudianteDTO guardarEstudiante(Estudiante estudiante);
    public Estudiante actualizarEstudiante(Estudiante estudiante);
    public EliminarDTO eliminarEstudiante(int estudianteId);

    public EstudianteCompletoDTO obtenerEstudianteCompletoPorId(int estudianteId);

    public List<MateriasDTO> obtenerMateriasDeEstudiante(int estudianteId);
    public EstudianteMateria agregarMateriaAEstudiante(int estudianteId, EstudianteMateriaDTO dto);
    public EstudianteMateria actualizarMateriaDeEstudiante(int estudianteId, EstudianteMateriaDTO dto);
    public EliminarDTO eliminarMateriaDeEstudiante(int estudianteId, int materiaId);
}
