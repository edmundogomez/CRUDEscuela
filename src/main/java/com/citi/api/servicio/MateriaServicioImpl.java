package com.citi.api.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.api.dto.EliminarDTO;
import com.citi.api.entidad.Materia;
import com.citi.api.excepcion.MalaPeticionExcepcion;
import com.citi.api.excepcion.NoEncontradoExcepcion;
import com.citi.api.repositorio.EstudianteMateriaRepositorio;
import com.citi.api.repositorio.MateriaRepositorio;

@Service
public class MateriaServicioImpl implements MateriaServicio{

    MateriaServicioImpl() {}

    @Autowired
    private MateriaRepositorio repository;

    @Autowired
    private EstudianteMateriaRepositorio estudianteMateriaRepositorio;
    
    private void verificarMateria(Materia materia){
        if(materia.getNombre() == null  || materia.getNombre().isBlank())
            throw new MalaPeticionExcepcion("Nombre en blanco");
    }

    public List<Materia> obtenerMaterias(){
        return repository.findAll();
    }

    public Materia obtenerMateriaPorId(int id){
        Materia materia = repository.findById(id).orElse(null);
        
        if(materia == null)
            throw new NoEncontradoExcepcion("Materia id no encontrado - " + id);

        return materia;
    }

    public Materia guardarMateria(Materia materia){
        materia.setId(0);

        verificarMateria(materia);

        return repository.save(materia);
    }

    public Materia actualizarMateria(Materia materia){
        Materia materiaExistente = obtenerMateriaPorId(materia.getId());

        verificarMateria(materia);

        materiaExistente.setNombre(materia.getNombre());

        return repository.save(materiaExistente);
    }

    public EliminarDTO eliminarMateria(int materiaId){
        obtenerMateriaPorId(materiaId);

        estudianteMateriaRepositorio.deleteByMateriaId(materiaId);
        repository.deleteById(materiaId);

        return new EliminarDTO("Materia id se ha eliminado - " + materiaId);
    }
}
