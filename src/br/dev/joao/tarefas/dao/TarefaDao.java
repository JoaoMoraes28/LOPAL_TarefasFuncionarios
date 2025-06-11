package br.dev.joao.tarefas.dao;

import java.io.BufferedWriter;

import br.dev.joao.tarefas.factory.ArquivoTarefaFactory;
import br.dev.joao.tarefas.model.Tarefa;

public class TarefaDao {

	private Tarefa tarefa;
	private ArquivoTarefaFactory aff = new ArquivoTarefaFactory();
	
	
	public TarefaDao(Tarefa tarefa) {
		this.tarefa = tarefa;
		gravarTarefa();
	}
	
	public boolean gravarTarefa() {
		
		try {
			
			BufferedWriter bw = aff.getBw();
			bw.write(tarefa.toString());
			bw.flush();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	
	}
	
	
}
