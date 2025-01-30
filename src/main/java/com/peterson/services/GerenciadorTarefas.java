package com.peterson.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.peterson.entities.Tarefa;
import com.peterson.gerenciadorTarefas.emuns.Prioridade;
import com.peterson.gerenciadorTarefas.emuns.TaskStatus;

public class GerenciadorTarefas {
	
	private List<Tarefa> tarefas;
	
	public GerenciadorTarefas() {
		this.tarefas = new ArrayList<>();
	}

	public void adicionarTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
	}
	
	public void removerTarefa(String id) {
		tarefas.removeIf(tarefa -> tarefa.getIdTarefa().equals(id));
	} 

	public List<Tarefa> listarTarefas() {
		return new ArrayList<>(tarefas);
	}
	
	public Tarefa buscartarefaPorId(String id) {
		return tarefas.stream()
                .filter(tarefa -> tarefa.getIdTarefa().equals(id))
                .findFirst()
                .orElse(null);
	}
	
	public List<Tarefa> listarTarefasPorStatus(TaskStatus status){
		return tarefas.stream().filter(tarefa -> tarefa.getStatus() == status) //filtrando com base no status
				.collect(Collectors.toList()); // coleta como lista	
	}
	
	public List<Tarefa> listarTarefasPorPrioridade(Prioridade prioridade){
		return tarefas.stream().filter(tarefa -> tarefa.getPrioridade() == prioridade) // filtrando com base na prioridade
				.collect(Collectors.toList()); // coleta como lista
	}
	
	
}
