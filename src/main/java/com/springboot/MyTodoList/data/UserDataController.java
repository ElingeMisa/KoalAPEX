package com.springboot.MyTodoList.data;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userdata")
@CrossOrigin(origins = "*")
public class UserDataController {

    private final UserData userData;

    @Autowired
    public UserDataController(UserData userData) {
        this.userData = userData;
    }

    @GetMapping("/start")
    public String userData(Model model) {
        model.addAttribute("userData", userData);
        return "userData";
    }

    @GetMapping("/perfil")
    public String mostrarPerfil(Model model) {
        // Usar los datos almacenados en userData
        model.addAttribute("usuario", userData.getUsuario());
        model.addAttribute("desarrollador", userData.getDesarrollador());
        model.addAttribute("tareas", userData.getTareas());
        return "perfil";
    }
    
}
