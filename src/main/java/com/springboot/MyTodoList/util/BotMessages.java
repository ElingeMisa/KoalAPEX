package com.springboot.MyTodoList.util;

/* 
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
	NO_TAREAS_TO_DONE("¡No hay tareas para marcar como completadas! Selecciona /start para ir a la pantalla principal."),
	BOT_NOT_STARTED("Hola, veo que no has presionado el boton de inicio, por lo que lo hare yo mismo.\n Iniciando bot...");
	private String message;

	BotMessages(String enumMessage) {
		this.message = enumMessage;
	}

	public String getMessage() {
		return message;
	}

}
	*/
	
	public enum BotMessages {
		
		HELLO_MYTODO_BOT(
		"<b>✨ BIENVENIDO A TASKMASTER ✨</b>\n\n"
		+ "<i>Tu asistente personal de productividad</i>\n\n"
		+ "<span class='large'>📝 Escribe una nueva tarea o selecciona una opción:</span>\n"
		+ "<pre>——————————————</pre>"),
		
		BOT_REGISTERED_STARTED("<b>🚀 TASKMASTER ACTIVADO 🚀</b>\n\n"
		+ "<span class='large'>Estoy listo para ayudarte a conquistar tu día.</span>\n"
		+ "<pre>——————————————</pre>"),
		
		ITEM_DONE("<b>✅ ¡TAREA COMPLETADA! 🎉</b>\n\n"
		+ "<i>Un paso más hacia tus objetivos.</i>\n\n"
		+ "<span class='large'>Opciones:</span>\n"
		+ "• <code>/todolist</code> → Ver todas mis tareas\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		ITEM_UNDONE("<b>🔄 ¡TAREA PENDIENTE DE NUEVO!</b>\n\n"
		+ "<i>No hay problema, a veces necesitamos más tiempo.</i>\n\n"
		+ "<span class='large'>Opciones:</span>\n"
		+ "• <code>/todolist</code> → Ver todas mis tareas\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		ITEM_DELETED("<b>🗑️ ¡TAREA ELIMINADA!</b>\n\n"
		+ "<i>Más espacio para lo importante.</i>\n\n"
		+ "<span class='large'>Opciones:</span>\n"
		+ "• <code>/todolist</code> → Ver todas mis tareas\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		TYPE_NEW_TODO_ITEM("<b>📝 NUEVA TAREA</b>\n\n"
		+ "<span class='large'>Escribe tu tarea y envíala con la flecha azul ➡️</span>\n\n"
		+ "<i>¡Sé específico para mejores resultados!</i>\n"
		+ "<pre>——————————————</pre>"),
		
		NEW_ITEM_ADDED("<b>➕ ¡NUEVA TAREA AÑADIDA! 💪</b>\n\n"
		+ "<i>Tu futuro yo te lo agradecerá.</i>\n\n"
		+ "<span class='large'>Opciones:</span>\n"
		+ "• <code>/todolist</code> → Ver todas mis tareas\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		BYE("<b>👋 ¡HASTA PRONTO!</b>\n\n"
		+ "<span class='large'>Estaré aquí cuando me necesites.</span>\n\n"
		+ "• <code>/start</code> → Volver cuando quieras\n"
		+ "<pre>——————————————</pre>"),
		
		NO_ITEMS("<b>📭 TU LISTA ESTÁ VACÍA</b>\n\n"
		+ "<span class='large'>¡Es el momento perfecto para añadir nuevas metas!</span>\n\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		NO_ITEMS_TO_DELETE("<b>🔍 NO HAY TAREAS PARA ELIMINAR</b>\n\n"
		+ "<span class='large'>Tu lista ya está limpia y lista para nuevos desafíos.</span>\n\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		NEW_HELLO("<b>🌟 ¡HOLA! SOY TASKMASTER 2.0</b>\n\n"
		+ "<span class='large'>He evolucionado para servirte mejor.</span>\n\n"
		+ "<i>¿En qué puedo ayudarte hoy?</i>\n"
		+ "<pre>——————————————</pre>"),
		
		INVALID_COMMAND("<b>❓ COMANDO NO RECONOCIDO</b>\n\n"
		+ "<span class='large'>Incluso los mejores asistentes se confunden a veces.</span>\n\n"
		+ "Intenta con un comando válido o <code>/start</code> para ver las opciones.\n"
		+ "<pre>——————————————</pre>"),
		
		TYPE_NEW_TAREA("<b>📋 NUEVA TAREA</b>\n\n"
		+ "<span class='large'>Describe lo que necesitas hacer y envíalo con la flecha azul ➡️</span>\n\n"
		+ "<i>Tip: Añade detalles para recordar mejor después.</i>\n"
		+ "<pre>——————————————</pre>"),
		
		NEW_TAREA_ADDED("<b>✅ ¡TAREA REGISTRADA CON ÉXITO!</b>\n\n"
		+ "<i>La organización es el primer paso hacia el éxito.</i>\n\n"
		+ "<span class='large'>Opciones:</span>\n"
		+ "• <code>/todolist</code> → Ver todas mis tareas\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		NO_TAREAS("<b>🏝️ ¡ZONA LIBRE DE TAREAS!</b>\n\n"
		+ "<span class='large'>Disfruta el momento o añade nuevos objetivos.</span>\n\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		NO_TAREAS_TO_DELETE("<b>🧹 TODO LIMPIO POR AQUÍ</b>\n\n"
		+ "<span class='large'>No hay tareas para eliminar. ¡Buen trabajo!</span>\n\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		NO_TAREAS_TO_DONE("<b>🔎 NO HAY TAREAS PENDIENTES</b>\n\n"
		+ "<span class='large'>¡Parece que ya completaste todo! Es hora de nuevos desafíos.</span>\n\n"
		+ "• <code>/start</code> → Volver al inicio\n"
		+ "<pre>——————————————</pre>"),
		
		BOT_NOT_STARTED("<b>👋 ¡HOLA! PARECE QUE AÚN NO HEMOS COMENZADO</b>\n\n"
		+ "<span class='large'>No te preocupes, yo me encargo.</span>\n\n"
		+ "<b>🔄 Iniciando TaskMaster...</b>\n"
		+ "<i>¡Listo para la acción!</i>\n"
		+ "<pre>——————————————</pre>");
		
		private String message;
	
		BotMessages(String enumMessage) {
			this.message = enumMessage;
		}
	
		public String getMessage() {
			return message;
		}
	
	}
