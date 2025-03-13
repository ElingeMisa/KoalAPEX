
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Proyecto;

@Repository
@Transactional
@EnableTransactionManagement
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    // Busca todos los proyects con un nombre en especifico
    List<Proyecto> findByNombre(String nombre);

    // SELECT p.* FROM TODOUSER.PROYECTO p LEFT JOIN TODOUSER.EQUIPO_PROYECTO ep ON ep.IDPROYECTO = p.IDPROYECTO left JOIN TODOUSER.EQUIPO e ON e.IDEQUIPO = ep.IDEQUIPO WHERE e.IDEQUIPO = 3
    @Query("SELECT p FROM Proyecto p LEFT JOIN EquipoProyecto ep ON ep.proyecto.id = p.id LEFT JOIN Equipo e ON e.id = ep.equipo.id WHERE e.id = :id")
    List<Proyecto> findByEquipoId(@Param("id") Long id);
    // Busca todos los 
}

