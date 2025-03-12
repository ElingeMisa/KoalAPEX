package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.repository.SprintRepository;

@Service("sprintService")
public class SprintService {

    
    private final SprintRepository sprintRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }
    
    public List<Sprint> findAll() {
        return sprintRepository.findAll();
    }

    public Sprint findByIdSprint(int id) {
        Optional<Sprint> sprint = sprintRepository.findById(id);
        if (sprint.isPresent()) {
            return sprint.get();
        }
        return null;
    }

    public Sprint addSprint(Sprint sprint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSprint'");
    }

    public Sprint updateSprint(int id, Sprint sprint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSprint'");
    }

    public Boolean deleteSprint(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSprint'");
    }
}

