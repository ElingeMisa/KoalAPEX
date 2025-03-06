package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotLabels;
import com.springboot.MyTodoList.util.BotMessages;
import com.springboot.MyTodoList.util.BotHelper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Handles actions on to-do items (mark as done, undo, delete)
 */
public class ItemActionHandler implements CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(ItemActionHandler.class);
    private final ToDoItemService toDoItemService;

    public ItemActionHandler(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.contains(BotLabels.DONE.getLabel()) || 
               messageText.contains(BotLabels.UNDO.getLabel()) ||
               messageText.contains(BotLabels.DELETE.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();

        try {
            if (messageText.contains(BotLabels.DONE.getLabel())) {
                handleDoneAction(messageText, chatId, sender);
            } else if (messageText.contains(BotLabels.UNDO.getLabel())) {
                handleUndoAction(messageText, chatId, sender);
            } else if (messageText.contains(BotLabels.DELETE.getLabel())) {
                handleDeleteAction(messageText, chatId, sender);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            BotHelper.sendMessageToTelegram(chatId, "Error: " + e.getMessage(), sender);
        }
    }

    private void handleDoneAction(String messageText, long chatId, AbsSender sender) throws Exception {
        String done = messageText.substring(0, messageText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(done);

        ToDoItem item = toDoItemService.getItemById(id).getBody();
        item.setDone(true);
        toDoItemService.updateToDoItem(id, item);
        BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE.getMessage(), sender);
    }

    private void handleUndoAction(String messageText, long chatId, AbsSender sender) throws Exception {
        String undo = messageText.substring(0, messageText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(undo);

        ToDoItem item = toDoItemService.getItemById(id).getBody();
        item.setDone(false);
        toDoItemService.updateToDoItem(id, item);
        BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE.getMessage(), sender);
    }

    private void handleDeleteAction(String messageText, long chatId, AbsSender sender) throws Exception {
        String delete = messageText.substring(0, messageText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(delete);

        toDoItemService.deleteToDoItem(id);
        BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), sender);
    }
}