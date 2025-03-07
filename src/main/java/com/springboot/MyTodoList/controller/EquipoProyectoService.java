/*
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.EquipoProyecto;
import com.springboot.MyTodoList.repository.EquipoProyectoRepository;

@Service
public class EquipoProyectoService {
    @Autowired
    private EquipoProyectoRepository equipoProyectoRepository;

    public List<EquipoProyecto> findAll() {
        return equipoProyectoRepository.findAll();
    }

    public ResponseEntity<EquipoProyecto> getEquipoProyectoById(int id) {
        Optional<EquipoProyecto> equipoProyectoData = equipoProyectoRepository.findById(id);
        return equipoProyectoData.map(equipoProyecto -> new ResponseEntity<>(equipoProyecto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public EquipoProyecto addEquipoProyecto(EquipoProyecto equipoProyecto) {
        return equipoProyectoRepository.save(equipoProyecto);
    }

    public boolean deleteEquipoProyecto(int id) {
        try {
            equipoProyectoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EquipoProyecto updateEquipoProyecto(int id, EquipoProyecto equipoProyecto) {
        Optional<EquipoProyecto> equipoProyectoData = equipoProyectoRepository.findById(id);
        if (equipoProyectoData.isPresent()) {
            EquipoProyecto existingEquipoProyecto = equipoProyectoData.get();
            existingEquipoProyecto.setEquipo(equipoProyecto.getEquipo());
            existingEquipoProyecto.setProyecto(equipoProyecto.getProyecto());
            existingEquipoProyecto.setActivo(equipoProyecto.getActivo());
            return equipoProyectoRepository.save(existingEquipoProyecto);
        } else {
            return null;
        }
    }
}
*/