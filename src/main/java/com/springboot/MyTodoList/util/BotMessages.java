package com.springboot.MyTodoList.util;

public enum BotMessages {
	
	HELLO_MYTODO_BOT(
	"¡Hola! Soy el Bot de MyTodoList.\nEscribe un nuevo elemento de la lista de tareas a continuación y presiona el botón de enviar (flecha azul), o selecciona una opción a continuación:"),
	BOT_REGISTERED_STARTED("¡Bot registrado y iniciado con éxito!"),
	ITEM_DONE("¡Elemento completado! Selecciona /todolist para volver a la lista de tareas, o /start para ir a la pantalla principal."), 
	ITEM_UNDONE("¡Elemento desmarcado! Selecciona /todolist para volver a la lista de tareas, o /start para ir a la pantalla principal."), 
	ITEM_DELETED("¡Elemento eliminado! Selecciona /todolist para volver a la lista de tareas, o /start para ir a la pantalla principal."),
	TYPE_NEW_TODO_ITEM("Escribe un nuevo elemento de la lista de tareas a continuación y presiona el botón de enviar (flecha azul) en el lado derecho."),
	NEW_ITEM_ADDED("¡Nuevo elemento añadido! Selecciona /todolist para volver a la lista de tareas, o /start para ir a la pantalla principal."),
	BYE("¡Adiós! Selecciona /start para continuar."),
	NO_ITEMS("¡No hay elementos para mostrar! Selecciona /start para ir a la pantalla principal."),
	NO_ITEMS_TO_DELETE("¡No hay elementos para eliminar! Selecciona /start para ir a la pantalla principal."),
	NEW_HELLO("¡Hola, soy un mensaje nuevo creado por el desarrollador!"),
	INVALID_COMMAND("¡Por favor, ingrese un comando válido!"),
	TYPE_NEW_TAREA("Escribe una nueva tarea a continuación y presiona el botón de enviar (flecha azul) en el lado derecho."),
	NEW_TAREA_ADDED("¡Nueva tarea añadida! Selecciona /todolist para volver a la lista de tareas, o /start para ir a la pantalla principal."),
	NO_TAREAS("¡No hay tareas para mostrar! Selecciona /start para ir a la pantalla principal."),
	NO_TAREAS_TO_DELETE("¡No hay tareas para eliminar! Selecciona /start para ir a la pantalla principal."),
	NO_TAREAS_TO_DONE("¡No hay tareas para marcar como completadas! Selecciona /start para ir a la pantalla principal."),;

	private String message;

	BotMessages(String enumMessage) {
		this.message = enumMessage;
	}

	public String getMessage() {
		return message;
	}

}
