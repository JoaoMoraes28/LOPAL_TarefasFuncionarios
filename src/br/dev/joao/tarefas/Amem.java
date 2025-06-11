package br.dev.joao.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.model.Funcionario;
import br.dev.joao.tarefas.model.Status;
import br.dev.joao.tarefas.model.Tarefa;
import br.dev.joao.tarefas.ui.FuncionarioFrame;
import br.dev.joao.tarefas.ui.FuncionarioListaFrame;
import br.dev.joao.tarefas.ui.MenuPrincipalFrame;
import br.dev.joao.tarefas.ui.TarefaFrame;
import br.dev.joao.tarefas.utils.Utils;

public class Amem {

	public static void main(String[] args) {
		
//		Funcionario funcionario = new Funcionario("Paulo" , "Engenheiro");
//		funcionario.setSetor("Tecnologia da informação");
//		funcionario.setSalario(6987.98);
//		
//		FuncionarioDAO dao = new FuncionarioDAO(funcionario);
//		dao.gravar();
		
		
//		new FuncionarioFrame();
		
//		new FuncionarioListaFrame();
		
		
		
//		new MenuPrincipalFrame(); CORRETA
		
		new TarefaFrame();
	}

	private static void testarLeituraEscritaArquivo() {

		String caminho = "/Users/25132878/projetoTarefas/tarefas";
		
		FileReader fr = null;
		String linha = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fr = new FileReader(caminho);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(caminho , true);
			bw = new BufferedWriter(fw);
			
			bw.append("Victor\n");
			bw.flush();
			
			linha =  br.readLine();
			
			while (linha != null) {
			
				System.out.println(linha);
				linha = br.readLine();
				
			}
			
			
		} catch (FileNotFoundException erro) {
			System.out.println("Arquivo não encontado!");
		
		} catch (IOException erro) {
			System.out.println("Arquivo não pode ser lido");
			
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}











}
