package com.springboot.MyTodoList.model;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
 CREATE TABLE "TODOUSER"."TAREA" 
   (	"IDTAREA" NUMBER, 
	"DESCRIPCION" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"FECHAENTREGA" TIMESTAMP (6), 
	"HORAS_ESTIMADAS" NUMBER DEFAULT 2, 
	"HORAS_REALES" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT '2', 
	"ACTIVO" NUMBER(1,0) DEFAULT 1, 
	"ESTADO" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'Activo', 
	"CATEGORIA" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'Tarea', 
	"IDDESARROLLADOR" NUMBER, 
	"IDPROYECTO" NUMBER, 
	"IDSPRINT" NUMBER
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."TAREA" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."TAREA" ADD PRIMARY KEY ("IDTAREA")
    USING INDEX  ENABLE;

    ALTER TABLE "TODOUSER"."TAREA" ADD CONSTRAINT "FK_TAREA_DESARROLLADOR" FOREIGN KEY ("IDDESARROLLADOR")
        REFERENCES "TODOUSER"."DESARROLLADOR" ("IDDESARROLLADOR") ENABLE;

    ALTER TABLE "TODOUSER"."TAREA" ADD CONSTRAINT "FK_TAREA_PROYECTO" FOREIGN KEY ("IDPROYECTO")
        REFERENCES "TODOUSER"."PROYECTO" ("IDPROYECTO") ENABLE;

    ALTER TABLE "TODOUSER"."TAREA" ADD CONSTRAINT "FK_TAREA_SPRINT" FOREIGN KEY ("IDSPRINT")
        REFERENCES "TODOUSER"."SPRINT" ("IDSPRINT") ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."TAREA_TRG" 
    BEFORE INSERT ON TODOUSER.Tarea
    FOR EACH ROW
    BEGIN
        IF :NEW.idTarea IS NULL THEN
            SELECT Tarea_seq.NEXTVAL INTO :NEW.idTarea FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."TAREA_TRG" ENABLE;
 */

 
@Entity
@Table(name = "Tarea", schema = "TODOUSER")
public class Tarea {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tarea_seq")
    @SequenceGenerator(name = "tarea_seq", sequenceName = "Tarea_seq", allocationSize = 1)
    @Column(name = "IDTAREA", nullable = false)
    private Integer idTarea;

    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    @Column(name = "FECHAENTREGA")
    private LocalDateTime fechaEntrega;

    @Column(name = "HORAS_ESTIMADAS", columnDefinition = "NUMBER DEFAULT 2")
    private Integer horasEstimadas = 2;

    @Column(name = "HORAS_REALES", length = 100)
    private String horasReales = "2";

    @Column(name = "ACTIVO", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    @Column(name = "ESTADO", length = 100)
    private String estado = "Activo";

    @Column(name = "CATEGORIA", length = 100)
    private String categoria = "Tarea";

    @ManyToOne
    @JoinColumn(name = "IDDESARROLLADOR", referencedColumnName = "IDDESARROLLADOR", nullable = true,foreignKey = @ForeignKey(name = "FK_TAREA_DESARROLLADOR"))
    private Desarrollador desarrollador;

    @ManyToOne
    @JoinColumn(name = "IDPROYECTO", referencedColumnName = "IDPROYECTO")
    private Proyecto proyecto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IDSPRINT", referencedColumnName = "IDSPRINT")
    private Sprint sprint;

    // Constructores
    public Tarea() {
        this.activo = 1;
        this.estado = "Activo";
        this.categoria = "Tarea";
        this.horasEstimadas = 2;
        this.horasReales = "2";
        this.fechaEntrega = LocalDateTime.now();
        this.desarrollador = new Desarrollador();
        this.proyecto = new Proyecto();
        this.sprint = new Sprint();
        this.idTarea = 0;
        this.descripcion = "Descripcion";
    }

    public Tarea(Integer idTarea, String descripcion, LocalDateTime fechaEntrega, Integer horasEstimadas, String horasReales,
                 Integer activo, String estado, String categoria, Desarrollador desarrollador, Proyecto proyecto, Sprint sprint) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horasEstimadas = horasEstimadas;
        this.horasReales = horasReales;
        this.activo = activo;
        this.estado = estado;
        this.categoria = categoria;
        this.desarrollador = desarrollador;
        this.proyecto = proyecto;
        this.sprint = sprint;
    }

    // Getters y Setters
    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getHorasEstimadas() {
        return horasEstimadas;
    }

    public void setHorasEstimadas(Integer horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }

    public String getHorasReales() {
        return horasReales;
    }

    public void setHorasReales(String horasReales) {
        this.horasReales = horasReales;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Desarrollador getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(Desarrollador desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Boolean isActivo() {
        return estado.equals("Activa"); 
    }
    
    @Override
    public String toString() {
        return "Tarea{" +
                "idTarea=" + idTarea +
                ", descripcion='" + descripcion + '\'' +
                "}\n";
    }
}
