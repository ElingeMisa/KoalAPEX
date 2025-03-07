package com.springboot.MyTodoList.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.springboot.MyTodoList.model.Usuarios;

/*
CREATE TABLE "TODOUSER"."USUARIOS" 
   (	"IDUSUARIO" NUMBER, 
	"NOMBRE" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"CORREO" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"TOKENCHANNEL" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"TELEFONO" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"USUARIOT" VARCHAR2(100 BYTE) COLLATE "USING_NLS_COMP", 
	"ACTIVO" NUMBER(1,0) DEFAULT 1
   )  DEFAULT COLLATION "USING_NLS_COMP" ;

ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("NOMBRE" NOT NULL ENABLE);

ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("CORREO" NOT NULL ENABLE);

ALTER TABLE "TODOUSER"."USUARIOS" MODIFY ("TELEFONO" NOT NULL ENABLE);

ALTER TABLE "TODOUSER"."USUARIOS" ADD CHECK (Activo IN (0,1)) ENABLE;

ALTER TABLE "TODOUSER"."USUARIOS" ADD PRIMARY KEY ("IDUSUARIO")
  USING INDEX  ENABLE;

 */
@Repository
@Transactional
@EnableTransactionManagement
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    
    // MÉTODOS CON NOMBRES QUE COINCIDEN EXACTAMENTE CON LOS DE LA ENTIDAD
    @Query("SELECT u FROM Usuarios u WHERE u.tokenChannel = :tokenChannel")
    List<Usuarios> findByTokenChannel(@Param("tokenChannel") String tokenChannel);

    @Query("SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    List<Usuarios> findByNombreContaining(String nombre);

    @Query("SELECT u FROM Usuarios u WHERE u.correo = :correo")
    List<Usuarios> findByCorreoContaining(String correo);

    @Query("SELECT u FROM Usuarios u WHERE u.telefono = :telefono")
    List<Usuarios> findByTelefonoContaining(String telefono);

    @Query("SELECT u FROM Usuarios u WHERE u.usuarioT = :usuarioT")
    List<Usuarios> findByUsuarioTContaining(String usuarioT);

    @Query("SELECT u FROM Usuarios u WHERE u.activo = :activo")
    List<Usuarios> findByActivo(int activo);
     
    // SQL NATIVO PARA ORACLE - AQUÍ SÍ USAMOS LOS NOMBRES DE LAS COLUMNAS DE LA BASE DE DATOS
    @Query(value = "SELECT * FROM TODOUSER.USUARIOS WHERE " +
        "(UPPER(:columna) = 'NOMBRE' AND UPPER(NOMBRE) LIKE UPPER(CONCAT('%', :valor, '%'))) OR " +
        "(UPPER(:columna) = 'CORREO' AND UPPER(CORREO) LIKE UPPER(CONCAT('%', :valor, '%'))) OR " +
        "(UPPER(:columna) = 'TOKENCHANNEL' AND UPPER(TOKENCHANNEL) LIKE UPPER(CONCAT('%', :valor, '%'))) OR " +
        "(UPPER(:columna) = 'TELEFONO' AND UPPER(TELEFONO) LIKE UPPER(CONCAT('%', :valor, '%'))) OR " +
        "(UPPER(:columna) = 'USUARIOT' AND UPPER(USUARIOT) LIKE UPPER(CONCAT('%', :valor, '%'))) OR " +
        "(UPPER(:columna) = 'ACTIVO' AND :valor IN ('0', '1') AND ACTIVO = TO_NUMBER(:valor))",
        nativeQuery = true)
List<Usuarios> buscarPorColumnaNativo(@Param("columna") String columna, @Param("valor") String valor);
}