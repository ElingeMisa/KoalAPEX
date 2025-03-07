package com.springboot.MyTodoList.controller.Handlers;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for command handlers to provide a consistent approach
 * for handling different commands in the Telegram bot.
 */
public interface CommandHandler {
    
    /**
     * Determines if this handler can process the given command
     * 
     * @param messageText The message text from the telegram update
     * @return true if this handler can process the command
     */
    boolean canHandle(String messageText);
    
    /**
     * Handles the command
     * 
     * @param update The telegram update
     * @param sender The telegram bot that can send messages
     * @throws TelegramApiException if there is an error executing the command
     */
    void handle(Update update, AbsSender sender) throws TelegramApiException;
}
