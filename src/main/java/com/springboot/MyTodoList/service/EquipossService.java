/* 
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.repository.EquipoRepository;

@Service
public class EquipossService {
    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        List<Equipo> equipos = equipoRepository.findAll();
        return equipos;
    }

    public ResponseEntity<Equipo> getEquipoById(int id) {
        Optional<Equipo> equipoData = equipoRepository.findById((long) id);
        if (equipoData.isPresent()) {
            return new ResponseEntity<>(equipoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Equipo addEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public boolean deleteEquipo(int id) {
        try {
            equipoRepository.deleteById((long) id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Equipo updateEquipo(int id, Equipo equipo) {
        Optional<Equipo> equipoData = equipoRepository.findById((long) id);
        if (equipoData.isPresent()) {
            Equipo existingEquipo = equipoData.get();
            existingEquipo.setNombre(equipo.getNombre());
            existingEquipo.setNotificacion(equipo.getNotificacion());
            existingEquipo.setActivo(equipo.getActivo());
            return equipoRepository.save(existingEquipo);
        } else {
            return null;
        }
    }
}
/* */