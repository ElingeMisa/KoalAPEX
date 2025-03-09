
package com.springboot.MyTodoList.model;

import javax.persistence.*;
/*
    CREATE TABLE "TODOUSER"."DESARROLLADOR" 
   (	"IDDESARROLLADOR" NUMBER, 
	"IDUSUARIO" NUMBER, 
	"MODALIDAD" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT 'Presencial', 
	"CODIGOCHAMBA" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP" DEFAULT '0888880', 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."DESARROLLADOR" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."DESARROLLADOR" ADD PRIMARY KEY ("IDDESARROLLADOR")
    USING INDEX  ENABLE;

    ALTER TABLE "TODOUSER"."DESARROLLADOR" ADD CONSTRAINT "FK_DESARROLLADOR_USUARIO" FOREIGN KEY ("IDUSUARIO")
        REFERENCES "TODOUSER"."USUARIOS" ("IDUSUARIO") ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."DESARROLLADOR_TRG" 
    BEFORE INSERT ON TODOUSER.Desarrollador
    FOR EACH ROW
    BEGIN
        IF :NEW.idDesarrollador IS NULL THEN
            SELECT Desarrollador_seq.NEXTVAL INTO :NEW.idDesarrollador FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."DESARROLLADOR_TRG" ENABLE;
 */
@Entity
@Table(name = "DESARROLLADOR",schema = "TODOUSER")
public class Desarrollador {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desarrollador_seq")
    @SequenceGenerator(name = "desarrollador_seq", sequenceName = "DESARROLLADOR_SEQ", allocationSize = 1)
    @Column(name = "IDDESARROLLADOR", nullable = false)
    private Integer idDesarrollador;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO", foreignKey = @ForeignKey(name = "fk_Desarrollador_Usuario"), nullable = true)
    private Usuarios usuario;

    @Column(name = "MODALIDAD", length = 100)
    private String modalidad = "Presencial";

    @Column(name = "CODIGOCHAMBA", length = 100)
    private String codigoChamba = "0888880";

    @Column(name = "ACTIVO", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Desarrollador() {
        this.idDesarrollador = 0;
        this.usuario = new Usuarios();
        this.modalidad = "Presencial";
        this.codigoChamba = "0888880";
        this.activo = 1;
    }

    public Desarrollador(Integer idDesarrollador, Usuarios usuario, String modalidad, String codigoChamba, Integer activo) {
        this.idDesarrollador = idDesarrollador;
        this.usuario = usuario;
        this.modalidad = modalidad;
        this.codigoChamba = codigoChamba;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdDesarrollador() {
        return idDesarrollador;
    }

    public void setIdDesarrollador(Integer idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getCodigoChamba() {
        return codigoChamba;
    }

    public void setCodigoChamba(String codigoChamba) {
        this.codigoChamba = codigoChamba;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Desarrollador\n{\n\t\t\tidDesarrollador=" + idDesarrollador +
                ", usuario=" + usuario +
                ",\n\t\t\tmodalidad='" + modalidad + '\'' +
                ",\n\t\t\tcodigoChamba='" + codigoChamba + '\'' +
                ",\n\t\t\t activo=" + activo +
                "\n}";
    }
}
