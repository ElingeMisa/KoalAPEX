
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Tarea;

import oracle.security.o3logon.a;

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

@Repository
@Transactional
@EnableTransactionManagement
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    
/*
 * 
SELECT * from TODOUSER.TAREA t WHERE t.IDDESARROLLADOR = (SELECT IDDESARROLLADOR FROM TODOUSER.DESARROLLADOR d WHERE d.IDUSUARIO = (SELECT IDUSUARIO FROM TODOUSER.USUARIOS u WHERE u.TOKENCHANNEL =7714149514 ));
 */
    // Solución 1: Consulta JPQL con nombres de entidad correctos
    @Query("SELECT t FROM Tarea t " +
    "JOIN FETCH t.desarrollador d " +
    "JOIN d.usuario u " +
    "WHERE u.tokenChannel = :tokenChannel")
    List<Tarea> findByTokenChannel(@Param("tokenChannel") String tokenChannel);

    // Busca todas ls tareas que tengan un idDesarrollador.
    @Query(value = "SELECT * FROM TODOUSER.TAREA t WHERE t.IDDESARROLLADOR = :idDesarrollador", nativeQuery = true)
    List<Tarea> findByIdDesarrollador(@Param("idDesarrollador") Integer idDesarrollador);


    /* 
    @Query(value = "SELECT t.* FROM TODOUSER.TAREA t " +
       "WHERE t.IDDESARROLLADOR = " +
       "(SELECT d.IDDESARROLLADOR FROM TODOUSER.DESARROLLADOR d " +
       "WHERE d.IDUSUARIO = " +
       "(SELECT u.IDUSUARIO FROM TODOUSER.USUARIOS u " +
       "WHERE u.TOKENCHANNEL = :tokenChannel))", 
       nativeQuery = true)
List<Tarea> findByTokenChannel(@Param("tokenChannel") String tokenChannel);
    */
}
