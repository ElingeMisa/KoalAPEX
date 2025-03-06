package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.service.ToDoItemService;

import com.springboot.MyTodoList.model.ToDoItem;

import com.springboot.MyTodoList.util.BotMessages;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.OffsetDateTime;

/**
 * This is a special handler that receives text input after the AddItemCommandHandler
 * and creates a new to-do item. It needs special treatment as part of a multi-step flow.
 */
public class NewToDoItemHandler {
    private static final Logger logger = LoggerFactory.getLogger(NewToDoItemHandler.class);
    private final ToDoItemService toDoItemService;
    
    // Flag to indicate if we're expecting a new to-do item text
    private boolean expectingNewItem = false;

    public NewToDoItemHandler(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }
    
    public void setExpectingNewItem(boolean expectingNewItem) {
        this.expectingNewItem = expectingNewItem;
    }
    
    public boolean isExpectingNewItem() {
        return expectingNewItem;
    }

    public void handleNewItemText(Update update, AbsSender sender) throws TelegramApiException {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        
        try {
            ToDoItem newItem = new ToDoItem();
            newItem.setDescription(messageText);
            newItem.setCreation_ts(OffsetDateTime.now());
            newItem.setDone(false);
            toDoItemService.addToDoItem(newItem);

            SendMessage messageToTelegram = new SendMessage();
            messageToTelegram.setChatId(chatId);
            messageToTelegram.setText(BotMessages.NEW_ITEM_ADDED.getMessage());
            
            sender.execute(messageToTelegram);
            
            // Reset the flag
            expectingNewItem = false;
            
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            
            SendMessage messageToTelegram = new SendMessage();
            messageToTelegram.setChatId(chatId);
            messageToTelegram.setText("Error adding item: " + e.getMessage());
            
            sender.execute(messageToTelegram);
        }
    }
}