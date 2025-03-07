package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;
import com.springboot.MyTodoList.util.BotHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Handles the hide keyboard command
 */
public class HideCommandHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(HideCommandHandler.class);

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.HIDE_COMMAND.getCommand()) || 
               messageText.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();
        BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), sender);
    }
}