package com.springboot.MyTodoList.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.controller.Handlers.CommandHandler;
import com.springboot.MyTodoList.controller.Handlers.AddItemCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.AddTareaCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.DefaultCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.HideCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.ItemActionHandler;
import com.springboot.MyTodoList.controller.Handlers.ListItemsCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewHelloCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewTareaCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewToDoItemHandler;
import com.springboot.MyTodoList.controller.Handlers.StartCommandHandler;

import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotMessages;

import com.springboot.MyTodoList.data.UserData;

/* */

/**
 * Controlador principal para el Bot de Telegram de la lista de tareas pendientes
 * Delega el manejo de comandos a manejadores especializados
 * (los 500 archivos de la carpeta handlers)
 */

public class ToDoItemBotController extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(ToDoItemBotController.class);
    private final List<CommandHandler> commandHandlers = new ArrayList<>();
    private final String botName;
    
    private final NewHelloCommandHandler newHelloCommandHandler;
    private final StartCommandHandler startCommandHandler;
    private final AddTareaCommandHandler addTareaCommandHandler;
    private final NewToDoItemHandler newToDoItemHandler;
    private final NewTareaCommandHandler newTareaCommandHandler;
    private final ListItemsCommandHandler listItemsCommandHandler;

    private boolean StartCommandHandler;
    public UserData userData;

    @Autowired 
    public ToDoItemBotController
    (
        String botToken, 
        String botName, 
        ToDoItemService toDoItemService, 
        NewHelloCommandHandler newHelloCommandHandler, 
        StartCommandHandler startCommandHandler,
        AddTareaCommandHandler addTareaCommandHandler,
        NewTareaCommandHandler newTareaCommandHandler,
        ListItemsCommandHandler listItemsCommandHandler,
        UserData userData,
        Boolean StartCommandHandler
    ) 
    {    
        super(botToken);
        logger.info("Bot Token: " + botToken);
        logger.info("Bot name: " + botName);
        
        this.botName = botName;
        this.listItemsCommandHandler = listItemsCommandHandler;
        this.newHelloCommandHandler = newHelloCommandHandler;  // <-- Guardamos la instancia inyectada
        this.startCommandHandler = startCommandHandler;
        this.addTareaCommandHandler = addTareaCommandHandler;
        this.newTareaCommandHandler = newTareaCommandHandler;
        this.StartCommandHandler = StartCommandHandler;
        this.userData = userData;

        // Initialize handlers
        newToDoItemHandler = new NewToDoItemHandler(toDoItemService);
        
        // Flujos de comandos
        commandHandlers.add(startCommandHandler);
        commandHandlers.add(listItemsCommandHandler);
        commandHandlers.add(new AddItemCommandHandler(toDoItemService));
        commandHandlers.add(new ItemActionHandler(toDoItemService));
        commandHandlers.add(newHelloCommandHandler);  // <-- Usamos la instancia inyectada
        commandHandlers.add(new HideCommandHandler());
        commandHandlers.add(addTareaCommandHandler);
        commandHandlers.add(new DefaultCommandHandler());
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        if (StartCommandHandler || update.getMessage().getText().equals("/start")) {
            
            StartCommandHandler = true;

            if (update.hasMessage() && update.getMessage().hasText()) {

                String messageTextFromTelegram = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

                try {
                    // Los items nuevos se manejan de manera especial
                    //if (newToDoItemHandler.isExpectingNewItem()) {
                    //    newToDoItemHandler.handleNewItemText(update, this);
                    //    return;
                    //}
                    // En caso de que add tarea se haya ejecutado
                    if( newTareaCommandHandler.isEsperandoNombre() || 
                        newTareaCommandHandler.isEsperandoFechaEntrega() || 
                        newTareaCommandHandler.isEsperandoProyecto() 
                        )
                    {
                        newTareaCommandHandler.handle(update, this);
                        return;
                    }
                    // Recorremos los comandos para ver si alguno coincide con el comando recibido
                    boolean handled = false;
                    
                    for (CommandHandler handler : commandHandlers) {

                        if (handler.canHandle(messageTextFromTelegram)) {
                            
                            handler.handle(update, this);
                            
                            // Special case for setting the expectingNewItem flag
                            //if (handler instanceof AddItemCommandHandler) {
                            //    newToDoItemHandler.setExpectingNewItem(true);
                            //}

                            if(handler instanceof AddTareaCommandHandler){
                                newTareaCommandHandler.setEsperandoNombre();
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

        }else{

            StartCommandHandler = true;

            SendMessage messageToTelegram = new SendMessage();
            messageToTelegram.setChatId(update.getMessage().getChatId());
            messageToTelegram.setText(BotMessages.BOT_NOT_STARTED.getMessage());

            try {
                execute(messageToTelegram);
            } catch (TelegramApiException e) {
                logger.error("Error handling update: " + e.getLocalizedMessage(), e);
            }

            try {
                startCommandHandler.handle(update, this);
            } catch (TelegramApiException e) {
                
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}