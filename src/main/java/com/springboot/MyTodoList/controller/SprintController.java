
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sprints")
@CrossOrigin(origins = "*")
public class SprintController {

    @Autowired
    private SprintService sprintService;

    //@CrossOrigin
    @GetMapping
    public List<Sprint> getAllSprints() {
        return sprintService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable int id) {
        try {
            Sprint sprint = sprintService.findById(id);
            return new ResponseEntity<>(sprint, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint) throws Exception {
        Sprint newSprint = sprintService.addSprint(sprint);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newSprint.getIdSprint());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newSprint);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint, @PathVariable int id) {
        try {
            Sprint updatedSprint = sprintService.updateSprint(id, sprint);
            return new ResponseEntity<>(updatedSprint, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSprint(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = sprintService.deleteSprint(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}