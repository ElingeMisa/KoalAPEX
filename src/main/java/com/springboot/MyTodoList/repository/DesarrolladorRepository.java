
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.springboot.MyTodoList.model.Desarrollador;

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

@Repository
@Transactional
@EnableTransactionManagement
public interface DesarrolladorRepository extends JpaRepository<Desarrollador, Integer> {

    // Método corregido: Buscar por el ID de usuario - usando correctamente la relación
    @Query("SELECT d FROM Desarrollador d WHERE d.usuario.idUsuario = :idUsuario")
    List<Desarrollador> findByIdUsuario(@Param("idUsuario") long idUsuario);
    
    // Método 2: Con la convención de nombres de Spring Data (alternativa al método anterior)
    List<Desarrollador> findByUsuario_IdUsuario(Integer idUsuario);
    
    // Método 3: Buscar por tokenChannel 
    @Query("SELECT d FROM Desarrollador d JOIN d.usuario u WHERE u.tokenChannel = :tokenChannel")
    List<Desarrollador> findByTokenChannel(@Param("tokenChannel") String tokenChannel);
    
    // Método 4: Buscar por modalidad
    List<Desarrollador> findByModalidad(String modalidad);
    
    // Método 5: Buscar activos
    List<Desarrollador> findByActivoEquals(Integer activo);
    
    // Método 6: Buscar por código chamba
    List<Desarrollador> findByCodigoChamba(String codigoChamba);
}
