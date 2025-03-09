
package com.springboot.MyTodoList.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprint_seq")
    @SequenceGenerator(name = "sprint_seq", sequenceName = "Sprint_seq", allocationSize = 1)
    @Column(name = "idSprint", nullable = false)
    private Integer idSprint;

    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto", nullable = true)
    private Proyecto proyecto;

    @Column(name = "FechaInicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "FechaFin")
    private LocalDateTime fechaFin;

    @Column(name = "Nombre", length = 100)
    private String nombre = "SprintProyecto";

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Sprint() {}

    public Sprint(Integer idSprint, Proyecto proyecto, LocalDateTime fechaInicio, LocalDateTime fechaFin, String nombre, Integer activo) {
        this.idSprint = idSprint;
        this.proyecto = proyecto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}