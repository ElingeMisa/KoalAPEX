package com.springboot.MyTodoList.model;
import javax.persistence.*;

/*
 CREATE TABLE "TODOUSER"."USUARIO_EQUIPO" 
   (	"IDUSUARIO_EQUIPO" NUMBER, 
	"IDUSUARIO" NUMBER, 
	"IDEQUIPO" NUMBER, 
	"ROL" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'Desarrollador', 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

ALTER TABLE "TODOUSER"."USUARIO_EQUIPO" ADD CHECK (Activo IN (0,1)) ENABLE;

ALTER TABLE "TODOUSER"."USUARIO_EQUIPO" ADD PRIMARY KEY ("IDUSUARIO_EQUIPO")
  USING INDEX  ENABLE;

ALTER TABLE "TODOUSER"."USUARIO_EQUIPO" ADD CONSTRAINT "FK_USUARIOEQUIPO_USUARIO" FOREIGN KEY ("IDUSUARIO")
	  REFERENCES "TODOUSER"."USUARIOS" ("IDUSUARIO") ENABLE;

ALTER TABLE "TODOUSER"."USUARIO_EQUIPO" ADD CONSTRAINT "FK_USUARIOEQUIPO_EQUIPO" FOREIGN KEY ("IDEQUIPO")
	  REFERENCES "TODOUSER"."EQUIPO" ("IDEQUIPO") ENABLE;

CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."USUARIO_EQUIPO_TRG" 
BEFORE INSERT ON TODOUSER.Usuario_Equipo
FOR EACH ROW
BEGIN
    IF :NEW.idUsuario_equipo IS NULL THEN
        SELECT TODOUSER.Usuario_Equipo_seq.NEXTVAL INTO :NEW.idUsuario_equipo FROM DUAL;
    END IF;
END;

/
ALTER TRIGGER "TODOUSER"."USUARIO_EQUIPO_TRG" ENABLE;
 */


 @Entity
 @Table(name = "Usuario_Equipo", schema = "TODOUSER")
 public class UsuarioEquipo {
 
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_equipo_seq")
     @SequenceGenerator(name = "usuario_equipo_seq", sequenceName = "Usuario_Equipo_seq", allocationSize = 1)
     @Column(name = "idUsuario_equipo", nullable = false)
     private Long idUsuarioEquipo;  // Cambiado de Integer a Long
 
     @ManyToOne
     @JoinColumn(name = "idUsuario", referencedColumnName = "IDUSUARIO", nullable = true)
     private Usuarios usuario;
 
     @ManyToOne
     @JoinColumn(name = "idEquipo", referencedColumnName = "IDEQUIPO", nullable = true)
     private Equipo equipo;
 
     @Column(name = "Rol", length = 100)
     private String rol = "Desarrollador";
 
     @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
     private Integer activo = 1;
 
     // Constructores
     public UsuarioEquipo() {}
 
     public UsuarioEquipo(Long idUsuarioEquipo, Usuarios usuario, Equipo equipo, String rol, Integer activo) {
         this.idUsuarioEquipo = idUsuarioEquipo;
         this.usuario = usuario;
         this.equipo = equipo;
         this.rol = rol;
         this.activo = activo;
     }
 
     // Getters y Setters actualizados
     public Long getIdUsuarioEquipo() {
         return idUsuarioEquipo;
     }
 
     public void setIdUsuarioEquipo(Long idUsuarioEquipo) {
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