package br.dev.joao.tarefas;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.dao.TarefaDao;
import br.dev.joao.tarefas.model.Tarefa;
import br.dev.joao.tarefas.ui.FuncionarioListaFrame;
import br.dev.joao.tarefas.ui.MenuPrincipalFrame;
import br.dev.joao.tarefas.ui.TarefaFrame;

public class Main {

	public static void main(String[] args) {

		new TarefaFrame();
		Tarefa tar = new Tarefa();
		TarefaDao dao = new TarefaDao();
		
		System.out.println(dao.getTarefas());
		
	}

}
