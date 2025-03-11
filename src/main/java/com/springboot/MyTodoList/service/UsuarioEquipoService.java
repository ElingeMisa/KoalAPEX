package com.springboot.MyTodoList.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.springboot.MyTodoList.model.UsuarioEquipo;
import com.springboot.MyTodoList.repository.UsuarioEquipoRepository;

@Service
public class UsuarioEquipoService {
    
    private final UsuarioEquipoRepository usuarioEquipoRepository;

    @Autowired
    public UsuarioEquipoService(UsuarioEquipoRepository usuarioEquipoRepository) {
        this.usuarioEquipoRepository = usuarioEquipoRepository;
    }
    
    public List<UsuarioEquipo> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public UsuarioEquipo findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public UsuarioEquipo addUsuarioEquipo(UsuarioEquipo usuarioEquipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUsuarioEquipo'");
    }

    public UsuarioEquipo updateUsuarioEquipo(int id, UsuarioEquipo usuarioEquipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsuarioEquipo'");
    }

    public Boolean deleteUsuarioEquipo(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUsuarioEquipo'");
    }
}
