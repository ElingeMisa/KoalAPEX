/*
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.UsuarioEquipo;
import com.springboot.MyTodoList.service.UsuarioEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-equipo")
@CrossOrigin(origins = "*")
public class UsuarioEquipoController {

    @Autowired
    private UsuarioEquipoService usuarioEquipoService;

    //@CrossOrigin
    @GetMapping
    public List<UsuarioEquipo> getAllUsuarioEquipo() {
        return usuarioEquipoService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEquipo> getUsuarioEquipoById(@PathVariable int id) {
        try {
            UsuarioEquipo usuarioEquipo = usuarioEquipoService.findById(id);
            return new ResponseEntity<>(usuarioEquipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<UsuarioEquipo> addUsuarioEquipo(@RequestBody UsuarioEquipo usuarioEquipo) throws Exception {
        UsuarioEquipo newUsuarioEquipo = usuarioEquipoService.addUsuarioEquipo(usuarioEquipo);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newUsuarioEquipo.getIdUsuarioEquipo());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newUsuarioEquipo);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEquipo> updateUsuarioEquipo(@RequestBody UsuarioEquipo usuarioEquipo, @PathVariable int id) {
        try {
            UsuarioEquipo updatedUsuarioEquipo = usuarioEquipoService.updateUsuarioEquipo(id, usuarioEquipo);
            return new ResponseEntity<>(updatedUsuarioEquipo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsuarioEquipo(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = usuarioEquipoService.deleteUsuarioEquipo(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
*/