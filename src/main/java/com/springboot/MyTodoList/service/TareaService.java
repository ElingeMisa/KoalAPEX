
package com.springboot.MyTodoList.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.repository.TareaRepository;
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
@Service
public class TareaService {
    
    private final TareaRepository tareaRepository;

    @Autowired
    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }
    
    public List<Tarea> findAll() {
        return tareaRepository.findAll();
    }

    public Tarea findById(int id) {
        Optional<Tarea> tarea = tareaRepository.findById(id);
        if (tarea.isPresent()) {
            return tarea.get();
        }
        return null;
    }

    public Tarea addTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Transactional
    public void agregarTarea(Tarea tarea) {
        // Asegurar que los objetos relacionados tengan sus IDs correctos
        Integer idDesarrollador = null;
        Number idProyecto = null;
        Integer idSprint = null;
        
        if (tarea.getDesarrollador() != null) {
            idDesarrollador = tarea.getDesarrollador().getIdDesarrollador();
        } else {
            throw new IllegalArgumentException("El desarrollador no puede ser nulo para crear una tarea");
        }
        
        if (tarea.getProyecto() != null) {
            // Convertimos explícitamente Long a Number para evitar problemas de tipado con Oracle
            idProyecto = new BigDecimal(tarea.getProyecto().getIdProyecto());
        } else {
            throw new IllegalArgumentException("El proyecto no puede ser nulo para crear una tarea");
        }
        
        if (tarea.getSprint() != null) {
            idSprint = tarea.getSprint().getIdSprint();
        }
        
        try {
            // Dejamos que el trigger de la base de datos genere el ID
            tareaRepository.agregarTarea(
                tarea.getDescripcion(),
                tarea.getFechaEntrega(),
                tarea.getHorasEstimadas(),
                tarea.getHorasReales(),
                tarea.getActivo(),
                tarea.getEstado(),
                tarea.getCategoria(),
                idDesarrollador,
                idProyecto,
                idSprint
            );
        } catch (Exception e) {
            // Log detailed error information
            System.err.println("Error al agregar tarea: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to maintain transaction behavior
        }
    }

    public Tarea updateTarea(int id, Tarea tarea) {
        Optional<Tarea> tareaOptional = tareaRepository.findById(id);
        if (tareaOptional.isPresent()) {
            Tarea tareaToUpdate = tareaOptional.get();
            tareaToUpdate.setDescripcion(tarea.getDescripcion());
            tareaToUpdate.setFechaEntrega(tarea.getFechaEntrega());
            tareaToUpdate.setHorasEstimadas(tarea.getHorasEstimadas());
            tareaToUpdate.setHorasReales(tarea.getHorasReales());
            tareaToUpdate.setActivo(tarea.getActivo());
            tareaToUpdate.setEstado(tarea.getEstado());
            tareaToUpdate.setCategoria(tarea.getCategoria());
            tareaToUpdate.setDesarrollador(tarea.getDesarrollador());
            tareaToUpdate.setProyecto(tarea.getProyecto());
            tareaToUpdate.setSprint(tarea.getSprint());
            return tareaRepository.save(tareaToUpdate);
        }
        return null;
    }

    public List<Tarea> findByTokenChannel(String tokenChannel) {
        List<Tarea> tareas = tareaRepository.findByTokenChannel(tokenChannel);
        if (tareas == null || tareas.isEmpty()) {
            tareas = new ArrayList<>();
            tareas.add(new Tarea());
        }
        return tareas;
    }

    public List<Tarea> findByIdDesarrollador(int idDesarrollador) {
        return tareaRepository.findByIdDesarrollador(idDesarrollador);
    }

    public ResponseEntity<HttpStatus> deleteTarea(int id) {
        tareaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}