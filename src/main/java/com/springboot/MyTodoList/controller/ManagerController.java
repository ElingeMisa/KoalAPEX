
package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Manager;
import com.springboot.MyTodoList.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
@CrossOrigin(origins = "*")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    //@CrossOrigin
    @GetMapping
    public List<Manager> getAllManagers() {
        return managerService.findAll();
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable int id) {
        try {
            Manager manager = managerService.findById(id);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @PostMapping
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager) throws Exception {
        Manager newManager = managerService.addManager(manager);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + newManager.getIdManage());
        responseHeaders.set("Access-Control-Expose-Headers", "location");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(newManager);
    }

    //@CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager, @PathVariable int id) {
        try {
            Manager updatedManager = managerService.updateManager(id, manager);
            return new ResponseEntity<>(updatedManager, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //@CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteManager(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = managerService.deleteManager(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
    
