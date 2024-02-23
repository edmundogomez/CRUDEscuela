package com.citi.api.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.api.dto.EliminarDTO;
import com.citi.api.entidad.Materia;
import com.citi.api.servicio.MateriaServicio;

@RestController
@RequestMapping("/api")
public class MateriaControlador {

    @Autowired
    private MateriaServicio service;

    @GetMapping("/materias")
    public List<Materia> findAllSubjects(){
        return service.obtenerMaterias();
    }

    @GetMapping("/materias/{materiaId}")
    public Materia getSubject(@PathVariable int materiaId){
        return service.obtenerMateriaPorId(materiaId);
    }

    @PostMapping(value = "/materias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Materia createSubject(@RequestBody Materia subject){
        return service.guardarMateria(subject);
    }

    @PutMapping(value = "/materias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Materia updateSubject(@RequestBody Materia subject){
        return service.actualizarMateria(subject);
    }

    @DeleteMapping(value = "/materias/{materiaId}")
    public EliminarDTO deleteSubject(@PathVariable int materiaId){
        return service.eliminarMateria(materiaId);
    }
}
