
package com.springboot.MyTodoList.repository;

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
}
