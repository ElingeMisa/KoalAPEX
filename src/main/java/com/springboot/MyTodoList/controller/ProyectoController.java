/*/
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Proyecto;
import com.springboot.MyTodoList.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    //@CrossOrigin
    @GetMapping
    public List<Proyecto> getAllProyectos() {
        return proyectoService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable int id) {
        try {
            Proyecto proyecto = proyectoService.findById(id);
            return new ResponseEntity<>(proyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<Proyecto> addProyecto(@RequestBody Proyecto proyecto) throws Exception {
        Proyecto newProyecto = proyectoService.addProyecto(proyecto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newProyecto.getIdProyecto());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newProyecto);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> updateProyecto(@RequestBody Proyecto proyecto, @PathVariable int id) {
        try {
            Proyecto updatedProyecto = proyectoService.updateProyecto(id, proyecto);
            return new ResponseEntity<>(updatedProyecto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProyecto(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = proyectoService.deleteProyecto(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
    */
