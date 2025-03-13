
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.springboot.MyTodoList.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@Repository
@Transactional
@EnableTransactionManagement
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query("SELECT e FROM Equipo e WHERE e.activo = 1")
    List<Equipo> findAllActive();

    @Query("SELECT e FROM Equipo e WHERE e.activo = 0")
    List<Equipo> findAllInactive();

    @Query("SELECT e FROM Equipo e WHERE e.nombre = :nombre")
    Equipo findByNombre(@Param("nombre") String nombre);

    @Query("SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo")
    Equipo findByIdEquipo(@Param("idEquipo") Long idEquipo);

    //Select e.* FROM TODOUSER.EQUIPO e LEFT JOIN TODOUSER.USUARIO_EQUIPO ue ON ue.IDEQUIPO = e.IDEQUIPO LEFT JOIN TODOUSER.USUARIOS u ON u.IDUSUARIO = ue.IDUSUARIO WHERE u.IDUSUARIO = :idUsuario;
    @Query("SELECT e FROM Equipo e JOIN e.usuarioEquipos ue JOIN ue.usuario u WHERE u.idUsuario = :idUsuario")
    List<Equipo> findByIdUsuario(@Param("idUsuario") Long idUsuario);


}

