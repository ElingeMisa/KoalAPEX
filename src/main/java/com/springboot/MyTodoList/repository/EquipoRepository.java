
package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.springboot.MyTodoList.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@Transactional
@EnableTransactionManagement
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    @Query("SELECT e FROM Equipo e JOIN e.usuarioEquipos ue WHERE ue.usuario.idUsuario = :idUsuario")
    List<Equipo> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}

