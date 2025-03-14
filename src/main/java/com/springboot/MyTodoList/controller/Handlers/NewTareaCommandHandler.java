package com.springboot.MyTodoList.controller.Handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.data.UserData;
import com.springboot.MyTodoList.model.Proyecto;
import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.ProyectoService;
import com.springboot.MyTodoList.service.SprintService;
import com.springboot.MyTodoList.service.TareaService;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Segunda parte de la creación de una nueva tarea, se espera 
 * haber recibido el nombre con el cual iniciamos la tarea
 */

@Component
public class NewTareaCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(NewTareaCommandHandler.class);

    private final TareaService tareaService;
    private final ProyectoService proyectoService;
    private final UserData userData;
    private final SprintService sprintService;

    private boolean isWaitingForTareaName = false;
    private boolean isWaitingForProyecto = false;
    private boolean isWaitingForFechaEntrega = false;
    private boolean isWaitingForEquipo = false;
    private boolean isWaitingForHorasEstimadas = false;
    
    // Mapa para almacenar tareas temporales mientras se completa el proceso de creación
    private Map<Long, Tarea> tareasEnProceso = new HashMap<>();

    public NewTareaCommandHandler
    (
        TareaService tareaService, 
        UserData userData, 
        ProyectoService proyectoService,
        SprintService sprintService
    ) {
        this.proyectoService = proyectoService;
        this.userData = userData;
        this.tareaService = tareaService;
        this.sprintService = sprintService;
    }

    public void setEsperandoNombre() {
        isWaitingForTareaName = true;
    }

    public boolean isEsperandoNombre() {
        return isWaitingForTareaName;
    }
    
    public boolean isEsperandoEquipo(){
        return isWaitingForEquipo;
    }
    public boolean isEsperandoProyecto() {
        return isWaitingForProyecto;
    }
    
    public boolean isEsperandoFechaEntrega() {
        return isWaitingForFechaEntrega;
    }

    public boolean isEsperandoHorasEstimadas() {
        return isWaitingForHorasEstimadas;
    }
    
    private void trymessage(Update update, AbsSender sender, String message) 
    throws TelegramApiException
    {
        long chatId = update.getMessage().getChatId();
        SendMessage mensajeParaTelegram = new SendMessage();
        mensajeParaTelegram.setChatId(chatId);
        mensajeParaTelegram.setText(message);
        try {
            sender.execute(mensajeParaTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }
    
    private void sendMessageWithoutUpdate(long chatId, AbsSender sender, String message) 
    throws TelegramApiException
    {
        SendMessage mensajeParaTelegram = new SendMessage();
        mensajeParaTelegram.setChatId(chatId);
        mensajeParaTelegram.setText(message);
        try {
            sender.execute(mensajeParaTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
    }

    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        
        String user_response = messageText;
        String message = "";

        // Si es un callback query (botón presionado)
        if (update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery().getData(), chatId, sender, update);
            return;
        }

        try {
            if (isWaitingForTareaName) {
                // Primera etapa: recibimos el nombre/descripción de la tarea
                Tarea nuevaTarea = new Tarea();
                nuevaTarea.setDescripcion(user_response);
                nuevaTarea.setActivo(1);
                nuevaTarea.setEstado("Activa");
                nuevaTarea.setCategoria("Tarea");
                nuevaTarea.setHorasEstimadas(0);
                nuevaTarea.setHorasReales("Aun no se ha completado esta tarea");
                nuevaTarea.setDesarrollador(userData.getDesarrollador());
                
                // Guardamos la tarea en proceso
                tareasEnProceso.put(chatId, nuevaTarea);
                
                // Pasamos a la siguiente etapa
                isWaitingForTareaName = false;
                isWaitingForProyecto = true;

                // Obtenemos los proyectos del usuario
                List<Proyecto> proyectos_usuario = userData.getProyectos();
                List<String> nombres_proyectos = new ArrayList<>();
                for (Proyecto proyecto : proyectos_usuario) {
                    nombres_proyectos.add(proyecto.getNombre());
                }

                // Solicitamos el nombre del equipo
                message = "Por Favor, Ingresa el nombre del proyecto en el que se encuentra la tarea " + nuevaTarea.getDescripcion() + " :(de la siguiente lista)\n";
                for (String nombreProyecto : nombres_proyectos) {message += "\t\t\t- " + nombreProyecto + "\n";}
                trymessage(update, sender, message);

            } else if (isWaitingForProyecto) {
                // Segunda etapa: recibimos el nombre del proyecto
                Boolean existe = false;
                Tarea tarea = tareasEnProceso.get(chatId);
                List<Proyecto> proyectos_usuario = userData.getProyectos();
                List<String> nombres_proyectos = new ArrayList<>();

                for (Proyecto proyecto : proyectos_usuario) {
                    nombres_proyectos.add(proyecto.getNombre());
                }

                for (Proyecto proyecto : proyectos_usuario) {
                    if (proyecto.getNombre().equals(user_response)) {
                        
                        tarea.setProyecto(proyecto);
                        tareasEnProceso.put(chatId, tarea);
                        
                        isWaitingForProyecto = false;
                        isWaitingForFechaEntrega = true;

                        message = "Excelente, has seleccionado el proyecto " + tarea.getProyecto().getNombre() + ".\n" +
                              "Por favor, selecciona la fecha de entrega para la tarea:";
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    message = "El proyecto no existe, por favor ingrese un proyecto válido:\n";
                    for (String nombreProyecto : nombres_proyectos) {message += "\t\t\t- " + nombreProyecto + "\n";}
                    trymessage(update, sender, message);
                } else {
                    // Solicitamos la fecha de entrega con un calendario inline
                    
                    trymessage(update, sender, message);
                    mostrarCalendarioFechaEntrega(chatId, sender);
                }
    
                
            } else if (isWaitingForFechaEntrega) {
                // Tercera etapa: recibimos la fecha de entrega
                user_response = messageText;
                LocalDateTime fechaEntrega;
                
                try {
                    // Si la respuesta es en formato "yyyy-MM-dd'T'HH:mm"
                    if (user_response.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
                        DateTimeFormatter isoFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        fechaEntrega = LocalDateTime.parse(user_response, isoFormatter);
                    } else {
                        // Formato tradicional DD/MM/YYYY
                        // Se espera recibir 12/03/2025
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        fechaEntrega = LocalDate.parse(user_response, formatter).atStartOfDay();
                    }
                    
                    logger.info("=====================\nFecha de entrega:\n " + fechaEntrega + "======================\n");

                    LocalDateTime fechaentrega_mediodia = fechaEntrega.withHour(12);
                    
                    Tarea tarea = tareasEnProceso.get(chatId);
                    tarea.setFechaEntrega(fechaentrega_mediodia);

                    // Convertir la fecha a String en el formato esperado por el repositorio
                    DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    String fechaEntregaStr = fechaEntrega.format(dbFormatter);
                    
                    // Pasar la fecha como String al método del servicio
                    Sprint sprint_entrega = sprintService.getClosest(fechaEntregaStr, tarea.getProyecto().getIdProyecto());
                    
                    // Revisamos de la lista de Sprint en que sprint entraria
                    tarea.setSprint(sprint_entrega);
                    isWaitingForFechaEntrega = false;
                    isWaitingForHorasEstimadas = true;
                    // Guardamos la tarea en la base de datos
                    
                    
                    // Notificamos éxito
                    SendMessage mensajeExito = new SendMessage();
                    mensajeExito.setChatId(chatId);
                    mensajeExito.setText("Tarea \"" + tarea.getDescripcion() + "\" ha sido seleccionada para entregarse en \"" + 
                                        tarea.getProyecto().getNombre() + "\" con fecha de entrega " + tarea.getFechaEntrega() + "ahora por favor ingresa las horas estimadas para la tarea");
                    sender.execute(mensajeExito);
                    
                    // Limpiamos el estad
                    
                } catch (Exception e) {
                    // Formato de fecha inválido
                    SendMessage mensajeError = new SendMessage();
                    mensajeError.setChatId(chatId);
                    mensajeError.setText("⚠️ Formato de fecha inválido. " + user_response + " No es un formato de fecha válido. Por favor, ingresa la fecha en formato DD/MM/YYYY o YYYY-MM-DDTHH:MM:\n" + e.getMessage());
                    sender.execute(mensajeError);
                }
            } else if(isWaitingForHorasEstimadas){
                // Cuarta etapa: recibimos las horas estimadas
                user_response = messageText;
                Tarea tarea = tareasEnProceso.get(chatId);
                try {
                    int horasEstimadas = Integer.parseInt(user_response);
                    tarea.setHorasEstimadas(horasEstimadas);
                    tareaService.agregarTarea(tarea);
                    isWaitingForHorasEstimadas = false;
                    tareasEnProceso.remove(chatId);
                    sendMessageWithoutUpdate(chatId, sender, "✅ Tarea \"" + tarea.getDescripcion() + "\" creada con éxito en el proyecto \"" + 
                                     tarea.getProyecto().getNombre() + "\" con fecha de entrega " + tarea.getFechaEntrega());
                } catch (Exception e) {
                    SendMessage mensajeError = new SendMessage();
                    mensajeError.setChatId(chatId);
                    mensajeError.setText("⚠️ Formato de horas estimadas inválido. " + user_response + " No es un número válido. Por favor, ingresa las horas estimadas como un número entero.");
                    sender.execute(mensajeError);
                }
                tareaService.agregarTarea(tarea);
            }
            
        } catch (Exception e) {
            logger.error("Error en el proceso de creación de tarea: " + e.getLocalizedMessage(), e);

            SendMessage mensajeParaTelegram = new SendMessage();
            mensajeParaTelegram.setChatId(chatId);
            mensajeParaTelegram.setText("❌ Error al crear la tarea: " + e.getMessage());

            sender.execute(mensajeParaTelegram);
            
            // Reiniciamos el estado
            isWaitingForTareaName = false;
            isWaitingForProyecto = false;
            isWaitingForFechaEntrega = false;
            tareasEnProceso.remove(chatId);
        }
    }
    
    private void mostrarCalendarioFechaEntrega(long chatId, AbsSender sender) throws TelegramApiException {
        // Creamos un teclado inline con fechas para los próximos 7 días
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        
        // Primera fila: hoy y mañana
        List<InlineKeyboardButton> rowFirst = new ArrayList<>();
        
        LocalDate hoy = LocalDate.now();
        LocalDate manana = hoy.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        InlineKeyboardButton buttonHoy = new InlineKeyboardButton();
        buttonHoy.setText("Hoy: " + hoy.format(formatter));
        buttonHoy.setCallbackData("fecha_" + hoy.format(formatter));
        
        InlineKeyboardButton buttonManana = new InlineKeyboardButton();
        buttonManana.setText("Mañana: " + manana.format(formatter));
        buttonManana.setCallbackData("fecha_" + manana.format(formatter));
        
        rowFirst.add(buttonHoy);
        rowFirst.add(buttonManana);
        rowsInline.add(rowFirst);
        
        // Segunda fila: 3 días y 7 días
        List<InlineKeyboardButton> rowSecond = new ArrayList<>();
        
        LocalDate tresDias = hoy.plusDays(3);
        LocalDate sieteDias = hoy.plusDays(7);
        
        InlineKeyboardButton buttonTresDias = new InlineKeyboardButton();
        buttonTresDias.setText("3 días: " + tresDias.format(formatter));
        buttonTresDias.setCallbackData("fecha_" + tresDias.format(formatter));
        
        InlineKeyboardButton buttonSieteDias = new InlineKeyboardButton();
        buttonSieteDias.setText("7 días: " + sieteDias.format(formatter));
        buttonSieteDias.setCallbackData("fecha_" + sieteDias.format(formatter));
        
        rowSecond.add(buttonTresDias);
        rowSecond.add(buttonSieteDias);
        rowsInline.add(rowSecond);
        
        // Tercera fila: fecha personalizada
        List<InlineKeyboardButton> rowThird = new ArrayList<>();
        
        InlineKeyboardButton buttonCustom = new InlineKeyboardButton();
        buttonCustom.setText("Fecha personalizada");
        buttonCustom.setCallbackData("fecha_personalizada");
        
        rowThird.add(buttonCustom);
        rowsInline.add(rowThird);
        
        markupInline.setKeyboard(rowsInline);
        
        // Enviamos el mensaje con el teclado inline
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Por favor, selecciona la fecha de entrega para la tarea:");
        message.setReplyMarkup(markupInline);
        
        sender.execute(message);
    }
    
    // Método para manejar las respuestas de los botones inline del calendario
    public void handleCallbackQuery(String callbackData, long chatId, AbsSender sender, Update update) throws TelegramApiException {
        if (callbackData.startsWith("fecha_")) {
            String fechaSeleccionada = callbackData.substring("fecha_".length());
            
            if (fechaSeleccionada.equals("personalizada")) {
                // El usuario quiere ingresar una fecha personalizada
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Por favor, ingresa la fecha de entrega en formato DD/MM/YYYY:");
                sender.execute(message);
            } else {
                // Primero enviamos la fecha como si el usuario la hubiera escrito
                SendMessage echoFecha = new SendMessage();
                echoFecha.setChatId(chatId);
                echoFecha.setText(fechaSeleccionada);
                sender.execute(echoFecha);
                
                // Procesamos la fecha seleccionada
                Tarea tarea = tareasEnProceso.get(chatId);
                
                // Convertimos de string a LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(fechaSeleccionada, formatter);
                LocalDateTime fechaEntrega = fecha.atStartOfDay();
                LocalDateTime fechaEntregaMediodia = fechaEntrega.withHour(12);
                tarea.setFechaEntrega(fechaEntregaMediodia);
                
                // Revisamos de la lista de Sprint en qué sprint entraría
                List<Proyecto> proyectos_usuario = userData.getProyectos();
                for (Proyecto proyecto : proyectos_usuario) {
                    if (proyecto.getNombre().equals(tarea.getProyecto().getNombre())) {
                        List<Sprint> sprints = userData.getSprintsProyecto(proyecto);
                        for (Sprint sprint : sprints) {
                            if (sprint.getFechaInicio().isBefore(fechaEntrega) && sprint.getFechaFin().isAfter(fechaEntrega)) {
                                tarea.setSprint(sprint);
                                break;
                            }
                        }
                        break;
                    }
                }
                
                // Guardamos la tarea en la base de datos
                tareaService.agregarTarea(tarea);
                
                // Notificamos éxito
                SendMessage mensajeExito = new SendMessage();
                mensajeExito.setChatId(chatId);
                mensajeExito.setText("✅ Tarea \"" + tarea.getDescripcion() + "\" creada con éxito en el proyecto \"" + 
                                     tarea.getProyecto().getNombre() + "\" con fecha de entrega " + tarea.getFechaEntrega());
                sender.execute(mensajeExito);
                
                // Limpiamos el estado
                isWaitingForFechaEntrega = false;
                tareasEnProceso.remove(chatId);
            }
        }
    }
    
    // Método para manejar callbacks cuando no tenemos la referencia al Update
    public void handleCallbackQuery(String callbackData, long chatId, AbsSender sender) throws TelegramApiException {
        if (callbackData.startsWith("fecha_")) {
            String fechaSeleccionada = callbackData.substring("fecha_".length());
            
            if (fechaSeleccionada.equals("personalizada")) {
                // El usuario quiere ingresar una fecha personalizada
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Por favor, ingresa la fecha de entrega en formato DD/MM/YYYY:");
                sender.execute(message);
            } else {
                // Primero enviamos la fecha como si el usuario la hubiera escrito
                SendMessage echoFecha = new SendMessage();
                echoFecha.setChatId(chatId);
                echoFecha.setText(fechaSeleccionada);
                sender.execute(echoFecha);
                
                // Procesamos la fecha seleccionada automáticamente
                Tarea tarea = tareasEnProceso.get(chatId);
                
                // Convertimos de string a LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate fecha = LocalDate.parse(fechaSeleccionada, formatter);
                LocalDateTime fechaEntrega = fecha.atStartOfDay();
                LocalDateTime fechaEntregaMediodia = fechaEntrega.withHour(12);
                tarea.setFechaEntrega(fechaEntregaMediodia);
                
                // Revisamos de la lista de Sprint en qué sprint entraría
                List<Proyecto> proyectos_usuario = userData.getProyectos();
                for (Proyecto proyecto : proyectos_usuario) {
                    if (proyecto.getNombre().equals(tarea.getProyecto().getNombre())) {
                        List<Sprint> sprints = userData.getSprintsProyecto(proyecto);
                        for (Sprint sprint : sprints) {
                            if (sprint.getFechaInicio().isBefore(fechaEntrega) && sprint.getFechaFin().isAfter(fechaEntrega)) {
                                tarea.setSprint(sprint);
                                break;
                            }
                        }
                        break;
                    }
                }
                
                // Guardamos la tarea en la base de datos
                tareaService.agregarTarea(tarea);
                
                // Notificamos éxito
                SendMessage mensajeExito = new SendMessage();
                mensajeExito.setChatId(chatId);
                mensajeExito.setText("✅ Tarea \"" + tarea.getDescripcion() + "\" creada con éxito en el proyecto \"" + 
                                     tarea.getProyecto().getNombre() + "\" con fecha de entrega " + tarea.getFechaEntrega());
                sender.execute(mensajeExito);
                
                // Limpiamos el estado
                isWaitingForFechaEntrega = false;
                tareasEnProceso.remove(chatId);
            }
        }
    }
    
    // Método para cancelar la creación de tarea en curso
    public void cancelarCreacionTarea(long chatId, AbsSender sender) throws TelegramApiException {
        if (isWaitingForTareaName || isWaitingForProyecto || isWaitingForFechaEntrega) {
            // Hay una creación en proceso, la cancelamos
            isWaitingForTareaName = false;
            isWaitingForProyecto = false;
            isWaitingForFechaEntrega = false;
            tareasEnProceso.remove(chatId);
            
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("❌ Creación de tarea cancelada.");
            sender.execute(message);
        }
    }
}