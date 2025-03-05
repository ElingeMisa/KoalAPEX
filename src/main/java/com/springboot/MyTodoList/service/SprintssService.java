/*/
package com.springboot.MyTodoList.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.repository.SprintRepository;

@Service
public class SprintssService {
    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> findAll() {
        List<Sprint> sprints = sprintRepository.findAll();
        return sprints;
    }

    public ResponseEntity<Sprint> getSprintById(int id) {
        Optional<Sprint> sprintData = sprintRepository.findById(id);
        if (sprintData.isPresent()) {
            return new ResponseEntity<>(sprintData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Sprint addSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public boolean deleteSprint(int id) {
        try {
            sprintRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Sprint updateSprint(int id, Sprint sprint) {
        Optional<Sprint> sprintData = sprintRepository.findById(id);
        if (sprintData.isPresent()) {
            Sprint existingSprint = sprintData.get();
            existingSprint.setProyecto(sprint.getProyecto());
            existingSprint.setFechaInicio(sprint.getFechaInicio());
            existingSprint.setFechaFin(sprint.getFechaFin());
            existingSprint.setNombre(sprint.getNombre());
            existingSprint.setActivo(sprint.getActivo());
            return sprintRepository.save(existingSprint);
        } else {
            return null;
        }
    }
}
*/