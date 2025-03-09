
package com.springboot.MyTodoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.TareassService;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    @Autowired
    private TareassService tareaService;

    //@CrossOrigin
    @GetMapping(value = "/tareas")
    public List<Tarea> getAllTareas() {
        return tareaService.findAll();
    }

    //@CrossOrigin
    @GetMapping(value = "/tareas/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable int id) {
        try {
            ResponseEntity<Tarea> tarea = tareaService.getTareaById(id);
            return new ResponseEntity<>(tarea.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping(value = "/tareas")
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
    @PutMapping(value = "/tareas/{id}")
    public ResponseEntity<Tarea> updateTarea(@RequestBody Tarea tarea, @PathVariable int id) {
        try {
            Tarea updatedTarea = tareaService.updateTarea(id, tarea);
            return new ResponseEntity<>(updatedTarea, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping(value = "/tareas/{id}")
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