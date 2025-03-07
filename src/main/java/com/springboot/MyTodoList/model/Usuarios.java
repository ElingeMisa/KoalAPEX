
package com.springboot.MyTodoList.model;

// Librerias para persistencia de datos
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
    CREATE TABLE "TODOUSER"."USUARIOS" 
    (	"IDUSUARIO" NUMBER, 
        "NOMBRE" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
        "CORREO" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
        "TOKENCHANNEL" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
        "TELEFONO" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
        "USUARIOT" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
        "ACTIVO" NUMBER(1,0) DEFAULT 1
    )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("NOMBRE" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("CORREO" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("TELEFONO" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."USUARIOS" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."USUARIOS" ADD PRIMARY KEY ("IDUSUARIO")
    USING INDEX  ENABLE;

 */
// Clase Usuarios


@Entity
@Table(name = "USUARIOS", schema = "TODOUSER")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @Column(name = "IDUSUARIO")
    private Long idUsuario;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CORREO")
    private String correo;

    @Column(name = "TOKENCHANNEL")
    private String tokenChannel;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "USUARIOT")
    private String usuarioT;

    @Column(name = "ACTIVO")
    private int activo;

    // Constructores
    public Usuarios() {
        this.idUsuario = 0L;
        this.nombre = "usuario";
        this.correo = "correo@ejemplo.com";
        this.tokenChannel = "token1234";
        this.telefono = "telefono";
        this.usuarioT = "usuarioT@telegram";
        this.activo = 1;
    }

    public Usuarios(long id, String nombre, String correo, String telefono) {
        this.idUsuario = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Usuarios(long id, String nombre, String correo, String tokenChannel, String telefono, String usuarioT, int activo) {
        this.idUsuario = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tokenChannel = tokenChannel;
        this.telefono = telefono;
        this.usuarioT = usuarioT;
        this.activo = activo;
    }
    
    // Getters y Setters
    public long getId() {
        return this.idUsuario;
    }
    
    public void setId(long id) {
        this.idUsuario = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getTokenChannel() {
        return tokenChannel;
    }
    
    public void setTokenChannel(String tokenChannel) {
        this.tokenChannel = tokenChannel;
    }

    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getUsuarioT() {
        return usuarioT;
    }
    
    public void setUsuarioT(String usuarioT) {
        this.usuarioT = usuarioT;
    }
    
    public int getActivo() {
        return activo;
    }
    
    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuarios\n{\n\t\t\tid=" + idUsuario +
                ",\n\t\t\tnombre='" + nombre + '\'' +
                ",\n\t\t\tncorreo='" + correo + '\'' +
                ",\n\t\t\ttokenChannel='" + tokenChannel + '\'' +
                ",\n\t\t\ttelefono='" + telefono + '\'' +
                ",\n\t\t\tusuarioT='" + usuarioT + '\'' +
                ",\n\t\t\tactivo=" + activo +
                '\n' + '}';
    }
}