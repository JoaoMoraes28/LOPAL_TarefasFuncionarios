package br.dev.joao.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuPrincipalFrame {

	private JButton btnFuncionario;
	private JButton btnTarefas;

	public MenuPrincipalFrame() {
		criarTela();

	}

	public void criarTela() {

		JFrame telaMenu = new JFrame();
		telaMenu.setTitle("Menu de tarefas e funcionários");
		telaMenu.setSize(370, 150);
		telaMenu.setLayout(null);
		telaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaMenu.setResizable(false);
		telaMenu.setLocationRelativeTo(null);

		Container painel = telaMenu.getContentPane();

		btnFuncionario = new JButton();
		btnFuncionario.setText("FUNCIONÁRIOS");
		btnFuncionario.setBounds(10, 30, 150, 50);

		btnTarefas = new JButton();
		btnTarefas.setText("TAREFAS");
		btnTarefas.setBounds(190, 30, 150, 50);

		btnFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FuncionarioListaFrame(telaMenu);

			}
		});

		btnTarefas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefaListaFrame(telaMenu);

			}
		});

		painel.add(btnFuncionario);
		painel.add(btnTarefas);

		telaMenu.setVisible(true);

	}

}
