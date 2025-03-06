/* 
package com.springboot.MyTodoList.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.*;

@Entity
@Table(name = "Manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_seq")
    @SequenceGenerator(name = "manager_seq", sequenceName = "Manager_seq", allocationSize = 1)
    @Column(name = "idManage", nullable = false)
    private Integer idManage;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID", nullable = true)
    private Usuarios usuario;

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Manager() {}

    public Manager(Integer idManage, Usuarios usuario, Integer activo) {
        this.idManage = idManage;
        this.usuario = usuario;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdManage() {
        return idManage;
    }

    public void setIdManage(Integer idManage) {
        this.idManage = idManage;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
    */
