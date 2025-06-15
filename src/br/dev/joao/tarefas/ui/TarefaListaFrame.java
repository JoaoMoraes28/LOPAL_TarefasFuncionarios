package br.dev.joao.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import br.dev.joao.tarefas.dao.TarefaDao;
import br.dev.joao.tarefas.model.Tarefa;
import br.dev.joao.tarefas.utils.Utils;

public class TarefaListaFrame {

	private JLabel labelTitulo;
	private JButton btnSair;
	private JButton btnNovo;
	private JScrollPane scroll;
	private JTable tabelaTarefas;
	private DefaultTableModel model;
	private String[] colunas = {"ID", "TAREFA" ,"RESPONSÁVEL", "DATA DE INÍCIO", "DATA DE ENTREGA", "STATUS", "ENTREGUE"};
	
	public TarefaListaFrame(JFrame pai) {
		criarTela(pai);
	}
	
	public void criarTela(JFrame pai) {
		JDialog telaListaTarefas = new JDialog(pai, "Lista de tarefas" ,true);
		telaListaTarefas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		telaListaTarefas.setLayout(null);
		telaListaTarefas.setResizable(false);
		telaListaTarefas.setSize(700, 500);
		telaListaTarefas.setLocationRelativeTo(null);
		
		Container painel = telaListaTarefas.getContentPane();
		
		labelTitulo = new JLabel();
		labelTitulo.setText("Cadastro de Tarefas");
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.red);
		labelTitulo.setBounds(10, 10, 500, 40);
		
		model = new DefaultTableModel();
		tabelaTarefas = new JTable(model);
		
		scroll = new JScrollPane(tabelaTarefas);
		scroll.setBounds(10, 70, 680, 300);
		
		btnNovo = new JButton();
		btnNovo.setText("Cadastrar nova tarefa");
		btnNovo.setBounds(10, 400, 250, 50);
		
		btnSair =  new JButton();
		btnSair.setText("Sair");
		btnSair.setBounds(440, 400, 250, 50);
		
		carregarDadosTabela();
		
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaListaTarefas, "Atenção", "Sair do sistema?", JOptionPane.YES_NO_OPTION);
				
				if (resposta == 0) {
					telaListaTarefas.dispose();
				}
				
			}
		});
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefaFrame(telaListaTarefas);
				
			}
		});
		
		
		painel.add(labelTitulo);
		painel.add(scroll);
		painel.add(btnSair);
		painel.add(btnNovo);
		
		telaListaTarefas.setVisible(true);
	}
	
	private Object[][] carregarDadosTabela() {
		
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		TarefaDao dao = new TarefaDao();
		tarefas = dao.getTarefas();
		
		Object[][] dados = new Object[tarefas.size()][7];
		
//		"ID", "TAREFA" ,"RESPONSÁVEL", "DATA DE INÍCIO", "DATA DE ENTREGA", "STATUS", "ENTREGUE"
		int i = 0;
		for (Tarefa t : tarefas) {
			dados[i][0] = t.getId();
			dados[i][1] = t.getNome();
			dados[i][2] = t.getResponsavel();
			dados[i][3] = t.getDataInicio();
			dados[i][4] = t.getDataPrevistaEntrega();
			dados[i][5] = t.getStatus();
			dados[i][6] = 0;
			i++;
		}
		model.setDataVector(dados, colunas);
		
		return dados;
	}
	
	
	
	
}
