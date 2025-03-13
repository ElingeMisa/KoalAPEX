package com.springboot.MyTodoList.util;

public enum BotLabels {
	
	SHOW_MAIN_SCREEN("Mostrar Pantalla Principal"), 
	HIDE_MAIN_SCREEN("Ocultar Pantalla Principal"),
	LIST_ALL_ITEMS("Listar todas las tareas"), 
	ADD_NEW_ITEM("Agregar nueva tarea"),
	DONE("Hecho"),
	UNDO("Deshacer"),
	DELETE("Eliminar"),
	MY_TODO_LIST("Mi Lista de Tareas"),
	DASH("-"),
	NEW_HELLO("Nuevo Saludo"),
	ADD_NEW_TAREA("Nueva Tarea");

	private String label;

	BotLabels(String enumLabel) {
		this.label = enumLabel;
	}

	public String getLabel() {
		return label;
	}

}
