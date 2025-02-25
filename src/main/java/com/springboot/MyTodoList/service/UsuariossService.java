/*
 package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.repository.UsuariosRepository;

public class UsuariossService {
    @Autowired
    private UsuariosRepository UsuariosRepository;

    public List<Usuarios> findAll(){
        List<Usuarios> Usuarioss = UsuariosRepository.findAll();
        return Usuarioss;
    }

    public ResponseEntity<Usuarios> getUsuariosById(int id){
        Optional<Usuarios> UsuariosData = UsuariosRepository.findById(id);
        if (UsuariosData.isPresent()){
            return new ResponseEntity<>(UsuariosData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Usuarios addUsuarios(Usuarios Usuarios){
        return UsuariosRepository.save(Usuarios);
    }

    public boolean deleteUsuarios(int id){
        try{
            UsuariosRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Usuarios updateUsuarios(int id, Usuarios Usuarios){
        Optional<Usuarios> UsuariosData = UsuariosRepository.findById(id);
        if(UsuariosData.isPresent()){
            Usuarios existingUsuarios = UsuariosData.get();
            existingUsuarios.setID(id);
            existingUsuarios.setNombre(Usuarios.getNombre());
            existingUsuarios.setCorreo(Usuarios.getCorreo());
            existingUsuarios.setTelefono(Usuarios.getTelefono());
            return UsuariosRepository.save(existingUsuarios);
        }else{
            return null;
        }
    }
}

 */