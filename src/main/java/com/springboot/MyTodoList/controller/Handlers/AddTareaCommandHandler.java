package com.springboot.MyTodoList.controller.Handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.service.TareaService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;
/*
 * Primera parte de la creación de una nueva tarea
 */
public class AddTareaCommandHandler implements CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(AddTareaCommandHandler.class);

    private final TareaService tareaService;

    public AddTareaCommandHandler(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.ADD_TAREA.getCommand()) || 
               messageText.equals(BotLabels.ADD_NEW_TAREA.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();

        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);
        messageToTelegram.setText(BotMessages.TYPE_NEW_TAREA.getMessage());

        // Escondemos el teclado de opciones
        ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove(true);
        messageToTelegram.setReplyMarkup(keyboardMarkup);
        
        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }
    
}
