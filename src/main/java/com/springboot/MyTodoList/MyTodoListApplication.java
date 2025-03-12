package com.springboot.MyTodoList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.springboot.MyTodoList.controller.ToDoItemBotController;
import com.springboot.MyTodoList.controller.Handlers.AddTareaCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewHelloCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.NewTareaCommandHandler;
import com.springboot.MyTodoList.controller.Handlers.StartCommandHandler;
import com.springboot.MyTodoList.data.UserData;
import com.springboot.MyTodoList.service.SprintService;
import com.springboot.MyTodoList.service.ToDoItemService;
import com.springboot.MyTodoList.util.BotMessages;

import oracle.security.o3logon.a;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.springboot.MyTodoList")
@EnableJpaRepositories("com.springboot.MyTodoList.repository")
public class MyTodoListApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(MyTodoListApplication.class);

	@Autowired
	public UserData userData;

	@Autowired
	private ToDoItemService toDoItemService;

	@Autowired
	private SprintService sprintService;

	@Value("${telegram.bot.token}")
	private String telegramBotToken;

	@Value("${telegram.bot.name}")
	private String botName;

	@Autowired  // Agregar la inyección de NewHelloCommandHandler
	private NewHelloCommandHandler newHelloCommandHandler;

	@Autowired
	private StartCommandHandler startCommandHandler;

	@Autowired
	private AddTareaCommandHandler addTareaCommandHandler;

	@Autowired
	private NewTareaCommandHandler newTareaCommandHandler;

	public static void main(String[] args) {
		SpringApplication.run(MyTodoListApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(
				DefaultBotSession.class
			);

			telegramBotsApi.registerBot(
				new ToDoItemBotController
					(
						telegramBotToken, 
						botName, 
						toDoItemService, 
						newHelloCommandHandler, 
						startCommandHandler, 
						addTareaCommandHandler,
						newTareaCommandHandler,
						userData,
						false
					)
				);
			logger.info(BotMessages.BOT_REGISTERED_STARTED.getMessage());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}


