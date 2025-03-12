
package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 CREATE TABLE "TODOUSER"."SPRINT" 
   (	"IDSPRINT" NUMBER, 
	"IDPROYECTO" NUMBER, 
	"FECHAINICIO" TIMESTAMP (6), 
	"FECHAFIN" TIMESTAMP (6), 
	"NOMBRE" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'SprintProyecto', 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."SPRINT" MODIFY ("FECHAINICIO" NOT NULL ENABLE);

    ALTER TABLE "TODOUSER"."SPRINT" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."SPRINT" ADD PRIMARY KEY ("IDSPRINT")
    USING INDEX  ENABLE;

    ALTER TABLE "TODOUSER"."SPRINT" ADD CONSTRAINT "FK_SPRINT_PROYECTO" FOREIGN KEY ("IDPROYECTO")
        REFERENCES "TODOUSER"."PROYECTO" ("IDPROYECTO") ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."SPRINT_TRG" 
    BEFORE INSERT ON TODOUSER.Sprint
    FOR EACH ROW
    BEGIN
        IF :NEW.idSprint IS NULL THEN
            SELECT Sprint_seq.NEXTVAL INTO :NEW.idSprint FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."SPRINT_TRG" ENABLE;
 */

 
 @Entity
 @Table(name = "SPRINT", schema = "TODOUSER")
 public class Sprint {
 
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprint_seq")
     @SequenceGenerator(name = "sprint_seq", sequenceName = "Sprint_seq", allocationSize = 1)
     @Column(name = "IDSPRINT")
     private Integer idSprint;
 
     @ManyToOne
     @JoinColumn(name = "IDPROYECTO", referencedColumnName = "IDPROYECTO")
     private Proyecto proyecto;
 
     @Column(name = "FECHAINICIO", nullable = false)
     private LocalDateTime fechaInicio;
 
     @Column(name = "FECHAFIN")
     private LocalDateTime fechaFin;
 
     @Column(name = "NOMBRE", length = 100)
     private String nombre = "SprintProyecto";
 
     @Column(name = "ACTIVO")
     private Integer activo = 1;
 
     // Constructores
     public Sprint() {
         this.idSprint = 0;
         this.proyecto = new Proyecto();
         this.fechaInicio = LocalDateTime.now();
         this.fechaFin = LocalDateTime.now();
         this.nombre = "SprintProyecto";
         this.activo = 1;
     }
 
     public Sprint(Integer idSprint, Proyecto proyecto, LocalDateTime fechaInicio, LocalDateTime fechaFin, String nombre, Integer activo) {
         this.idSprint = idSprint;
         this.proyecto = proyecto;
         this.fechaInicio = fechaInicio;
         this.fechaFin = fechaFin;
         this.nombre = nombre;
         this.activo = activo;
     }
 
     // Getters y Setters
     public Integer getIdSprint() {
         return idSprint;
     }
 
     public void setIdSprint(Integer idSprint) {
         this.idSprint = idSprint;
     }
 
     public Proyecto getProyecto() {
         return proyecto;
     }
 
     public void setProyecto(Proyecto proyecto) {
         this.proyecto = proyecto;
     }
 
     public LocalDateTime getFechaInicio() {
         return fechaInicio;
     }
 
     public void setFechaInicio(LocalDateTime fechaInicio) {
         this.fechaInicio = fechaInicio;
     }
 
     public LocalDateTime getFechaFin() {
         return fechaFin;
     }
 
     public void setFechaFin(LocalDateTime fechaFin) {
         this.fechaFin = fechaFin;
     }
 
     public String getNombre() {
         return nombre;
     }
 
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
 
     public Integer getActivo() {
         return activo;
     }
 
     public void setActivo(Integer activo) {
         this.activo = activo;
     }

     @Override
        public String toString() {
            return "Sprint{" +
                    "idSprint=" + idSprint +
                    ", proyecto=" + proyecto.getNombre() +
                    ", fechaInicio=" + fechaInicio +
                    ", fechaFin=" + fechaFin +
                    ", nombre='" + nombre + '\'' +
                    ", activo=" + activo +
                    '}';
        }
 }