package br.dev.joao.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

import br.dev.joao.tarefas.factory.ArquivoFuncionarioFactory;
import br.dev.joao.tarefas.model.Funcionario;

public class FuncionarioDAO {

	private Funcionario funcionario;
	private ArquivoFuncionarioFactory aff = new ArquivoFuncionarioFactory();

	public FuncionarioDAO(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public FuncionarioDAO() {
		
	}

	public boolean gravar() {
		try {
			BufferedWriter bw = aff.getBw();
			bw.write(funcionario.toString());
			bw.flush();
			return true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public List<Funcionario> getFuncionarios() {

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		try {
			BufferedReader br = aff.getBr();

			String linha = "";

			while (linha != null) {
				linha = br.readLine();
				String[] funcionarioVetor = linha.split(",");
				Funcionario funcionario = new Funcionario(null);
				funcionario.setMatricula(funcionarioVetor[0]);
				funcionario.setNome(funcionarioVetor[1]);
				funcionario.setCargo(funcionarioVetor[2]);
				funcionario.setSetor(funcionarioVetor[3]);
				funcionario.setSalario(Double.parseDouble(funcionarioVetor[4]));
				funcionarios.add(funcionario);

			}

		} catch (Exception e) {
			e.getMessage();
		}

		return funcionarios;
	}

}
