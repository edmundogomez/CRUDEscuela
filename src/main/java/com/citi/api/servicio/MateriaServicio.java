package com.citi.api.servicio;

import java.util.List;

import com.citi.api.dto.EliminarDTO;
import com.citi.api.entidad.Materia;

public interface MateriaServicio {
    public List<Materia> obtenerMaterias();
    public Materia obtenerMateriaPorId(int id);
    public Materia guardarMateria(Materia materia);
    public Materia actualizarMateria(Materia materia);
    public EliminarDTO eliminarMateria(int materiaId);
    
}
