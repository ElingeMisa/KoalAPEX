
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Manager;
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

@Repository
@Transactional
@EnableTransactionManagement
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    
    @Query(value = "SELECT m.* FROM TODOUSER.MANAGER m WHERE m.IDUSUARIO = :idUsuario", nativeQuery = true)
    List<Manager> findByIdUsuario(@Param("idUsuario") long idUsuario);
    
}
