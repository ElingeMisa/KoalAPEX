package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.model.Desarrollador;
import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

import oracle.net.aso.m;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.springboot.MyTodoList.repository.UsuariosRepository;
import com.springboot.MyTodoList.service.DesarrolladorService;
import com.springboot.MyTodoList.service.TareaService;
import com.springboot.MyTodoList.service.UsuariosService;

/**
 * Handles the new hello command
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Handles the new hello command
 */
@Component  // <-- Agregar esta anotación para que Spring gestione esta clase
public class NewHelloCommandHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(NewHelloCommandHandler.class);

    private final UsuariosService usuariosService;
    private final DesarrolladorService desarrolladorService;
    private final TareaService tareaService;


    @Autowired
    public NewHelloCommandHandler(DesarrolladorService desarrolladorService, UsuariosService usuariosService, TareaService tareaService) {
        this.tareaService = tareaService;
        this.usuariosService = usuariosService;
        this.desarrolladorService = desarrolladorService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.NEW_HELLO.getCommand()) || 
               messageText.equals(BotLabels.NEW_HELLO.getLabel());
    }

    private void trymessage(AbsSender sender,String message, SendMessage messageToTelegram) throws TelegramApiException{
        messageToTelegram.setText(message);
        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        
        long chatId = update.getMessage().getChatId();
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);

        String chatidString = String.valueOf(chatId);
        String message = BotMessages.NEW_HELLO.getMessage();

        trymessage(sender, message, messageToTelegram);

        message = "Información adicional: \n";

        trymessage(sender, message, messageToTelegram);

        message = "";

        List<Usuarios> usuarios = usuariosService.finByTokenChannel(chatidString);
                
        for (Usuarios usuario : usuarios) {
            message += usuario.toString();
        }

        trymessage(sender, message, messageToTelegram);

        message= "\nRol :\n";

        trymessage(sender, message, messageToTelegram);

        message = "";

        List<Desarrollador> desarrollador = desarrolladorService.findByIdUsuario(usuarios.get(0).getId());
        for (Desarrollador des : desarrollador) {
            message += des.toString();
        }
        
        trymessage(sender, message, messageToTelegram);

        message = "Tareas asignadas: \n";

        trymessage(sender, message, messageToTelegram);

        List<Tarea> tareas = tareaService.findByIdDesarrollador(desarrollador.get(0).getIdDesarrollador());
        message = "";

        for (Tarea tarea : tareas) {
            message += tarea.toString();
        }
        messageToTelegram.setText(message);
        trymessage(sender, message, messageToTelegram);
    }
}
