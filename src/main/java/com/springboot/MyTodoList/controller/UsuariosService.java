package com.springboot.MyTodoList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.repository.UsuariosRepository;
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

@Service
public class UsuariosService {
    
    private final UsuariosRepository usuariosRepository;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
    
    List<Usuarios> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    Usuarios findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    Usuarios addUsuario(Usuarios usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUsuario'");
    }

    Usuarios updateUsuarios(int id, Usuarios usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUsuarios'");
    }

    Boolean deleteUsuario(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUsuario'");
    }

    public List<Usuarios> finByTokenChannel(String tokenChannel) {
        List<Usuarios> usuariosPorToken = usuariosRepository.findByTokenChannel(tokenChannel);
        if (usuariosPorToken == null || usuariosPorToken.isEmpty()) {
            usuariosPorToken = new ArrayList<>();
            usuariosPorToken.add(new Usuarios());
        }
        return usuariosPorToken;
    }



} 
