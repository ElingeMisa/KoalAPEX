/*
package com.springboot.MyTodoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.service.EquipoService;


@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // Obtener todos los equipos
    @GetMapping
    public List<Equipo> getAllEquipos() {
        return equipoService.findAll();
    }

    // Obtener un equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        try {
            Equipo equipo = equipoService.findById(id);
            return new ResponseEntity<>(equipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Agregar un nuevo equipo
    @PostMapping
    public ResponseEntity<Equipo> addEquipo(@RequestBody Equipo equipo) throws Exception {
        Equipo newEquipo = equipoService.addEquipo(equipo);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newEquipo.getIdEquipo());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newEquipo);
    }

    // Actualizar un equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@RequestBody Equipo equipo, @PathVariable Long id) {
        try {
            Equipo updatedEquipo = equipoService.updateEquipo(id, equipo);
            return new ResponseEntity<>(updatedEquipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un equipo
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEquipo(@PathVariable("id") Long id) {
        Boolean flag = false;
        try {
            flag = equipoService.deleteEquipo(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
     */
