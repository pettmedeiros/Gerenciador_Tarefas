package com.peterson.menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.peterson.entities.Tarefa;
import com.peterson.gerenciadorTarefas.emuns.TaskStatus;
import com.peterson.services.GerenciadorTarefas;

public class MenuTarefas {
	
	private GerenciadorTarefas gerenciadorTarefas;
	private Scanner scanner;
	
	public MenuTarefas() {
		this.gerenciadorTarefas = new GerenciadorTarefas();
		this.scanner = new Scanner(System.in);
	}
	
	public void exibirMenu() {
		while (true) {
			System.out.println("\n -- SISTEMA DE GERENCIAMENTO DE TAREFAS --");
			System.out.println("1. Adicionar tarefa");
			System.out.println("2. Listar Tarefas");
			System.out.println("3. Alterar Status da Tarefa");
			System.out.println("4. Remover tarefa");
			System.out.println("5. Sair");
			System.out.println("Escolha uma opção: ");
			
			int opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao) {
			
			case 1: 
				adicionarTarefa();
				break;
			 case 2:
                 listarTarefas();
                 break;
             case 3:
                 alterarStatusTarefa();
                 break;
             case 4:
                 removerTarefa();
                 break;
             case 5:
                 System.out.println("Encerrando o sistema...");
                 return;
             default:
                 System.out.println("Opção inválida!");
			}
		}
		
	}
		private void adicionarTarefa() {

			System.out.println("Digite o título da tarefa: ");
			String titulo = scanner.nextLine();
			
			System.out.print("Digite a descrição da tarefa: ");
			String descricao = scanner.nextLine();	
			
			System.out.print("Digite a prioridade da tarefa: ");
			String prioridade = scanner.nextLine();
			
			System.out.println("Digite quantos dias para conclusão da tarefa: ");
			int diasConclusao = scanner.nextInt();
			LocalDate dataPrevista = LocalDate.now().plusDays(diasConclusao); //soma dia atual + dias informado
			
			Tarefa novaTarefa = new Tarefa(null, titulo, descricao, dataPrevista, dataPrevista, null, null);
			gerenciadorTarefas.adicionarTarefa(novaTarefa);;
		}
		
		private void listarTarefas() {
			List<Tarefa> tarefas = gerenciadorTarefas.listarTarefas();
			System.out.println("Número de tarefas armazenadas: " + tarefas.size());

			if(tarefas.isEmpty()) {
				System.out.println("Nenhuma tarefa encontrada.");
				return;
			}
			
			tarefas.forEach(tarefa -> System.out.println(tarefa));
		}
		
		private void alterarStatusTarefa() {
			listarTarefas();
			
			System.out.println("Digite o ID da tarefa para alterar o Status: ");
			String id = scanner.nextLine();
			
			Tarefa tarefa = gerenciadorTarefas.buscartarefaPorId(id);
			if (tarefa == null) {
				System.out.println("Tarefa ão encontrada.");
			}
			
			System.out.println("Selecione o novo Status: ");
			for(TaskStatus status : TaskStatus.values()) {
				System.out.println(status.ordinal() + 1 +"." + status);
			}
			
			int statusIndex = scanner.nextInt();
	        TaskStatus novoStatus = TaskStatus.values()[statusIndex - 1];

	        tarefa.atualizarStatus(novoStatus);
	        System.out.println("Status da tarefa atualizado com sucesso!");
	    
		}
	
		private void removerTarefa() {
			listarTarefas();
			
			System.out.println("Digite o ID da tarefa para remover: ");
			String id = scanner.nextLine();
			
			Tarefa tarefa = gerenciadorTarefas.buscartarefaPorId(id);
			if (tarefa == null) {
				System.out.println("Tarefa ão encontrada.");
			}
			
			gerenciadorTarefas.removerTarefa(id);
			System.out.println("Tarefa removida com sucesso!");
		}
}
