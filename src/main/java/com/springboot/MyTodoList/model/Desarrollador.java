
package com.springboot.MyTodoList.model;

import javax.persistence.*;
/*
    -- Crear la tabla Desarrollador
    CREATE TABLE TODOUSER.Desarrollador (
        idDesarrollador NUMBER PRIMARY KEY,
        idUsuario NUMBER NULL,
        Modalidad VARCHAR2(100) DEFAULT 'Presencial',
        CodigoChamba VARCHAR2(100) DEFAULT '0888880',
        Activo NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1)),
        CONSTRAINT fk_Desarrollador_Usuario FOREIGN KEY (idUsuario) REFERENCES TODOUSER.Usuarios(idUsuario)
    );

    CREATE SEQUENCE TODOUSER.Desarrollador_seq START WITH 1 INCREMENT BY 1;
    CREATE OR REPLACE TRIGGER TODOUSER.Desarrollador_trg
    BEFORE INSERT ON TODOUSER.Desarrollador
    FOR EACH ROW
    BEGIN
        IF :NEW.idDesarrollador IS NULL THEN
            SELECT Desarrollador_seq.NEXTVAL INTO :NEW.idDesarrollador FROM DUAL;
        END IF;
    END;
    /
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
    public Desarrollador() {}

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
