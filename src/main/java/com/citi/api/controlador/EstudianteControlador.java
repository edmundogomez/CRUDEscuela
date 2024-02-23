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

import com.citi.api.dto.MateriasDTO;
import com.citi.api.dto.EliminarDTO;
import com.citi.api.dto.EstudianteCompletoDTO;
import com.citi.api.dto.EstudianteDTO;
import com.citi.api.dto.EstudianteMateriaDTO;
import com.citi.api.entidad.Estudiante;
import com.citi.api.entidad.EstudianteMateria;
import com.citi.api.servicio.EstudianteServicio;

@RestController
@RequestMapping("/api")
public class EstudianteControlador {

    private EstudianteServicio servicio;

    @Autowired
    public EstudianteControlador(EstudianteServicio servicio){
        this.servicio = servicio;
    }

    @GetMapping("/estudiantes")
    public List<Estudiante> findAllStudents(){
        return servicio.obtenerEstudiantes();
    }

    @GetMapping("/estudiantes/{estudianteId}")
    public Estudiante obtenerEstudiante(@PathVariable int estudianteId){
        return servicio.obtenerEstudiantePorId(estudianteId);
    }

    @GetMapping("/estudiantes/{estudianteId}/completo")
    public EstudianteCompletoDTO obtenerEstudianteCompleto(@PathVariable int estudianteId){
        return servicio.obtenerEstudianteCompletoPorId(estudianteId);
    }

    @PostMapping(value = "/estudiantes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudianteDTO crearEstudiante(@RequestBody Estudiante estudiante){
        return servicio.guardarEstudiante(estudiante);
    }

    @PutMapping(value = "/estudiantes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Estudiante updateStudent(@RequestBody Estudiante estudiante){
        return servicio.actualizarEstudiante(estudiante);
    }

    @DeleteMapping(value = "/estudiantes/{estudianteId}")
    public EliminarDTO eliminarEstudiante(@PathVariable int estudianteId){
        return servicio.eliminarEstudiante(estudianteId);
    }
    


    @GetMapping("/estudiantes/{estudianteId}/materias")
    public List<MateriasDTO> getStudentMateria(@PathVariable int estudianteId){
        return servicio.obtenerMateriasDeEstudiante(estudianteId);
    }

    @PostMapping(value = "/estudiantes/{estudianteId}/materias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudianteMateria createStudentMateria(@PathVariable int estudianteId, @RequestBody EstudianteMateriaDTO dto){
        return servicio.agregarMateriaAEstudiante(estudianteId,dto);
    }

    @PutMapping(value = "/estudiantes/{estudianteId}/materias", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudianteMateria updateStudentMateria(@PathVariable int estudianteId, @RequestBody EstudianteMateriaDTO dto){
        return servicio.actualizarMateriaDeEstudiante(estudianteId,dto);
    }

    @DeleteMapping(value = "/estudiantes/{estudianteId}/materias/{materiaId}")
    public EliminarDTO deleteStudentMateria(@PathVariable int estudianteId, @PathVariable int materiaId){
        return servicio.eliminarMateriaDeEstudiante(estudianteId,materiaId);
    }
}
