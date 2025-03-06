/*/
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.repository.DesarrolladorRepository;

@Service
public class DesarrolladorssService {
    @Autowired
    private DesarrolladorRepository desarrolladorRepository;

    public List<Desarrollador> findAll() {
        List<Desarrollador> desarrolladores = desarrolladorRepository.findAll();
        return desarrolladores;
    }

    public ResponseEntity<Desarrollador> getDesarrolladorById(int id) {
        Optional<Desarrollador> desarrolladorData = desarrolladorRepository.findById(id);
        if (desarrolladorData.isPresent()) {
            return new ResponseEntity<>(desarrolladorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Desarrollador addDesarrollador(Desarrollador desarrollador) {
        return desarrolladorRepository.save(desarrollador);
    }

    public boolean deleteDesarrollador(int id) {
        try {
            desarrolladorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Desarrollador updateDesarrollador(int id, Desarrollador desarrollador) {
        Optional<Desarrollador> desarrolladorData = desarrolladorRepository.findById(id);
        if (desarrolladorData.isPresent()) {
            Desarrollador existingDesarrollador = desarrolladorData.get();
            existingDesarrollador.setUsuario(desarrollador.getUsuario());
            existingDesarrollador.setModalidad(desarrollador.getModalidad());
            existingDesarrollador.setCodigoChamba(desarrollador.getCodigoChamba());
            existingDesarrollador.setActivo(desarrollador.getActivo());
            return desarrolladorRepository.save(existingDesarrollador);
        } else {
            return null;
        }
    }
}
*/