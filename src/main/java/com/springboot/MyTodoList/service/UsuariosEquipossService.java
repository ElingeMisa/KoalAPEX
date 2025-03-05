/* 
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.UsuarioEquipo;
import com.springboot.MyTodoList.repository.UsuarioEquipoRepository;

@Service
public class UsuarioEquipossService {
    @Autowired
    private UsuarioEquipoRepository usuarioEquipoRepository;

    public List<UsuarioEquipo> findAll() {
        return usuarioEquipoRepository.findAll();
    }

    public ResponseEntity<UsuarioEquipo> getUsuarioEquipoById(int id) {
        Optional<UsuarioEquipo> usuarioEquipoData = usuarioEquipoRepository.findById(id);
        return usuarioEquipoData.map(usuarioEquipo -> new ResponseEntity<>(usuarioEquipo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public UsuarioEquipo addUsuarioEquipo(UsuarioEquipo usuarioEquipo) {
        return usuarioEquipoRepository.save(usuarioEquipo);
    }

    public boolean deleteUsuarioEquipo(int id) {
        try {
            usuarioEquipoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UsuarioEquipo updateUsuarioEquipo(int id, UsuarioEquipo usuarioEquipo) {
        Optional<UsuarioEquipo> usuarioEquipoData = usuarioEquipoRepository.findById(id);
        if (usuarioEquipoData.isPresent()) {
            UsuarioEquipo existingUsuarioEquipo = usuarioEquipoData.get();
            existingUsuarioEquipo.setUsuario(usuarioEquipo.getUsuario());
            existingUsuarioEquipo.setEquipo(usuarioEquipo.getEquipo());
            existingUsuarioEquipo.setRol(usuarioEquipo.getRol());
            existingUsuarioEquipo.setActivo(usuarioEquipo.getActivo());
            return usuarioEquipoRepository.save(existingUsuarioEquipo);
        } else {
            return null;
        }
    }
}
*/