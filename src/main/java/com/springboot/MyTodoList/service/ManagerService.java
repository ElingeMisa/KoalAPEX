package com.springboot.MyTodoList.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Manager;
import com.springboot.MyTodoList.repository.ManagerRepository;

@Service
public class ManagerService {
    
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }
    
    public List<Manager> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Manager findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Manager addManager(Manager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addManager'");
    }

    public Manager updateManager(int id, Manager manager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateManager'");
    }

    public Boolean deleteManager(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteManager'");
    }

    public List<Manager> findByIdUsuario(long idUsuario) {
        // TODO Auto-generated method stub
        List<Manager> managers = managerRepository.findByIdUsuario(idUsuario);
        return managers;
    }
}
