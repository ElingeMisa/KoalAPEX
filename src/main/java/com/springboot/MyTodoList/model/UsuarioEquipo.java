/* 
package com.springboot.MyTodoList.model;

import javax.persistence.*;

@Entity
@Table(name = "Usuario_Equipo")
public class UsuarioEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_equipo_seq")
    @SequenceGenerator(name = "usuario_equipo_seq", sequenceName = "Usuario_Equipo_seq", allocationSize = 1)
    @Column(name = "idUsuario_equipo", nullable = false)
    private Integer idUsuarioEquipo;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "ID", nullable = true)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo", nullable = true)
    private Equipo equipo;

    @Column(name = "Rol", length = 100)
    private String rol = "Desarrollador";

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public UsuarioEquipo() {}

    public UsuarioEquipo(Integer idUsuarioEquipo, Usuarios usuario, Equipo equipo, String rol, Integer activo) {
        this.idUsuarioEquipo = idUsuarioEquipo;
        this.usuario = usuario;
        this.equipo = equipo;
        this.rol = rol;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdUsuarioEquipo() {
        return idUsuarioEquipo;
    }

    public void setIdUsuarioEquipo(Integer idUsuarioEquipo) {
        this.idUsuarioEquipo = idUsuarioEquipo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}
*/