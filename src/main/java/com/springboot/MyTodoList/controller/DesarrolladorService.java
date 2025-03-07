
package com.springboot.MyTodoList.controller;

import java.util.List;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.repository.DesarrolladorRepository;

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
@Service
public class DesarrolladorService {

    private final DesarrolladorRepository desarrolladorRepository;

    @Autowired
    public DesarrolladorService(DesarrolladorRepository desarrolladorRepository) {
        this.desarrolladorRepository = desarrolladorRepository;
    }

    List<Desarrollador> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    Desarrollador findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    Desarrollador addDesarrollador(Desarrollador desarrollador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addDesarrollador'");
    }

    Desarrollador updateDesarrollador(int id, Desarrollador desarrollador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDesarrollador'");
    }

    Boolean deleteDesarrollador(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteDesarrollador'");
    }

    public List<Desarrollador> findByIdUsuario(long idUsuario){
        // TODO Auto-generated method stub
        List<Desarrollador> desarrollador = desarrolladorRepository.findByIdUsuario(idUsuario);
        return desarrollador;
    }
}
