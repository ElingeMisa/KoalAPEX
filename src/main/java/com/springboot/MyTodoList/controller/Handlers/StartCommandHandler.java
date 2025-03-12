package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.data.UserData;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.model.Equipo;
import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.model.Manager;
import com.springboot.MyTodoList.model.Proyecto;
import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.model.Tarea;

import com.springboot.MyTodoList.service.DesarrolladorService;
import com.springboot.MyTodoList.service.EquipoService;
import com.springboot.MyTodoList.service.ManagerService;
import com.springboot.MyTodoList.service.ProyectoService;
import com.springboot.MyTodoList.service.SprintService;
import com.springboot.MyTodoList.service.TareaService;
import com.springboot.MyTodoList.service.UsuarioEquipoService;
import com.springboot.MyTodoList.service.UsuariosService;

import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the start command and main screen display
 */

@Component
public class StartCommandHandler implements CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(StartCommandHandler.class);

    private final UsuariosService usuariosService;
    private final DesarrolladorService desarrolladorService;
    private final TareaService tareaService;
    private final ManagerService managerService;
    private final UsuarioEquipoService  usuarioEquipoService;
    private final ProyectoService proyectoService;
    private final EquipoService equipoService;
    private final SprintService sprintService;

    private final UserData userData;

    @Autowired
    public StartCommandHandler
    (
            DesarrolladorService desarrolladorService, 
            UsuariosService usuariosService, 
            TareaService tareaService, 
            UserData userData, 
            ManagerService managerService, 
            UsuarioEquipoService usuarioEquipoService, 
            ProyectoService proyectoService, 
            EquipoService equiposService, 
            SprintService sprintService
    ) {
        this.sprintService = sprintService;
        this.equipoService = equiposService; 
        this.proyectoService = proyectoService;
        this.usuarioEquipoService = usuarioEquipoService;  
        this.managerService = managerService;
        this.userData = userData;
        this.tareaService = tareaService;
        this.usuariosService = usuariosService;
        this.desarrolladorService = desarrolladorService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.START_COMMAND.getCommand()) || 
               messageText.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel());
    }

    private void trymessage
    (AbsSender sender,String message, SendMessage messageToTelegram) 
    throws TelegramApiException
    {
        messageToTelegram.setText(message);
        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    private List<KeyboardRow> getKeyboard() {
        List<KeyboardRow> keyboard = new ArrayList<>();

        // first row
        KeyboardRow row = new KeyboardRow();
        row.add(BotLabels.LIST_ALL_ITEMS.getLabel());
        row.add(BotLabels.ADD_NEW_ITEM.getLabel());
        keyboard.add(row);

        // second row
        row = new KeyboardRow();
        row.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
        row.add(BotLabels.HIDE_MAIN_SCREEN.getLabel());
        keyboard.add(row);

        // third row
        row = new KeyboardRow();
        row.add(BotLabels.NEW_HELLO.getLabel());
        keyboard.add(row);

        return keyboard;
    }

    private void FillUserDataSequenceSH
        (String chatidString, AbsSender sender, SendMessage messageToTelegram) 
    throws TelegramApiException 
    {
        // Busqueda de usuario con el token del canal
        List<Usuarios> usuarios = usuariosService.finByTokenChannel(chatidString);
        if (!usuarios.isEmpty()) {
            userData.setUsuario(usuarios.get(0));
        } 
        // Busqueda de desarrollador con el id del usuario
        List<Desarrollador> desarrollador = desarrolladorService.findByIdUsuario(usuarios.get(0).getId());
        if(!desarrollador.isEmpty()){
            userData.setDesarrollador(desarrollador.get(0));
        }
        // Busca a un manager con el id del desarrollador
        List<Manager> managers = managerService.findByIdUsuario(usuarios.get(0).getId());
        if(!managers.isEmpty()){
            userData.setManager(managers.get(0));
        } 
        // Busca las tareas asignadas al desarrollador
        List<Tarea> tareas = tareaService.findByIdDesarrollador(desarrollador.get(0).getIdDesarrollador());   
        if(!tareas.isEmpty()){
            userData.setTareas(tareas);
        }
        // Busca los equipos a los que pertenece un usuario
        List<Equipo> equipos = equipoService.findByIdUsuario(usuarios.get(0).getId());
        if (!equipos.isEmpty()) {
            userData.setEquipos(equipos);
        }
        // Busca los proyectos de un usuario
        List<Proyecto> proyectos = new ArrayList<>();
        for(Equipo equipo : equipos){
            List<Proyecto> proyectosTemp = proyectoService.findByidEquipo(equipo.getIdEquipo());
            if(!proyectosTemp.isEmpty()){
                for(Proyecto proyecto : proyectosTemp){
                    proyectos.add(proyecto);
                }
            }
        }
        userData.setProyectos(proyectos);
        // Llenamos los sprints de los proyectos
        List<List<Sprint>> sprints = new ArrayList<>();
        for(Proyecto proyecto:proyectos){
            List<Sprint> sprintsProyecto = sprintService.findByidProyecto(proyecto.getIdProyecto());
            if(!sprintsProyecto.isEmpty()){
                sprints.add(sprintsProyecto);
            }
        }
        userData.setSprints(sprints);
        
    }

    @SuppressWarnings("unused")
    private void FillUserDataSequence
        (String chatidString, AbsSender sender, SendMessage messageToTelegram) 
    throws TelegramApiException 
    {
        String message = "";
        // Busqueda de usuario con el token del canal
        trymessage(sender, "Información adicional:", messageToTelegram);

        trymessage(sender, "Informacion de Usuario :", messageToTelegram);
        message = "";
        List<Usuarios> usuarios = usuariosService.finByTokenChannel(chatidString);
        
        if (!usuarios.isEmpty()) {
            for (Usuarios usuario : usuarios) {message += usuario.toString();}
            userData.setUsuario(usuarios.get(0));
        } else {
            message += "No se encontraron datos de usuario \n";
        }

        trymessage(sender, message, messageToTelegram);

        // Busqueda de desarrollador con el id del usuario
        trymessage(sender, "Informacion de Desarrollador: \n", messageToTelegram);
        message = "";
        List<Desarrollador> desarrollador = desarrolladorService.findByIdUsuario(usuarios.get(0).getId());

        if(!desarrollador.isEmpty()){
            for (Desarrollador des : desarrollador) {message += des.toString();}
            userData.setDesarrollador(desarrollador.get(0));
        } else {
            message += "No se encontraron datos de desarrollador \n";
        }

        trymessage(sender, message, messageToTelegram);

        // Busca a un manager con el id del desarrollador
        trymessage(sender, "Informacion de Manager: \n", messageToTelegram);
        message = "";
        List<Manager> managers = managerService.findByIdUsuario(usuarios.get(0).getId());

        if(!managers.isEmpty()){
            for(Manager manager: managers){message += manager.toString();}
            userData.setManager(managers.get(0));
        } else {
            message+= "No se encontraron datos de manager \n";
        }
        trymessage(sender, message, messageToTelegram);

        // Busca las tareas asignadas al desarrollador
        trymessage(sender, "Tareas asignadas: \n", messageToTelegram);
        message = "";
        List<Tarea> tareas = tareaService.findByIdDesarrollador(desarrollador.get(0).getIdDesarrollador());
        
        if(!tareas.isEmpty()){
            for (Tarea tarea : tareas) {message += tarea.toString();}
            userData.setTareas(tareas);
        } else {
            message += "No se encontraron tareas asignadas \n";
        }

        trymessage(sender, message, messageToTelegram);

        // Busca los equipos a los que pertenece un usuario
        trymessage(sender, "Equipos registrados de este usuario: \n", messageToTelegram);
        message = "";

        List<Equipo> equipos = equipoService.findByIdUsuario(usuarios.get(0).getId());

        if (!equipos.isEmpty()) {
            for (Equipo equipo : equipos) {message += equipo.toString();}
            userData.setEquipos(equipos);

        } else {
            message += "No se encontraron equipos registrados \n";
            
        }
        trymessage(sender, message, messageToTelegram);

        // Busca los proyectos de un usuario
        trymessage(sender, "Proyectos registrados de este usuario: \n", messageToTelegram);
        message = "";

        List<Proyecto> proyectos = new ArrayList<>();
        for(Equipo equipo : equipos){
            List<Proyecto> proyectosTemp = proyectoService.findByidEquipo(equipo.getIdEquipo());
            if(!proyectosTemp.isEmpty()){
                for(Proyecto proyecto : proyectosTemp){
                    proyectos.add(proyecto);
                }
            }
        }

        if(!proyectos.isEmpty()){
            for (Proyecto proyecto : proyectos) {message += proyecto.toString();}
            userData.setProyectos(proyectos);
        } else {
            message += "No se encontraron proyectos registrados \n";
        }
        trymessage(sender, message, messageToTelegram);

        // Todos los sprint de un proyecto
        trymessage(sender, "Sprints de los proyectos: \n", messageToTelegram);
        message = "";
        
        List<List<Sprint>> sprints = new ArrayList<>();

        for(Proyecto proyecto:proyectos){
            List<Sprint> sprintsProyecto = sprintService.findByidProyecto(proyecto.getIdProyecto());
            if(!sprintsProyecto.isEmpty()){
                for(Sprint sprint : sprintsProyecto){
                    message += sprint.toString();
                }
                sprints.add(sprintsProyecto);
            }
        }
        userData.setSprints(sprints);
        trymessage(sender, message, messageToTelegram);
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        
        long chatId = update.getMessage().getChatId();
        
        String chatidString = String.valueOf(chatId);
        String message = "";
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);
        messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard = getKeyboard();

        keyboardMarkup.setKeyboard(keyboard);

        messageToTelegram.setReplyMarkup(keyboardMarkup);

        trymessage(sender, BotMessages.HELLO_MYTODO_BOT.getMessage(), messageToTelegram);

        /*
         * Secuencia para llenar la información del usuario
         */

        FillUserDataSequenceSH(chatidString, sender, messageToTelegram);

        /* 
        message = "Información de usuario: \n";

        message+= "Hola " + userData.getUsuario().getNombre();
        message+="\nTienes " + userData.getTareas().size() + " tareas asignadas \n";
        message+="Tienes " + userData.getEquipos().size() + " equipos asignados \n";
        message+="Tienes " + userData.getProyectos().size() + " proyectos asignados \n";
        
        //mostramos el numero de sprint por proyecto
        for(int i = 0; i < userData.getProyectos().size(); i++){
            message += "El proyecto " + userData.getProyectos().get(i).getNombre() + " tiene " + userData.getSprints().get(i).size() + " sprints \n";
        }
        trymessage(sender, message, messageToTelegram);
        */
    }

}