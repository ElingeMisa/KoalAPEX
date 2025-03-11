package com.springboot.MyTodoList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.repository.EquipoRepository;

@Service
public class EquipoService {
    
    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Equipo findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Equipo addEquipo(Equipo equipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEquipo'");
    }

    public Equipo updateEquipo(Long id, Equipo equipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEquipo'");
    }

    public Boolean deleteEquipo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEquipo'");
    }

    public List<Equipo> findByIdUsuario(Long id) {
        List<Equipo> equipos = equipoRepository.findByUsuarioId(id);
        return equipos;
        
    }
}
