package com.springboot.MyTodoList.controller.Handlers;

import com.springboot.MyTodoList.data.UserData;
import com.springboot.MyTodoList.model.Tarea;
import com.springboot.MyTodoList.model.ToDoItem;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotCommands;
import com.springboot.MyTodoList.util.BotLabels;

import org.apache.tomcat.jni.User;
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
import java.util.stream.Collectors;

/**
 * Handles the command to list all to-do items
 */
@Component
public class ListItemsCommandHandler implements CommandHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(ListItemsCommandHandler.class);
    
    private final UserData userData;
    
    private final ToDoItemService toDoItemService;

    @Autowired
    public ListItemsCommandHandler
    (
        ToDoItemService toDoItemService,
        UserData userData
    ) {
        this.userData = userData;
        this.toDoItemService = toDoItemService;
    }

    @Override
    public boolean canHandle(String messageText) {
        return messageText.equals(BotCommands.TODO_LIST.getCommand()) || 
               messageText.equals(BotLabels.LIST_ALL_ITEMS.getLabel()) ||
               messageText.equals(BotLabels.MY_TODO_LIST.getLabel());
    }

    @Override
    public void handle(Update update, AbsSender sender) throws TelegramApiException {
        long chatId = update.getMessage().getChatId();
        
        List<ToDoItem> allItems = toDoItemService.findAll();
        
        List<Tarea> tareas = userData.getTareas();
        String label = "Tareas asignadas a " + userData.getUsuario().getNombre();

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        // command back to main screen
        KeyboardRow mainScreenRowTop = new KeyboardRow();
        mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
        keyboard.add(mainScreenRowTop);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
        keyboard.add(firstRow);

        KeyboardRow myTodoListTitleRow = new KeyboardRow();
        myTodoListTitleRow.add(label);
        keyboard.add(myTodoListTitleRow);

        // Active items
        List<ToDoItem> activeItems = allItems.stream()
                .filter(item -> !item.isDone())
                .collect(Collectors.toList());

        List<Tarea> activeTareas = tareas.stream()
                .filter(tarea -> tarea.isActivo())
                .collect(Collectors.toList());

        //for (ToDoItem item : activeItems) {
        //    KeyboardRow currentRow = new KeyboardRow();
        //    currentRow.add(item.getDescription());
        //   currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
        //    keyboard.add(currentRow);
        //}
        for (Tarea tarea : activeTareas) {
            KeyboardRow currentRow = new KeyboardRow();
            currentRow.add(tarea.getDescripcion());
            currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
            keyboard.add(currentRow);
        }

        // Done items
        List<ToDoItem> doneItems = allItems.stream()
                .filter(ToDoItem::isDone)
                .collect(Collectors.toList());
        
        List<Tarea> doneTareas = tareas.stream()
                .filter(Tarea -> !Tarea.isActivo())
                .collect(Collectors.toList());

        //for (ToDoItem item : doneItems) {
        //    KeyboardRow currentRow = new KeyboardRow();
        //    currentRow.add(item.getDescription());
        //    currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
        //    currentRow.add(item.getID() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
        //    keyboard.add(currentRow);
        //}
        for (Tarea tarea : doneTareas) {
            KeyboardRow currentRow = new KeyboardRow();
            currentRow.add(tarea.getDescripcion());
            currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
            currentRow.add(tarea.getIdTarea() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
            keyboard.add(currentRow);
        }

        // command back to main screen at bottom
        KeyboardRow mainScreenRowBottom = new KeyboardRow();
        mainScreenRowBottom.add(label);
        keyboard.add(mainScreenRowBottom);

        keyboardMarkup.setKeyboard(keyboard);

        // 
        SendMessage messageToTelegram = new SendMessage();
        messageToTelegram.setChatId(chatId);

        messageToTelegram.setText(BotLabels.MY_TODO_LIST.getLabel());
        messageToTelegram.setReplyMarkup(keyboardMarkup);

        try {
            sender.execute(messageToTelegram);
        } catch (TelegramApiException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        }
    }
}