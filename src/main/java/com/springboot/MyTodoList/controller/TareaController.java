/*
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    //@CrossOrigin
    @GetMapping
    public List<Tarea> getAllTareas() {
        return tareaService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable int id) {
        try {
            Tarea tarea = tareaService.findById(id);
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<Tarea> addTarea(@RequestBody Tarea tarea) throws Exception {
        Tarea newTarea = tareaService.addTarea(tarea);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newTarea.getIdTarea());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newTarea);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTarea(@RequestBody Tarea tarea, @PathVariable int id) {
        try {
            Tarea updatedTarea = tareaService.updateTarea(id, tarea);
            return new ResponseEntity<>(updatedTarea, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTarea(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = tareaService.deleteTarea(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
 */
