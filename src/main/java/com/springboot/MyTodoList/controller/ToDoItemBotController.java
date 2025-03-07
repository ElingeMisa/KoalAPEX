package com.springboot.MyTodoList.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.controller.Handlers.CommandHandler;

import com.springboot.MyTodoList.controller.Handlers.AddItemCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.DefaultCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.HideCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.ItemActionHandler;
import com.springboot.MyTodoList.controller.Handlers.ListItemsCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewHelloCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewToDoItemHandler;
import com.springboot.MyTodoList.controller.Handlers.StartCommandHandler;

import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotHelper;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

/* */

/**
 * Controlador principal para el Bot de Telegram de la lista de tareas pendientes
 * Delega el manejo de comandos a manejadores especializados
 * (los 500 archivos de la carpeta handlers)
 */

public class ToDoItemBotController extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(ToDoItemBotController.class);
    private final List<CommandHandler> commandHandlers = new ArrayList<>();
    private final NewToDoItemHandler newToDoItemHandler;
    private final String botName;

    private final NewHelloCommandHandler newHelloCommandHandler;

    @Autowired 
    public ToDoItemBotController(String botToken, String botName, ToDoItemService toDoItemService, NewHelloCommandHandler newHelloCommandHandler) {
        super(botToken);
        logger.info("Bot Token: " + botToken);
        logger.info("Bot name: " + botName);
        this.botName = botName;
        this.newHelloCommandHandler = newHelloCommandHandler;  // <-- Guardamos la instancia inyectada

        // Initialize handlers
        newToDoItemHandler = new NewToDoItemHandler(toDoItemService);
        
        // Flujos de comandos
        commandHandlers.add(new StartCommandHandler());
        commandHandlers.add(new ListItemsCommandHandler(toDoItemService));
        commandHandlers.add(new AddItemCommandHandler(toDoItemService));
        commandHandlers.add(new ItemActionHandler(toDoItemService));
        commandHandlers.add(newHelloCommandHandler);  // <-- Usamos la instancia inyectada
        commandHandlers.add(new HideCommandHandler());
        commandHandlers.add(new DefaultCommandHandler());
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageTextFromTelegram = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            try {
                // Los items nuevos se manejan de manera especial

                if (newToDoItemHandler.isExpectingNewItem()) {
                    newToDoItemHandler.handleNewItemText(update, this);
                    return;
                }
                // Recorremos los comandos para ver si alguno coincide con el comando recibido
                boolean handled = false;
                for (CommandHandler handler : commandHandlers) {

                    if (handler.canHandle(messageTextFromTelegram)) {
                        handler.handle(update, this);
                        
                        // Special case for setting the expectingNewItem flag
                        if (handler instanceof AddItemCommandHandler) {
                            newToDoItemHandler.setExpectingNewItem(true);
                        }
                        
                        handled = true;
                        break;
                    }
                }
                
                if (!handled) {
                    // Este caso no deberia ocurrir porque el DefaultCommandHandler siempre deberia manejarlo
                    SendMessage messageToTelegram = new SendMessage();
                    messageToTelegram.setChatId(chatId);
                    String feedback = '"' + messageTextFromTelegram + " \"no es un comando valido." + BotMessages.INVALID_COMMAND.getMessage() ;
                    messageToTelegram.setText(feedback);
                    execute(messageToTelegram);
                    
                }
            } catch (TelegramApiException e) {
                logger.error("Error handling update: " + e.getLocalizedMessage(), e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}