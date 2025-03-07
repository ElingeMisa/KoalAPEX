/* 
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
    
    /**
     * Obtiene la información de usuarios, equipos y roles a partir del token de canal
     * 
     * @param tokenChannel Token de canal del usuario
     * @return Lista de arreglos de objetos con la información solicitada
     
    @Query(value = "SELECT u.NOMBRE AS \"Usuario\", " +
                  "e.IDEQUIPO, " +
                  "e.NOMBRE AS \"Equipo\", " +
                  "ue.ROL AS \"Roll\" " +
                  "FROM TODOUSER.USUARIO_EQUIPO ue " +
                  "LEFT JOIN TODOUSER.USUARIOS u ON u.IDUSUARIO = ue.IDUSUARIO " +
                  "LEFT JOIN TODOUSER.EQUIPO e ON e.IDEQUIPO = ue.IDEQUIPO " +
                  "WHERE u.TOKENCHANNEL = :tokenChannel", 
            nativeQuery = true)
    List<Object[]> findUsuariosEquiposByTokenChannel(@Param("tokenChannel") String tokenChannel);
    
    // Método para encontrar todas las asignaciones de un usuario por su ID
    @SuppressWarnings("rawtypes")
    @Query("SELECT ue FROM UsuarioEquipo ue WHERE ue.idUsuario = :idUsuario")
    List<UsuarioEquipo> findByIdUsuario(@Param("idUsuario") Long idUsuario);
    
    // Método para encontrar todas las asignaciones a un equipo por su ID
    @SuppressWarnings("rawtypes")
    @Query("SELECT ue FROM UsuarioEquipo ue WHERE ue.idEquipo = :idEquipo")
    List<UsuarioEquipo> findByIdEquipo(@Param("idEquipo") Long idEquipo);
}
*/