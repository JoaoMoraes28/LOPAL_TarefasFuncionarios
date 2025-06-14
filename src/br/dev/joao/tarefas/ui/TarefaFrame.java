package br.dev.joao.tarefas.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.dao.TarefaDao;
import br.dev.joao.tarefas.model.Funcionario;
import br.dev.joao.tarefas.model.Status;
import br.dev.joao.tarefas.model.Tarefa;

public class TarefaFrame {

	private JLabel nome;
	private JLabel descricao;
	private JLabel responsavel;
	private JLabel dataInicio;
	private JLabel status;
	private JLabel prazo;
	private JLabel dataEntrega;
	private JTextField txtNome;
	private JTextField txtDescricao;
	private JTextField txtResponsavel;
	private JTextField txtDataInicio;
	private JTextField txtStatus;
	private JTextField txtPrazo;
	private JTextField txtDataEntrega;
	private JButton btnSalvar;
	private JButton btnSelecionarFuncionario;
	private JButton btnEscolherResponsavel;
	private ButtonGroup grupo;
	private JPanel painelEscolha;
	private JRadioButton[] botaoOpcoes;
	private JList listaBotoes;
	private JScrollPane scroll;

	public TarefaFrame() {
		criarTela();

	}

	public void criarTela() {

		JDialog telaTarefa = new JDialog();
		telaTarefa.setSize(500, 800);
		telaTarefa.setResizable(false);
		telaTarefa.setLayout(null);
		telaTarefa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		telaTarefa.setLocationRelativeTo(null);

		Container painel = telaTarefa.getContentPane();

		nome = new JLabel();
		nome.setText("Nome");
		nome.setBounds(10, 10, 50, 30);

		txtNome = new JTextField();
		txtNome.setBounds(10, 40, 400, 40);

		descricao = new JLabel();
		descricao.setText("Descrição");
		descricao.setBounds(10, 90, 90, 30);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 120, 400, 40);

		responsavel = new JLabel();
		responsavel.setText("Funcionário responsável");
		responsavel.setBounds(10, 170, 190, 30);

		txtResponsavel = new JTextField();
		txtResponsavel.setEditable(false);
		txtResponsavel.setBounds(10, 200, 300, 40);

		dataInicio = new JLabel();
		dataInicio.setText("Data de Início");
		dataInicio.setBounds(10, 250, 190, 30);

		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(10, 280, 200, 40);

		status = new JLabel();
		status.setText("Status da Tarefa");
		status.setBounds(10, 330, 120, 30);

		txtStatus = new JTextField();
		txtStatus.setBounds(10, 360, 130, 40);

		prazo = new JLabel();
		prazo.setText("Prazo");
		prazo.setBounds(10, 410, 70, 30);

		txtPrazo = new JTextField();
		txtPrazo.setBounds(10, 440, 200, 40);

		dataEntrega = new JLabel();
		dataEntrega.setText("Data de entrega");
		dataEntrega.setBounds(10, 490, 120, 30);

		txtDataEntrega = new JTextField();
		txtDataEntrega.setBounds(10, 520, 200, 40);

		btnSalvar = new JButton();
		btnSalvar.setText("Salvar");
		btnSalvar.setBounds(240, 620, 250, 60);

		btnSelecionarFuncionario = new JButton();
		btnSelecionarFuncionario.setText("Funcionarios");
		btnSelecionarFuncionario.setBounds(310, 200, 120, 40);

		painelEscolha = new JPanel();
		painelEscolha.setBackground(Color.lightGray);
		painelEscolha.setVisible(false);
		painelEscolha.setLayout(new BoxLayout(painelEscolha, BoxLayout.Y_AXIS));
		painelEscolha.setBounds(280, 240, 150, 300);

		btnEscolherResponsavel = new JButton();
		btnEscolherResponsavel.setText("Selecionar");
		btnEscolherResponsavel.setBounds(330, 540, 100, 30);
		btnEscolherResponsavel.setVisible(false);

		grupo = new ButtonGroup();

		FuncionarioDAO listaFuncionarios = new FuncionarioDAO();
		List<Funcionario> funcionarios = listaFuncionarios.getFuncionarios();
		botaoOpcoes = new JRadioButton[funcionarios.size()];

		int i = 0;
		for (Funcionario funcionario : funcionarios) {
			botaoOpcoes[i] = new JRadioButton(funcionario.getNome());
			botaoOpcoes[i].setBounds(240, 250, 200, 30);
			grupo.add(botaoOpcoes[i]);
			painelEscolha.add(botaoOpcoes[i]);
			i++;

		}

		scroll = new JScrollPane(painelEscolha);
		scroll.setVisible(false);

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String nome = txtNome.getText();
				String descricao = txtDescricao.getText();
				String func = txtResponsavel.getText();
				int prazo = Integer.parseInt(txtPrazo.getText());
				String dataInicioString = txtDataInicio.getText();

				Tarefa tarefa = new Tarefa(nome, descricao, func, prazo, dataInicioString);
					
				LocalDate dataPrevistaEntrega = tarefa.getDataPrevistaEntrega();
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String dataPrevistaString = dataPrevistaEntrega.format(formato);
				
				txtDataEntrega.setText(dataPrevistaString);
			
				tarefa.getStatus();
				
				txtStatus.setText(tarefa.getStatusString());
				
				new TarefaDao(tarefa);
			}
		});

		btnSelecionarFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				painelEscolha.setVisible(true);
				btnEscolherResponsavel.setVisible(true);
			}
		});

		btnEscolherResponsavel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int i = 0;
				while (botaoOpcoes[i].isSelected() == false) {
					i++;
				}

				txtResponsavel.setText(botaoOpcoes[i].getText());
				painelEscolha.setVisible(false);
				btnEscolherResponsavel.setVisible(false);

			}
		});

		painel.add(nome);
		painel.add(txtNome);
		painel.add(descricao);
		painel.add(txtDescricao);
		painel.add(responsavel);
		painel.add(txtResponsavel);
		painel.add(dataInicio);
		painel.add(txtDataInicio);
		painel.add(status);
		painel.add(txtStatus);
		painel.add(prazo);
		painel.add(txtPrazo);
		painel.add(dataEntrega);
		painel.add(txtDataEntrega);
		painel.add(btnSalvar);
		painel.add(btnSelecionarFuncionario);
		painel.add(painelEscolha);
		painel.add(btnEscolherResponsavel);
		painel.add(scroll);

		telaTarefa.setVisible(true);

	}

}
