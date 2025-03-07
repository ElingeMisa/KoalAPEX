/* 
package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EQUIPO", schema = "TODOUSER")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipo_seq")
    @SequenceGenerator(name = "equipo_seq", sequenceName = "Equipo_seq", allocationSize = 1)
    @Column(name = "IDEQUIPO", nullable = false)
    private Long idEquipo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "NOTIFICACION")
    private LocalDateTime notificacion;

    @Column(name = "ACTIVO", nullable = false)
    private Integer activo = 1;

    // Constructores
    public Equipo() {
    }

    public Equipo(String nombre, LocalDateTime notificacion, Integer activo) {
        this.nombre = nombre;
        this.notificacion = notificacion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(LocalDateTime notificacion) {
        this.notificacion = notificacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
    
/* */