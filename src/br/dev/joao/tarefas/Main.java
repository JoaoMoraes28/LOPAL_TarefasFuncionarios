package br.dev.joao.tarefas;

import javax.swing.JFrame;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.dao.TarefaDao;
import br.dev.joao.tarefas.model.Tarefa;
import br.dev.joao.tarefas.ui.FuncionarioListaFrame;
import br.dev.joao.tarefas.ui.MenuPrincipalFrame;
import br.dev.joao.tarefas.ui.TarefaFrame;
import br.dev.joao.tarefas.ui.TarefaListaFrame;

public class Main {

	public static void main(String[] args) {

		
		JFrame tela = new JFrame();
		new TarefaListaFrame(tela);
//		new MenuPrincipalFrame();	
		
	}

}
