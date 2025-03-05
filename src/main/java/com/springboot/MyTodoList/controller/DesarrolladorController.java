/*/
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.service.DesarrolladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desarrolladores")
@CrossOrigin(origins = "*")
public class DesarrolladorController {

    @Autowired
    private DesarrolladorService desarrolladorService;

    //@CrossOrigin
    @GetMapping
    public List<Desarrollador> getAllDesarrolladores() {
        return desarrolladorService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Desarrollador> getDesarrolladorById(@PathVariable int id) {
        try {
            Desarrollador desarrollador = desarrolladorService.findById(id);
            return new ResponseEntity<>(desarrollador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<Desarrollador> addDesarrollador(@RequestBody Desarrollador desarrollador) throws Exception {
        Desarrollador newDesarrollador = desarrolladorService.addDesarrollador(desarrollador);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newDesarrollador.getIdDesarrollador());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newDesarrollador);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Desarrollador> updateDesarrollador(@RequestBody Desarrollador desarrollador, @PathVariable int id) {
        try {
            Desarrollador updatedDesarrollador = desarrolladorService.updateDesarrollador(id, desarrollador);
            return new ResponseEntity<>(updatedDesarrollador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDesarrollador(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = desarrolladorService.deleteDesarrollador(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
*/