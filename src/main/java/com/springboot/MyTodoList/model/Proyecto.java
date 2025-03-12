package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.util.Date;

/*
 CREATE TABLE "TODOUSER"."PROYECTO" 
   (	"IDPROYECTO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"FECHACREACION" DATE, 
	"DESCRIPCION" CLOB COLLATE "USING_NLS_COMP", 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."PROYECTO" MODIFY ("NOMBRE" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."PROYECTO" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."PROYECTO" ADD PRIMARY KEY ("IDPROYECTO")
    USING INDEX  ENABLE;
 */

 
@Entity
@Table(name = "Proyecto", schema = "TODOUSER")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proyecto_seq")
    @SequenceGenerator(name = "proyecto_seq", sequenceName = "Proyecto_seq", allocationSize = 1)
    @Column(name = "IDPROYECTO", nullable = false)
    private Long idProyecto;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "FECHACREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Lob
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Proyecto(
    ) {
        this.idProyecto = (long) 0;
        this.nombre = "Proyecto";
        this.fechaCreacion = new Date();
        this.descripcion = "Descripcion";
        this.activo = 1;
    }

    public Proyecto(Long idProyecto, String nombre, Date fechaCreacion, String descripcion, Integer activo) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto=" + idProyecto +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                '}';
    }
}