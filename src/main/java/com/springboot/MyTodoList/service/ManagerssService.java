/*/
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Manager;
import com.springboot.MyTodoList.repository.ManagerRepository;

@Service
public class ManagerssService {
    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> findAll() {
        List<Manager> managers = managerRepository.findAll();
        return managers;
    }

    public ResponseEntity<Manager> getManagerById(int id) {
        Optional<Manager> managerData = managerRepository.findById(id);
        if (managerData.isPresent()) {
            return new ResponseEntity<>(managerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Manager addManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public boolean deleteManager(int id) {
        try {
            managerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Manager updateManager(int id, Manager manager) {
        Optional<Manager> managerData = managerRepository.findById(id);
        if (managerData.isPresent()) {
            Manager existingManager = managerData.get();
            existingManager.setUsuario(manager.getUsuario());
            existingManager.setActivo(manager.getActivo());
            return managerRepository.save(existingManager);
        } else {
            return null;
        }
    }
}
*/