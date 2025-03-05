/*/
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Proyecto;
import com.springboot.MyTodoList.repository.ProyectoRepository;

@Service
public class ProyectossService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> findAll() {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        return proyectos;
    }

    public ResponseEntity<Proyecto> getProyectoById(int id) {
        Optional<Proyecto> proyectoData = proyectoRepository.findById(id);
        if (proyectoData.isPresent()) {
            return new ResponseEntity<>(proyectoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Proyecto addProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public boolean deleteProyecto(int id) {
        try {
            proyectoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Proyecto updateProyecto(int id, Proyecto proyecto) {
        Optional<Proyecto> proyectoData = proyectoRepository.findById(id);
        if (proyectoData.isPresent()) {
            Proyecto existingProyecto = proyectoData.get();
            existingProyecto.setNombre(proyecto.getNombre());
            existingProyecto.setFechaCreacion(proyecto.getFechaCreacion());
            existingProyecto.setDescripcion(proyecto.getDescripcion());
            existingProyecto.setActivo(proyecto.getActivo());
            return proyectoRepository.save(existingProyecto);
        } else {
            return null;
        }
    }
}
*/