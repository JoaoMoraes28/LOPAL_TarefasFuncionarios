package br.dev.joao.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	public boolean gravarTarefa(Tarefa tarefa) {

		this.tarefa = tarefa;
		
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
				tarefa.setId(tarefaVetor[0]);
				tarefa.setNome(tarefaVetor[1]);
				tarefa.setDescricao(tarefaVetor[2]);
				tarefa.setResponsavel(tarefaVetor[3]);
				
				int prazo = Integer.parseInt(tarefaVetor[4]);
				tarefa.setPrazo(prazo);
				
			
				tarefa.setDataInicio(tarefaVetor[5]);
				
				tarefa.setListDataInicio(tarefaVetor[5]);
				tarefa.setStatus(tarefaVetor[6]);
				tarefa.setDataPrevista(tarefaVetor[7]);
				
				tarefas.add(tarefa);
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return tarefas;
		
	}

}
