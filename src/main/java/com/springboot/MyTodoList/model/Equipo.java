package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 CREATE TABLE "TODOUSER"."EQUIPO" 
   (	"IDEQUIPO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"NOTIFICACION" TIMESTAMP (6), 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."EQUIPO" MODIFY ("NOMBRE" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."EQUIPO" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."EQUIPO" ADD PRIMARY KEY ("IDEQUIPO")
    USING INDEX  ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."EQUIPO_TRG" 
    BEFORE INSERT ON TODOUSER.Equipo
    FOR EACH ROW
    BEGIN
        IF :NEW.idEquipo IS NULL THEN
            SELECT TODOUSER.Equipo_seq.NEXTVAL INTO :NEW.idEquipo FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."EQUIPO_TRG" ENABLE;
 */

 
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
