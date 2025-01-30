package com.peterson.gerenciadorTarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.peterson.menu.MenuTarefas;

@SpringBootApplication
public class GerenciadorTarefasApplication {

	public static void main(String[] args) {
		MenuTarefas menu = new MenuTarefas();
		menu.exibirMenu();
	}

}
