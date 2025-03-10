/* 
package com.springboot.MyTodoList.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.data.UsuarioEquipoDTO;
import com.springboot.MyTodoList.repository.UsuarioEquipoRepository;

@Service
public class UsuarioEquipoService {

    private final UsuarioEquipoRepository usuarioEquipoRepository;
    
    @Autowired
    public UsuarioEquipoService(UsuarioEquipoRepository usuarioEquipoRepository) {
        this.usuarioEquipoRepository = usuarioEquipoRepository;
    }
    
    /**
     * Obtiene la información de usuarios, equipos y roles a partir del token de canal
     * 
     * @param tokenChannel Token de canal del usuario
     * @return Lista de DTOs con la información formateada
     
    public List<UsuarioEquipoDTO> findUsuariosEquiposByTokenChannel(String tokenChannel) {
        List<Object[]> results = usuarioEquipoRepository.findUsuariosEquiposByTokenChannel(tokenChannel);
        List<UsuarioEquipoDTO> dtos = new ArrayList<>();
        
        for (Object[] result : results) {
            String nombreUsuario = (String) result[0];
            Long idEquipo = result[1] != null ? ((Number) result[1]).longValue() : null;
            String nombreEquipo = (String) result[2];
            String rol = (String) result[3];
            
            dtos.add(new UsuarioEquipoDTO(nombreUsuario, idEquipo, nombreEquipo, rol));
        }
        
        return dtos;
    }
    
    /**
     * Genera un string formateado con la información de usuarios, equipos y roles
     * 
     * @param tokenChannel Token de canal del usuario
     * @return String formateado con la información
     
    public String generateUsuarioEquipoInfoString(String tokenChannel) {
        List<UsuarioEquipoDTO> equiposInfo = findUsuariosEquiposByTokenChannel(tokenChannel);
        StringBuilder infoBuilder = new StringBuilder("\nRol :\n");
        
        if (equiposInfo.isEmpty()) {
            infoBuilder.append("No tienes equipos asignados actualmente.");
        } else {
            for (UsuarioEquipoDTO info : equiposInfo) {
                infoBuilder.append(info.toString());
            }
        }
        
        return infoBuilder.toString();
    }
}
*/