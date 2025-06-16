package br.dev.joao.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
	private JButton btnEntregar;
	private JScrollPane scroll;
	private JTable tabelaTarefas;
	private DefaultTableModel model;
	private JCheckBox[] vetorBox = null;
	private Object[][] dados;
	private String[] colunas = { "ID", "TAREFA", "DESCRIÇÃO", "RESPONSÁVEL", "DATA DE INÍCIO", "STATUS",
			"DATA PREVISTA DE ENTREGA", "DATA DE ENTREGA" };

	public TarefaListaFrame(JFrame pai) {
		criarTela(pai);
	}

	public void criarTela(JFrame pai) {
		JDialog telaListaTarefas = new JDialog(pai, "Lista de tarefas", true);
		telaListaTarefas.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		telaListaTarefas.setLayout(null);
		telaListaTarefas.setResizable(false);
		telaListaTarefas.setSize(1100, 700);
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
		scroll.setBounds(10, 70, 945, 500);

		btnNovo = new JButton();
		btnNovo.setText("Cadastrar nova tarefa");
		btnNovo.setBounds(10, 600, 250, 50);

		btnEntregar = new JButton();
		btnEntregar.setText("Entregar tarefas");
		btnEntregar.setBounds(460, 600, 250, 50);

		btnSair = new JButton();
		btnSair.setText("Sair");
		btnSair.setBounds(840, 600, 250, 50);

		carregarDadosTabela(painel);

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaListaTarefas, "Atenção", "Sair do sistema?",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					telaListaTarefas.dispose();
				}

			}
		});

		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TarefaFrame(telaListaTarefas);
				carregarDadosTabela(painel);

			}
		});

		btnEntregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				while (i < vetorBox.length) {

					if (vetorBox[i].isSelected() == true) {
						vetorBox[i].setVisible(false);
						vetorBox[i].setBounds(0, 0, 0, 0);
						DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate hoje = LocalDate.now();
						String hojeString = hoje.format(formato);
						model.setValueAt(hojeString, i, 7);
						model.setValueAt("Entregue", i, 5);

					}

					i++;
				}
			
			}
		});

		painel.add(labelTitulo);
		painel.add(scroll);
		painel.add(btnSair);
		painel.add(btnNovo);
		painel.add(btnEntregar);

		telaListaTarefas.setVisible(true);
	}

	private Object[][] carregarDadosTabela(Container painel) {

		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		TarefaDao dao = new TarefaDao();
		tarefas = dao.getTarefas();

		dados = new Object[tarefas.size()][8];
		vetorBox = new JCheckBox[tarefas.size()];

		int i = 0;
		int posicao = 88;
		for (Tarefa t : tarefas) {

			dados[i][0] = t.getId();
			dados[i][1] = t.getNome();
			dados[i][2] = t.getDescricao();
			dados[i][3] = t.getResponsavel();
			dados[i][4] = t.getDataInicioString();
			dados[i][5] = t.getStatus();
			dados[i][6] = t.getDataPrevistaString();
			dados[i][7] = "";

			vetorBox[i] = new JCheckBox();

			vetorBox[i].setBounds(955, posicao, 20, 20);
			posicao += 16;

			painel.add(vetorBox[i]);
			i++;
		}

		model.setDataVector(dados, colunas);
		return dados;
	}

}
