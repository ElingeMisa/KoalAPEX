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
			"*✨ BIENVENIDO A TASKMASTER ✨*\n\n" 
			+"\"==================================\n" 
			+ "_Tu asistente personal de productividad._\n\n"
			+ "📝 *Escribe una nueva tarea o selecciona una opción:*\n"
			+ "=================================="
		),

		BOT_REGISTERED_STARTED(
			"*🚀 TASKMASTER ACTIVADO 🚀*\n\n"
			+"\"==================================\n" 
			+ "Estoy listo para ayudarte a conquistar tu día. 💪\n"
			+ "=================================="
		),

		ITEM_DONE(
			"*✅ ¡TAREA COMPLETADA! 🎉*\n\n"
			+ "_Un paso más hacia tus objetivos._\n\n"
			+ "📌 *Opciones:*\n"
			+ "• <code>/todolist</code> → Ver todas mis tareas\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		ITEM_UNDONE(
			"*🔄 ¡TAREA PENDIENTE DE NUEVO!*\n\n"
			+ "_No hay problema, a veces necesitamos más tiempo._\n\n"
			+ "📌 *Opciones:*\n"
			+ "• <code>/todolist</code> → Ver todas mis tareas\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		ITEM_DELETED(
			"*🗑️ ¡TAREA ELIMINADA!*\n\n"
			+ "_Más espacio para lo importante._\n\n"
			+ "📌 *Opciones:*\n"
			+ "• <code>/todolist</code> → Ver todas mis tareas\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		TYPE_NEW_TODO_ITEM(
			"*📝 NUEVA TAREA*\n\n"
			+ "✏️ *Escribe tu tarea y envíala con la flecha azul ➡️*\n\n"
			+ "_¡Sé específico para mejores resultados!_"
		),

		NEW_ITEM_ADDED(
			"*➕ ¡NUEVA TAREA AÑADIDA! 💪*\n\n"
			+ "_Tu futuro yo te lo agradecerá._\n\n"
			+ "📌 *Opciones:*\n"
			+ "• <code>/todolist</code> → Ver todas mis tareas\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		BYE(
			"*👋 ¡HASTA PRONTO!*\n\n"
			+ "📌 *Vuelve cuando me necesites:*\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NO_ITEMS(
			"*📭 TU LISTA ESTÁ VACÍA*\n\n"
			+ "¡Es el momento perfecto para añadir nuevas metas! 🎯\n\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NO_ITEMS_TO_DELETE(
			"*🔍 NO HAY TAREAS PARA ELIMINAR*\n\n"
			+ "Tu lista ya está limpia y lista para nuevos desafíos. ✅\n\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NEW_HELLO(
			"*🌟 ¡HOLA! SOY TASKMASTER 2.0*\n\n"
			+ "🚀 *He evolucionado para servirte mejor.*\n\n"
			+ "_¿En qué puedo ayudarte hoy?_"
		),

		INVALID_COMMAND(
			"*❓ COMANDO NO RECONOCIDO*\n\n"
			+ "Incluso los mejores asistentes se confunden a veces. 🤔\n\n"
			+ "Prueba con un comando válido o usa <code>/start</code> para ver opciones."
		),

		TYPE_NEW_TAREA(
			"*📋 NUEVA TAREA*\n\n"
			+ "✏️ *Describe lo que necesitas hacer y envíalo con la flecha azul ➡️*\n\n"
			+ "_Tip: Añade detalles para recordar mejor después._"
		),

		NEW_TAREA_ADDED(
			"*✅ ¡TAREA REGISTRADA CON ÉXITO!*\n\n"
			+ "_La organización es el primer paso hacia el éxito._\n\n"
			+ "📌 *Opciones:*\n"
			+ "• <code>/todolist</code> → Ver todas mis tareas\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NO_TAREAS(
			"*🏝️ ¡ZONA LIBRE DE TAREAS!*\n\n"
			+ "Disfruta el momento o añade nuevos objetivos. ✨\n\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NO_TAREAS_TO_DELETE(
			"*🧹 TODO LIMPIO POR AQUÍ*\n\n"
			+ "No hay tareas para eliminar. ¡Buen trabajo! 🎉\n\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		NO_TAREAS_TO_DONE(
			"*🔎 NO HAY TAREAS PENDIENTES*\n\n"
			+ "¡Parece que ya completaste todo! Es hora de nuevos desafíos. 💡\n\n"
			+ "• <code>/start</code> → Volver al inicio"
		),

		BOT_NOT_STARTED(
			"*👋 ¡HOLA! PARECE QUE AÚN NO HEMOS COMENZADO*\n\n"
			+ "No te preocupes, yo me encargo. 🔄\n\n"
			+ "*Iniciando TaskMaster...*\n"
			+ "_¡Listo para la acción!_"
		);
		
		private String message;
	
		BotMessages(String enumMessage) {
			this.message = enumMessage;
		}
	
		public String getMessage() {
			return message;
		}
	
	}
