package com.springboot.MyTodoList.data;

public class UsuarioEquipoDTO {
    private String nombreUsuario;
    private Long idEquipo;
    private String nombreEquipo;
    private String rol;

    // Constructor
    public UsuarioEquipoDTO(String nombreUsuario, Long idEquipo, String nombreEquipo, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.rol = rol;
    }

    // Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario +
               "\nEquipo ID: " + idEquipo +
               "\nNombre del Equipo: " + nombreEquipo +
               "\nRol: " + rol + "\n";
    }
}
