
package com.springboot.MyTodoList.model;

import javax.persistence.*;
/*
 CREATE TABLE "TODOUSER"."EQUIPO_PROYECTO" 
   (	"IDEQUIPO_PROYECTO" NUMBER, 
	"IDEQUIPO" NUMBER, 
	"IDPROYECTO" NUMBER, 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."EQUIPO_PROYECTO" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."EQUIPO_PROYECTO" ADD PRIMARY KEY ("IDEQUIPO_PROYECTO")
    USING INDEX  ENABLE;

    ALTER TABLE "TODOUSER"."EQUIPO_PROYECTO" ADD CONSTRAINT "FK_EQUIPOPROYECTO_EQUIPO" FOREIGN KEY ("IDEQUIPO")
        REFERENCES "TODOUSER"."EQUIPO" ("IDEQUIPO") ENABLE;

    ALTER TABLE "TODOUSER"."EQUIPO_PROYECTO" ADD CONSTRAINT "FK_EQUIPOPROYECTO_PROYECTO" FOREIGN KEY ("IDPROYECTO")
        REFERENCES "TODOUSER"."PROYECTO" ("IDPROYECTO") ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."EQUIPO_PROYECTO_TRG" 
    BEFORE INSERT ON TODOUSER.Equipo_Proyecto
    FOR EACH ROW
    BEGIN
        IF :NEW.idEquipo_Proyecto IS NULL THEN
            SELECT Equipo_Proyecto_seq.NEXTVAL INTO :NEW.idEquipo_Proyecto FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."EQUIPO_PROYECTO_TRG" ENABLE;
 */

@Entity
@Table(name = "Equipo_Proyecto",schema = "TODOUSER")
public class EquipoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipo_proyecto_seq")
    @SequenceGenerator(name = "equipo_proyecto_seq", sequenceName = "Equipo_Proyecto_seq", allocationSize = 1)
    @Column(name = "idEquipo_Proyecto", nullable = false)
    private Integer idEquipoProyecto;

    @ManyToOne
    @JoinColumn(name = "IDEQUIPO", referencedColumnName = "IDEQUIPO", nullable = true)
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "IDPROYECTO", referencedColumnName = "IDPROYECTO", nullable = true)
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
