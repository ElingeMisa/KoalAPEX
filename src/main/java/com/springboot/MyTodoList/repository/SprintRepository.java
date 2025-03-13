
package com.springboot.MyTodoList.repository;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Sprint;

@Repository
@Transactional
@EnableTransactionManagement
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    
    // SELECT s.* FROM TODOUSER.SPRINT s WHERE s.IDPROYECTO = 4;
    @Query("SELECT s FROM Sprint s WHERE s.proyecto.id = :id")
    List<Sprint> findByIdProyecto(@Param("id") Long id);

    // SELECT s.* FROM TODOUSER.SPRINT s WHERE s.IDPROYECTO = 4  AND s.FECHAINICIO < TO_DATE('12/03/2025', 'DD/MM/YYYY')  ORDER BY s.FECHAINICIO DESC FETCH FIRST 1 ROW ONLY;
    @Query(value = "SELECT s.* FROM SPRINT s WHERE s.IDPROYECTO = :id AND s.FECHAINICIO < :fechaEntrega ORDER BY s.FECHAINICIO DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
    Sprint getClosest(@Param("fechaEntrega") String fechaEntrega, @Param("id") Long id);
    
    @Query("SELECT s FROM Sprint s WHERE s.proyecto.id = :id AND s.fechaInicio < :fechaEntrega ORDER BY s.fechaInicio DESC")
    List<Sprint> getClosestByTimestamp(@Param("fechaEntrega") LocalDateTime fechaEntrega, @Param("id") Long id);

    //SELECT s.* FROM TODOUSER.SPRINT s WHERE s.IDSPRINT = 11
    @Query("SELECT s FROM Sprint s WHERE s.idSprint = :id")
    Sprint findByIdSprint(@Param("id") Long id);

    List<Sprint> findAll();

}
