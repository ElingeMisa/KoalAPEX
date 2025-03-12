package com.springboot.MyTodoList.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.repository.SprintRepository;

@Service("sprintService")
public class SprintService {

    
    private final SprintRepository sprintRepository;

    @Autowired
    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }
    
    public List<Sprint> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Sprint findById(int id) {
        // TODO Auto-generated method stub
        return sprintRepository.findByIdSprint((long) id);
    }

    public Sprint addSprint(Sprint sprint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSprint'");
    }

    public Sprint updateSprint(int id, Sprint sprint) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSprint'");
    }

    public Boolean deleteSprint(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSprint'");
    }

    public List<Sprint> findByidProyecto(Long id) {
        List<Sprint> sprints = sprintRepository.findByIdProyecto(id);
        return sprints;
        
    }

    public Sprint getClosest(String fechaEntregaStr, Long id) {
        
        try {
            LocalDateTime fechaEntrega;
            
            // Normalizar espacios en blanco
            fechaEntregaStr = fechaEntregaStr.trim();
    
            if (fechaEntregaStr.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}(:\\d{2})?")) {
                // Formato ISO: "2025-03-12T00:00" o "2025-03-12T00:00:00"
                fechaEntrega = LocalDateTime.parse(fechaEntregaStr);
            } else if (fechaEntregaStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                // Formato DD/MM/YYYY: "12/03/2025"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fechaEntrega = LocalDate.parse(fechaEntregaStr, formatter).atStartOfDay();
            } else {
                throw new IllegalArgumentException("⚠️ Formato de fecha inválido: " + fechaEntregaStr);
            }
            
            // Establecer la hora a 12:00
            fechaEntrega = fechaEntrega.withHour(12).withMinute(0).withSecond(0).withNano(0);
    
            // 🛠️ DEBUG: Verificar que la fecha se ha convertido correctamente
            System.out.println("📅 Fecha procesada: " + fechaEntrega);
    
            // 🔥 Retornar `LocalDateTime` en lugar de `Timestamp`
            List<Sprint> sprints = sprintRepository.getClosestByTimestamp(fechaEntrega, id);
            return sprints.get(0);
        } catch (Exception e) {
            throw new RuntimeException("❌ Error al procesar la fecha: " + fechaEntregaStr + " - " + e.getMessage(), e);
        }
    }
}

