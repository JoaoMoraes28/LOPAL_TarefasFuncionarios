package br.dev.joao.tarefas.factory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoFuncionarioFactory {

	private String caminho = "/Users/25132878/eclipse-workspace/LOPAL_TarefasFuncionarios/funcionarios.csv";
	private FileWriter fw;
	private BufferedWriter bw;
	private FileReader fr;
	private BufferedReader br;

	public BufferedWriter getBw() throws IOException {

		fw = new FileWriter(caminho, true);
		bw = new BufferedWriter(fw);

		return bw;
	}

	public BufferedReader getBr() throws IOException {

		fr = new FileReader(caminho);
		br = new BufferedReader(fr);

		return br;
	}

}
