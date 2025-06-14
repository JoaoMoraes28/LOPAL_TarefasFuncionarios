package br.dev.joao.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.dev.joao.tarefas.factory.ArquivoTarefaFactory;
import br.dev.joao.tarefas.model.Tarefa;

public class TarefaDao {

	private Tarefa tarefa;
	private ArquivoTarefaFactory aff = new ArquivoTarefaFactory();
	BufferedWriter bw;
	BufferedReader br;

	public TarefaDao(Tarefa tarefa) {
		this.tarefa = tarefa;
		gravarTarefa();
	}
	
	public TarefaDao() {
		
	}

	public boolean gravarTarefa() {

		try {

			bw = aff.getBw();
			bw.write(tarefa.toString());
			bw.flush();

			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	public List<Tarefa> getTarefas() {
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		
		try {
			
			String linha = "";
			br = aff.getBr();
			
			while (linha != null) {
				linha = br.readLine();
				String[] tarefaVetor =  linha.split(",");
				Tarefa tarefa = new Tarefa();
				tarefa.setNome(tarefaVetor[0]);
				tarefa.setDescricao(tarefaVetor[1]);
				tarefa.setResponsavel(tarefaVetor[2]);
				
				int prazo = Integer.parseInt(tarefaVetor[3]);
				tarefa.setPrazo(prazo);
				
				tarefa.setListDataInicio(tarefaVetor[4]);
				tarefa.setStatus(tarefaVetor[5]);
				tarefa.setDataPrevista(tarefaVetor[6]);
				
				tarefas.add(tarefa);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		 
		System.out.println(tarefas);
		
		return tarefas;
		
	}

}
