package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.model.Usuarios;
import com.springboot.MyTodoList.service.DesarrolladorService;
import com.springboot.MyTodoList.service.TareaService;
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

    @Autowired
    public StartCommandHandler(DesarrolladorService desarrolladorService, UsuariosService usuariosService, TareaService tareaService) {
        this.tareaService = tareaService;
        this.usuariosService = usuariosService;
        this.desarrolladorService = desarrolladorService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.START_COMMAND.getCommand()) || 
               messageText.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel());
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

    private void FillUserDataSEQUENCE(){

    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        
        long chatId = update.getMessage().getChatId();
        
        String chatidString = String.valueOf(chatId);
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);
        messageToTelegram.setText(BotMessages.HELLO_MYTODO_BOT.getMessage());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard = getKeyboard();

        keyboardMarkup.setKeyboard(keyboard);

        messageToTelegram.setReplyMarkup(keyboardMarkup);

        trymessage(sender, BotMessages.HELLO_MYTODO_BOT.getMessage(), messageToTelegram);

        FillUserDataSEQUENCE();

    }
}