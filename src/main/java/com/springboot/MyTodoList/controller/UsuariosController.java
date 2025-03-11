
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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.service.UsuariosService;

@RestController
public class UsuariosController {
    
    @Autowired
    private UsuariosService usuariosService;

    //@CrossOrigin
    @GetMapping(value = "/Usuarios")
    public List<Usuarios> getAllUsuarios(){
        return usuariosService.findAll();
    }

    //@CrossOrigin
    @GetMapping(value = "/Usuarios/{id}")
    public ResponseEntity<Usuarios> getUsuariosById(@PathVariable int id){
        try{
            Usuarios usuario = usuariosService.findById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping(value = "/Usuarios")
    public ResponseEntity<Usuarios> addUsuarios(@RequestBody Usuarios usuario) throws Exception{
        Usuarios newUsuario = usuariosService.addUsuario(usuario);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newUsuario.getId());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders).body(newUsuario);
    }

    //@CrossOrigin
    @PutMapping(value = "/Usuarios/{id}")
    public ResponseEntity<Usuarios> updateUsuarios(@RequestBody Usuarios usuario, @PathVariable int id){
        try{
            Usuarios updatedUsuario = usuariosService.updateUsuarios(id, usuario);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping(value = "/Usuarios/{id}")
    public ResponseEntity<Boolean> deleteUsuarios(@PathVariable("id") int id){
        Boolean flag = false;
        try{
            flag = usuariosService.deleteUsuario(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
 
 
