package com.springboot.MyTodoList.data;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Usuarios;

import java.util.List;

/**
 * Repositorio dedicado a las consultas que involucran la relación entre usuarios y equipos
 */
@Repository
@Transactional
public interface UsuarioEquipoDTORepository extends JpaRepository<Object, Integer> {
    
    /**
     * Obtiene la información de usuarios, equipos y roles a partir del token de canal
     * 
     * @param tokenChannel Token de canal del usuario
     * @return Lista de arreglos de objetos con la información solicitada
     */
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
}