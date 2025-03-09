
package com.springboot.MyTodoList.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
@Table(name = "Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarea_seq")
    @SequenceGenerator(name = "tarea_seq", sequenceName = "Tarea_seq", allocationSize = 1)
    @Column(name = "idTarea", nullable = false)
    private Integer idTarea;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "dueDate")
    private LocalDateTime dueDate;

    @Column(name = "estimatedHours", columnDefinition = "NUMBER DEFAULT 2")
    private Integer estimatedHours = 2;

    @Column(name = "horasReales", length = 100)
    private String horasReales = "2";

    @Column(name = "activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (activo IN (0,1))")
    private Integer activo = 1;

    @Column(name = "state", length = 100)
    private String state = "Activo";

    @Column(name = "category", length = 100)
    private String category = "Tarea";

    @Column(name = "done", columnDefinition = "NUMBER(1) DEFAULT 0 CHECK (done IN (0,1))")
    private boolean done = false;

    @ManyToOne
    @JoinColumn(name = "idDesarrollador", referencedColumnName = "idDesarrollador", nullable = true)
    private Desarrollador desarrollador;

    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto", nullable = true)
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "idSprint", referencedColumnName = "idSprint", nullable = true)
    private Sprint sprint;

    // Constructores
    public Tarea() {}

    public Tarea(Integer idTarea, String description, LocalDateTime dueDate, Integer estimatedHours, String horasReales,
                 Integer activo, String state, String category, Desarrollador desarrollador, Proyecto proyecto, Sprint sprint) {
        this.idTarea = idTarea;
        this.description = description;
        this.dueDate = dueDate;
        this.estimatedHours = estimatedHours;
        this.horasReales = horasReales;
        this.activo = activo;
        this.state = state;
        this.category = category;
        this.desarrollador = desarrollador;
        this.proyecto = proyecto;
        this.sprint = sprint;
    }

    // Getters y Setters
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String description) {
        this.description = description;
    }

    public LocalDateTime getFechaEntrega() {
        return dueDate;
    }

    public void setFechaEntrega(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getHorasEstimadas() {
        return estimatedHours;
    }

    public void setHorasEstimadas(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public String getHorasReales() {
        return horasReales;
    }

    public void setHorasReales(String horasReales) {
        this.horasReales = horasReales;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getEstado() {
        return state;
    }

    public void setEstado(String state) {
        this.state = state;
    }

    public String getCategoria() {
        return category;
    }

    public void setCategoria(String category) {
        this.category = category;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "ID=" + idTarea +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)) +
                ", estimatedHours=" + estimatedHours +
                ", horasReales='" + horasReales + '\'' +
                ", activo=" + activo +
                ", state='" + state + '\'' +
                ", category='" + category + '\'' +
                ", done=" + done +
                '}';
    }
}