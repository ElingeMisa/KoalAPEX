/*
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.repository.TareaRepository;

@Service
public class TareassService {
    @Autowired
    private TareaRepository tareaRepository;

    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    public ResponseEntity<Tarea> getTareaById(int id) {
        Optional<Tarea> tareaData = tareaRepository.findById(id);
        return tareaData.map(tarea -> new ResponseEntity<>(tarea, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Tarea addTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public boolean deleteTarea(int id) {
        try {
            tareaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Tarea updateTarea(int id, Tarea tarea) {
        Optional<Tarea> tareaData = tareaRepository.findById(id);
        if (tareaData.isPresent()) {
            Tarea existingTarea = tareaData.get();
            existingTarea.setDescripcion(tarea.getDescripcion());
            existingTarea.setFechaEntrega(tarea.getFechaEntrega());
            existingTarea.setHorasEstimadas(tarea.getHorasEstimadas());
            existingTarea.setHorasReales(tarea.getHorasReales());
            existingTarea.setActivo(tarea.getActivo());
            existingTarea.setEstado(tarea.getEstado());
            existingTarea.setCategoria(tarea.getCategoria());
            existingTarea.setDesarrollador(tarea.getDesarrollador());
            existingTarea.setProyecto(tarea.getProyecto());
            existingTarea.setSprint(tarea.getSprint());
            return tareaRepository.save(existingTarea);
        } else {
            return null;
        }
    }
}
*/
