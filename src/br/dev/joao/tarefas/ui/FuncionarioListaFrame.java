package br.dev.joao.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.model.Funcionario;

public class FuncionarioListaFrame {

	
	private JLabel labelTitulo;
	private JButton btnNovo;
	private JButton btnSair;
	
	private DefaultTableModel model; // Dados da tabela
	private JTable tabelaFuncionarios; // Tabela visualmente
	private JScrollPane scrollFuncionarios; // Containêr da tabela
	String[] colunas = {"CÓDIGO", "NOME DO FUNCIONÁRIO", "CARGO"};
	
	public FuncionarioListaFrame(JFrame pai) {
		criarTela(pai);
	}
	
	private void criarTela(JFrame pai) {
		JDialog telaFuncionarioLista = new JDialog(pai , "Lista de Funcionários", true);
		telaFuncionarioLista.setSize(700, 500);
		telaFuncionarioLista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaFuncionarioLista.setLayout(null);
		telaFuncionarioLista.setLocationRelativeTo(null);
		telaFuncionarioLista.setResizable(false);
		
		Container painel = telaFuncionarioLista.getContentPane();
		
		labelTitulo = new JLabel("Cadastro de Funcionários");
		labelTitulo.setBounds(10, 10, 500, 40);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 32));
		labelTitulo.setForeground(Color.red);
		
		// Criar a tabela
		
		model = new DefaultTableModel(colunas, 10);
		tabelaFuncionarios = new JTable(model);
		
		scrollFuncionarios = new JScrollPane(tabelaFuncionarios);
		scrollFuncionarios.setBounds(10, 70, 680, 300);
		
		btnNovo = new JButton();
		btnNovo.setText("Cadastrar novo funcionário");
		btnNovo.setBounds(10, 400, 250, 50);
		
		btnSair = new JButton();
		btnSair.setText("SAIR");
		btnSair.setBounds(440, 400, 250, 50);
		
		carregarDadosTabela();
		
		painel.add(labelTitulo);
		painel.add(scrollFuncionarios);
		painel.add(btnNovo);
		painel.add(btnSair);
		
		btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new FuncionarioFrame(telaFuncionarioLista);
				carregarDadosTabela();
				
			}
		});
		
		
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaFuncionarioLista, "Sair do sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
				
				
				if (resposta == 0) {
					telaFuncionarioLista.dispose();
				}
				
				
			}
				
			
		});
		
		
		
		
		
		
		telaFuncionarioLista.setVisible(true);
		
		
	}

	
	
	
	public Object[][] carregarDadosTabela() {
		
		List<Funcionario> funcionarios = new ArrayList<>();
		FuncionarioDAO dao = new FuncionarioDAO(null);
		funcionarios = dao.getFuncionarios();
		
		int i = 0;
		
		Object[][] dados = new Object[funcionarios.size()][3];
		
		for(Funcionario f : funcionarios) {
			dados[i][0] = f.getMatricula().toUpperCase();
			dados[i][1] = f.getNome();
			dados[i][2] = f.getCargo();
			i++;
					
		}
		
		model.setDataVector(dados, colunas);
		return dados;
		
		
	}
	
	
	
	
}
