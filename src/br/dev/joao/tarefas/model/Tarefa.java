package br.dev.joao.tarefas.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {

	private String id;
	private String nome;
	private String descricao;
	private String responsavel;
	private LocalDate dataInicio;
	private String dataInicioString;
	private int prazo;
	private LocalDate dataEntrega;
	private String dataPrevista;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Status status;

	public Tarefa() {

	}

	public Tarefa(String id, String nome, String descricao, String responsavel, int prazo, String dataIncioString) {
		setId(id);
		setResponsavel(responsavel);
		setNome(nome);
		setDescricao(descricao);
		setPrazo(prazo);
		setDataInicio(dataIncioString);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
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

	public String getDataInicioString() {
		return dataInicioString;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		dataInicioString = dataInicio;
		this.dataInicio = LocalDate.parse(dataInicio, formato);
	}

	public void setListDataInicio(String dataInicio) {
		dataInicioString = dataInicio;
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

	public String getDataPrevistaString() {
		dataInicio = dataInicio.plusDays(prazo);
		dataPrevista = dataInicio.format(formato);
		return dataPrevista;
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

	public String getStatus() {
		LocalDate hoje = LocalDate.now();

		if (hoje.isAfter(getDataPrevistaEntrega())) {
			status = Status.EM_ATRASO;

		} else if (hoje.isBefore(dataInicio)) {
			status = Status.NAO_INICIADO;

		} else if (hoje.isAfter(dataInicio) || hoje.isBefore(getDataPrevistaEntrega())) {
			status = Status.EM_ANDAMENTO;

		}

		String statuString = status.name();

		return statuString;
	}

	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	@Override
	public String toString() {

		return String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", id, nome, descricao, responsavel, prazo, dataInicioString,
				status, getDataPrevistaEntrega());
	}

}
