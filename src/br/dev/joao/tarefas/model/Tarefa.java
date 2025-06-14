package br.dev.joao.tarefas.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

	private String nome;
	private String descricao;
	private String responsavel;
	private LocalDate dataInicio;
	private int prazo;
	private LocalDate dataEntrega;
	private String dataPrevista;
	private Status status;
	private String statusString;

	public Tarefa(String nome, String descricao, String responsavel, int prazo, String dataIncioString) {
		setResponsavel(responsavel);
		setNome(nome);
		setDescricao(descricao);
		setPrazo(prazo);
		setDataInicio(dataIncioString);
	}
	
	public Tarefa() {
		
	}

	public String getNome() {
		return nome;
	}
	
	public String getStatusString() {
		return statusString;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.dataInicio = LocalDate.parse(dataInicio, formato);
	
	}
	
	public void setListDataInicio(String dataInicio) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.dataInicio = LocalDate.parse(dataInicio, formato);
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public LocalDate getDataPrevistaEntrega() {
		return dataInicio.plusDays(prazo);
	}

	public void setDataPrevista(String dataPrevista) {
		 this.dataPrevista = dataPrevista;
	}
	
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public Status getStatus() {
		LocalDate hoje = LocalDate.now();
		
		if (hoje.isAfter(getDataPrevistaEntrega())) {
			status = Status.EM_ATRASO;
			statusString = "ATRASADA";
			
		} else if (hoje.isBefore(dataInicio)) {
			status = Status.NAO_INICIADO;
			statusString = "NÃO INICIADA";
			
		} else if (hoje.isAfter(dataInicio) || hoje.isBefore(getDataPrevistaEntrega())) {
			status = Status.EM_ANDAMENTO;
			statusString = "EM ANDAMENTO";
		
		}

		return status;
	}
	
	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	
	@Override
	public String toString() {

		return String.format("%s,%s,%s,%s,%s,%s,%s\n", nome, descricao, responsavel, prazo, dataInicio, status, getDataPrevistaEntrega());
	}

}
