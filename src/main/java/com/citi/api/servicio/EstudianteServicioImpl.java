package com.citi.api.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.api.dto.EliminarDTO;
import com.citi.api.dto.EstudianteCompletoDTO;
import com.citi.api.dto.EstudianteDTO;
import com.citi.api.dto.EstudianteMateriaDTO;
import com.citi.api.dto.MateriasDTO;
import com.citi.api.entidad.Estudiante;
import com.citi.api.entidad.EstudianteMateria;
import com.citi.api.entidad.Materia;
import com.citi.api.excepcion.ColisionExcepcion;
import com.citi.api.excepcion.MalaPeticionExcepcion;
import com.citi.api.excepcion.NoEncontradoExcepcion;
import com.citi.api.repositorio.EstudianteMateriaRepositorio;
import com.citi.api.repositorio.EstudianteRepositorio;
import com.citi.api.repositorio.MateriaRepositorio;


@Service
public class EstudianteServicioImpl implements EstudianteServicio{
    EstudianteServicioImpl(){}

    @Autowired
    private EstudianteRepositorio repositorio;

    @Autowired
    private MateriaRepositorio materiaRepositorio;

    @Autowired
    private EstudianteMateriaRepositorio estudianteMateriaRepositorio;

    @Override
    public List<Estudiante> obtenerEstudiantes() {
        return repositorio.findAll();
    }

    private void verificarDatosEstudiante(Estudiante estudiante){
        if(estudiante.getApellidos() == null || estudiante.getApellidos().isBlank())
            throw new MalaPeticionExcepcion("Apellidos en blanco");

        if(estudiante.getNombre() == null || estudiante.getNombre().isBlank())
            throw new MalaPeticionExcepcion("Nombre en blanco");
    }

    @Override
    public Estudiante obtenerEstudiantePorId(int id) {
        Estudiante estudiante = repositorio.findById(id).orElse(null);

        if(estudiante == null)
            throw new NoEncontradoExcepcion("Estudiante id no encontrado - " + id);

        return estudiante;
    }

    @Override
    public EstudianteDTO guardarEstudiante(Estudiante estudiante) {
        estudiante.setId(0);

        verificarDatosEstudiante(estudiante);

        return new EstudianteDTO(repositorio.save(estudiante));
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        Estudiante estudianteExistente = obtenerEstudiantePorId(estudiante.getId());

        verificarDatosEstudiante(estudiante);

        estudianteExistente.setApellidos(estudiante.getApellidos());
        estudianteExistente.setNombre(estudiante.getNombre());

        estudiante.setPromedio(estudianteExistente.getPromedio());

        return repositorio.save(estudiante);
    }

    @Override
    public EliminarDTO eliminarEstudiante(int estudianteId) {
        obtenerEstudiantePorId(estudianteId);

        repositorio.deleteById(estudianteId);

        return new EliminarDTO("Estudiante id se ha eliminado - " + estudianteId);
    }

    @Override
    public EstudianteCompletoDTO obtenerEstudianteCompletoPorId(int estudianteId){
        Estudiante estudiante = obtenerEstudiantePorId(estudianteId);
        EstudianteCompletoDTO dto = new EstudianteCompletoDTO();

        dto.setNombre(estudiante.getNombre());
        dto.setApellidos(estudiante.getApellidos());
        dto.setPromedio(estudiante.getPromedio());
        dto.setMaterias(obtenerMateriasDeEstudiante(estudianteId));

        return dto;
    }

    private EstudianteMateria obtenerPorEstudianteYMateria(int estudianteId, int materiaId){
        EstudianteMateria em = estudianteMateriaRepositorio.findByEstudianteIdAndMateriaId(estudianteId,materiaId);

        if(em == null)
            throw new NoEncontradoExcepcion("No se ha encontrado Materia Id - " + materiaId + " en Estudiante Id - " + estudianteId);
        
        return em;
    }

    private synchronized void actualizarPromedioGeneralDeEstudiante(int estudianteId){
        List<MateriasDTO> materias = estudianteMateriaRepositorio.findMateriasByEstudianteId(estudianteId);

        float suma = 0;
        for (MateriasDTO materia : materias) {
            suma += materia.getPromedio(); 
        }

        suma /= materias.size();

        Estudiante estudiante = repositorio.findById(estudianteId).orElse(null);

        estudiante.setPromedio(suma);

        repositorio.save(estudiante);
    }

    public void actualizarAsyncPromedioGeneralDeEstudiante(int estudianteId){
        Thread thread = new Thread(() -> {
            actualizarPromedioGeneralDeEstudiante(estudianteId);
        });
        thread.start();
    }

    @Override
    public List<MateriasDTO> obtenerMateriasDeEstudiante(int estudianteId){
        return estudianteMateriaRepositorio.findMateriasByEstudianteId(estudianteId);
    }

    private EstudianteMateria encontrarMateria(int materiaId, List<EstudianteMateria> materias){
        for(EstudianteMateria materia : materias){
            if(materia.getMateria().getId() == materiaId){
                return materia;
            }
        }
        return null;
    } 

    @Override
    public EstudianteMateria agregarMateriaAEstudiante(int estudianteId, EstudianteMateriaDTO dto) {
        Estudiante estudiante = obtenerEstudiantePorId(estudianteId);

        Materia materia = materiaRepositorio.findById(dto.getMateriaId()).orElse(null);

        if(materia == null){
            throw new NoEncontradoExcepcion("Materia id no encontrado - " + dto.getMateriaId());
        }

        EstudianteMateria relacion = new EstudianteMateria();

        relacion.setMateria(materia);
        relacion.setPromedio(dto.getPromedio());

        if(encontrarMateria(materia.getId(),estudiante.getMaterias()) != null)
            throw new ColisionExcepcion("Materia id - " + materia.getId() + " ya fue agregada a Estudiante Id - " + estudianteId + " previamente");

        estudiante.agregarMateria(relacion);

        EstudianteMateria nuevoEstudiante = estudianteMateriaRepositorio.save(relacion);
        actualizarAsyncPromedioGeneralDeEstudiante(estudianteId);

        return nuevoEstudiante;
    }

    private void verificarEstudiaMateriaDTO(EstudianteMateriaDTO dto){
        if(dto.getPromedio() <= 0.0f || dto.getPromedio() > 100.0)
            throw new MalaPeticionExcepcion("Promedio fuera de rango");

    }

    @Override
    public EstudianteMateria actualizarMateriaDeEstudiante(int estudianteId, EstudianteMateriaDTO dto) {
        EstudianteMateria em = obtenerPorEstudianteYMateria(estudianteId, dto.getMateriaId());

        verificarEstudiaMateriaDTO(dto);

        em.setPromedio(dto.getPromedio());

        EstudianteMateria nuevoEm = estudianteMateriaRepositorio.save(em);

        actualizarAsyncPromedioGeneralDeEstudiante(estudianteId);

        return nuevoEm;
    }

    @Override
    public EliminarDTO eliminarMateriaDeEstudiante(int estudianteId, int materiaId) {
        EstudianteMateria em = obtenerPorEstudianteYMateria(estudianteId, materiaId);

        estudianteMateriaRepositorio.deleteById(em.getId());

        actualizarAsyncPromedioGeneralDeEstudiante(estudianteId);

        return new EliminarDTO("Materia Id - " + materiaId + " se ha eliminado de Estudiante id - " + estudianteId);
    }
    
}
