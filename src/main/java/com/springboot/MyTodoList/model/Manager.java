 package com.springboot.MyTodoList.model;

import javax.persistence.*;

/*
 CREATE TABLE "TODOUSER"."MANAGER" 
   (	"IDMANAGE" NUMBER, 
	"IDUSUARIO" NUMBER, 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

    ALTER TABLE "TODOUSER"."MANAGER" ADD CHECK (Activo IN (0,1)) ENABLE;

    ALTER TABLE "TODOUSER"."MANAGER" ADD PRIMARY KEY ("IDMANAGE")
    USING INDEX  ENABLE;

    ALTER TABLE "TODOUSER"."MANAGER" ADD CONSTRAINT "FK_MANAGER_USUARIO" FOREIGN KEY ("IDUSUARIO")
        REFERENCES "TODOUSER"."USUARIOS" ("IDUSUARIO") ENABLE;

    CREATE OR REPLACE EDITIONABLE TRIGGER "TODOUSER"."MANAGER_TRG" 
    BEFORE INSERT ON TODOUSER.Manager
    FOR EACH ROW
    BEGIN
        IF :NEW.idManage IS NULL THEN
            SELECT Manager_seq.NEXTVAL INTO :NEW.idManage FROM DUAL;
        END IF;
    END;

    /
    ALTER TRIGGER "TODOUSER"."MANAGER_TRG" ENABLE;
 */

@Entity
@Table(name = "Manager", schema = "TODOUSER")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_seq")
    @SequenceGenerator(name = "manager_seq", sequenceName = "Manager_seq", allocationSize = 1)
    @Column(name = "idManage", nullable = false)
    private Integer idManage;

    @ManyToOne
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO", foreignKey =  @ForeignKey(name="fk_Manager_Usuario"), nullable = true)
    private Usuarios usuario;

    @Column(name = "Activo", columnDefinition = "NUMBER(1) DEFAULT 1 CHECK (Activo IN (0,1))")
    private Integer activo = 1;

    // Constructores
    public Manager() {
        this.activo = 1;
        this.usuario = new Usuarios();
        this.idManage = 0;
    }

    public Manager(Integer idManage, Usuarios usuario, Integer activo) {
        this.idManage = idManage;
        this.usuario = usuario;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdManage() {
        return idManage;
    }

    public void setIdManage(Integer idManage) {
        this.idManage = idManage;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}

