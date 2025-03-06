package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Handles the command to add a new to-do item
 */
public class AddItemCommandHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(AddItemCommandHandler.class);
    private final ToDoItemService toDoItemService;

    public AddItemCommandHandler(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.ADD_ITEM.getCommand()) || 
               messageText.equals(BotLabels.ADD_NEW_ITEM.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();
        
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);
        messageToTelegram.setText(BotMessages.TYPE_NEW_TODO_ITEM.getMessage());
        
        // hide keyboard
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