
package com.springboot.MyTodoList.model;

// Librerias para persistencia de datos
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 CREATE TABLE TODOUSER.Usuarios (
    idUsuario NUMBER PRIMARY KEY,
    Nombre VARCHAR2(100) NOT NULL,
    Correo VARCHAR2(100) NOT NULL,
    TokenChannel VARCHAR2(100) NULL,
    Telefono VARCHAR2(100) NOT NULL,
    UsuarioT VARCHAR2(100) NULL,
    Activo NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))
);
 */
// Clase Usuarios

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;
    @Column(name = "Nombre")
    String Nombre;
    
    @Column(name = "Correo")
    String Correo;

    @Column(name = "TokenChannel")
    String TokenChannel;

    @Column(name = "Telefono")
    String Telefono;

    @Column(name = "UsuarioT")
    String UsuarioT;

    @Column(name = "Activo")
    int Activo;

    // Constructores
    public Usuarios() {

        this.ID = 0;
        this.Nombre = "usuario";
        this.Correo = "correo@ejemplo.com";
        this.TokenChannel = "token1234";
        this.Telefono = "telefono";
        this.UsuarioT = "usuarioT@telegram";
        this.Activo = 1;

    }

    public Usuarios(int iD, String nombre, String correo, String telefono) {
        
        this.ID = iD;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Telefono = telefono;

    }

    public Usuarios(int iD, String nombre, String correo, String tokenChannel, String telefono, String usuarioT, int activo) {
        
        this.ID = iD;
        this.Nombre = nombre;
        this.Correo = correo;
        this.TokenChannel = tokenChannel;
        this.Telefono = telefono;
        this.UsuarioT = usuarioT;
        this.Activo = activo;

    }
    // Metodos Get y Set
    //#region Get y Set
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        this.ID = iD;
    }

    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String correo) {
        this.Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }
    //#endregion

    @Override
    public String toString() {
        return "Usuarios{" +
                "ID=" + ID +
                ", Nombre='" + Nombre + '\'' +
                ", Correo='" + Correo + '\'' +
                ", TokenChannel='" + TokenChannel + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", UsuarioT='" + UsuarioT + '\'' +
                ", Activo=" + Activo +
                '}';
    }
    
} 
 