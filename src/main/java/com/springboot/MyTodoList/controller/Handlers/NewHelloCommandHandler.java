package com.springboot.MyTodoList.controller.Handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.springboot.MyTodoList.controller.UsuariosService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

/**
 * Handles the new hello command
 */
public class NewHelloCommandHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(NewHelloCommandHandler.class);

    private final UsuariosService usuariosService = null;

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.NEW_HELLO.getCommand()) || 
               messageText.equals(BotLabels.NEW_HELLO.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);

        String chatidString = toString().valueOf(chatId);
        String message = BotMessages.NEW_HELLO.getMessage() + " " + chatidString;
        messageToTelegram.setText(message);
        
        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }
}