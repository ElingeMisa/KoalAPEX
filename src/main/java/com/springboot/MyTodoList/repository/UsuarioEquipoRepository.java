
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.UsuarioEquipo;

@Repository
@Transactional
@EnableTransactionManagement
public interface UsuarioEquipoRepository extends JpaRepository<UsuarioEquipo, Long> {
    
    
}
