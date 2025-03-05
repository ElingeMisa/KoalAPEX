/*
package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "Equipo_Proyecto")
public class EquipoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipo_proyecto_seq")
    @SequenceGenerator(name = "equipo_proyecto_seq", sequenceName = "Equipo_Proyecto_seq", allocationSize = 1)
    @Column(name = "idEquipo_Proyecto", nullable = false)
    private Integer idEquipoProyecto;

    @ManyToOne
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo", nullable = true)
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idProyecto", nullable = true)
    private Proyecto proyecto;

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public EquipoProyecto() {}

    public EquipoProyecto(Integer idEquipoProyecto, Equipo equipo, Proyecto proyecto, Integer activo) {
        this.idEquipoProyecto = idEquipoProyecto;
        this.equipo = equipo;
        this.proyecto = proyecto;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdEquipoProyecto() {
        return idEquipoProyecto;
    }

    public void setIdEquipoProyecto(Integer idEquipoProyecto) {
        this.idEquipoProyecto = idEquipoProyecto;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
*/