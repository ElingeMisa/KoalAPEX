package com.springboot.MyTodoList.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.model.Manager;
import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.model.Usuarios;

@Component
public class UserData {

    public Usuarios usuario;
    public String rol;
    public Desarrollador desarrollador;
    public Manager manager;
    
    public List<Tarea> tareas;

    public UserData() {
        this.usuario = new Usuarios();
        this.rol = "usuario";
        this.desarrollador = new Desarrollador();
        this.manager = new Manager();
        this.tareas = new ArrayList<>();
    }

    public UserData(Usuarios usuario, String rol, Desarrollador desarrollador, Manager manager) {
        this.usuario = usuario;
        this.rol = rol;
        this.desarrollador = desarrollador;
        this.manager = manager;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "UserData\n{\n\t\t\tusuario=" + usuario +
                ",\n\t\t\trol='" + rol + '\'' +
                ",\n\t\t\tdesarrollador=" + desarrollador +
                ",\n\t\t\tmanager=" + manager +
                "\n}";
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public Tarea getTarea(int index) {
        return tareas.get(index);
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    
}