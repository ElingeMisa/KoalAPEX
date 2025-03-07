/* 
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.EquipoProyecto;
import com.springboot.MyTodoList.service.EquipoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipo-proyecto")
@CrossOrigin(origins = "*")
public class EquipoProyectoController {

    @Autowired
    private EquipoProyectoService equipoProyectoService;

    //@CrossOrigin
    @GetMapping
    public List<EquipoProyecto> getAllEquipoProyecto() {
        return equipoProyectoService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<EquipoProyecto> getEquipoProyectoById(@PathVariable int id) {
        try {
            EquipoProyecto equipoProyecto = equipoProyectoService.findById(id);
            return new ResponseEntity<>(equipoProyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<EquipoProyecto> addEquipoProyecto(@RequestBody EquipoProyecto equipoProyecto) throws Exception {
        EquipoProyecto newEquipoProyecto = equipoProyectoService.addEquipoProyecto(equipoProyecto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newEquipoProyecto.getIdEquipoProyecto());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newEquipoProyecto);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<EquipoProyecto> updateEquipoProyecto(@RequestBody EquipoProyecto equipoProyecto, @PathVariable int id) {
        try {
            EquipoProyecto updatedEquipoProyecto = equipoProyectoService.updateEquipoProyecto(id, equipoProyecto);
            return new ResponseEntity<>(updatedEquipoProyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEquipoProyecto(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = equipoProyectoService.deleteEquipoProyecto(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
    */
