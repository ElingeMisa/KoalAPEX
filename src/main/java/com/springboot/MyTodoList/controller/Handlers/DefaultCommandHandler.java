package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Handles commands that don't match any other handler
 */
public class DefaultCommandHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCommandHandler.class);

    @Override
    public boolean canHandle(String messageText) {
        // This is the default handler, so it handles everything not caught by other handlers
        return true;
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);
        String feedback = '"' + messageText + "\" no es un comando valido. " + BotMessages.INVALID_COMMAND.getMessage();
        messageToTelegram.setText(feedback);
        
        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }
}