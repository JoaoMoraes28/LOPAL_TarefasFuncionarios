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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.dev.joao.tarefas.dao.FuncionarioDAO;
import br.dev.joao.tarefas.dao.TarefaDao;
import br.dev.joao.tarefas.model.Funcionario;
import br.dev.joao.tarefas.model.Status;
import br.dev.joao.tarefas.model.Tarefa;
import br.dev.joao.tarefas.utils.Utils;

public class TarefaFrame {

	private JLabel id;
	private JTextField txtId;
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
	private JButton btnSair;
	private JButton btnSelecionarFuncionario;
	private JButton btnEscolherResponsavel;
	private ButtonGroup grupo;
	private JPanel painelEscolha;
	private JRadioButton[] botaoOpcoes;
	private JList listaBotoes;
	private JScrollPane scroll;

	public TarefaFrame(JDialog pai) {
		criarTela(pai);

	}

	public void criarTela(JDialog pai) {

		JDialog telaTarefa = new JDialog(pai, "Cadastro de Tarefas", true);
		telaTarefa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		telaTarefa.setSize(500, 850);
		telaTarefa.setResizable(false);
		telaTarefa.setLayout(null);
		telaTarefa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		telaTarefa.setLocationRelativeTo(null);

		Container painel = telaTarefa.getContentPane();

		id = new JLabel();
		id.setText("ID");
		id.setBounds(10, 10, 400, 30);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText(Utils.gerarUUID8());
		txtId.setBounds(10, 40, 400, 40);

		nome = new JLabel();
		nome.setText("Nome");
		nome.setBounds(10, 90, 50, 30);

		txtNome = new JTextField();
		txtNome.setBounds(10, 120, 400, 40);

		descricao = new JLabel();
		descricao.setText("Descrição");
		descricao.setBounds(10, 170, 90, 30);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 200, 400, 40);

		responsavel = new JLabel();
		responsavel.setText("Funcionário responsável");
		responsavel.setBounds(10, 250, 190, 30);

		txtResponsavel = new JTextField();
		txtResponsavel.setEditable(false);
		txtResponsavel.setBounds(10, 280, 300, 40);

		dataInicio = new JLabel();
		dataInicio.setText("Data de Início");
		dataInicio.setBounds(10, 330, 190, 30);

		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(10, 360, 200, 40);

		status = new JLabel();
		status.setText("Status da Tarefa");
		status.setBounds(10, 410, 120, 30);

		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setBounds(10, 440, 130, 40);

		prazo = new JLabel();
		prazo.setText("Prazo");
		prazo.setBounds(10, 490, 70, 30);

		txtPrazo = new JTextField();
		txtPrazo.setBounds(10, 520, 200, 40);

		dataEntrega = new JLabel();
		dataEntrega.setText("Data de entrega");
		dataEntrega.setBounds(10, 570, 120, 30);

		txtDataEntrega = new JTextField();
		txtDataEntrega.setEditable(false);
		txtDataEntrega.setBounds(10, 600, 200, 40);

		btnSalvar = new JButton();
		btnSalvar.setText("Salvar");
		btnSalvar.setBounds(10, 700, 250, 60);

		btnSair = new JButton();
		btnSair.setText("Sair");
		btnSair.setBounds(270, 700, 150, 60);

		btnSelecionarFuncionario = new JButton();
		btnSelecionarFuncionario.setText("Funcionarios");
		btnSelecionarFuncionario.setBounds(310, 280, 120, 40);

		painelEscolha = new JPanel();
		painelEscolha.setBackground(Color.lightGray);
		painelEscolha.setVisible(false);
		painelEscolha.setLayout(new BoxLayout(painelEscolha, BoxLayout.Y_AXIS));
		painelEscolha.setBounds(280, 320, 150, 300);

		btnEscolherResponsavel = new JButton();
		btnEscolherResponsavel.setText("Selecionar");
		btnEscolherResponsavel.setBounds(330, 620, 100, 30);
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

				try {

					String id = txtId.getText();
					String nome = txtNome.getText();
					String descricao = txtDescricao.getText();
					String func = txtResponsavel.getText();
					int prazo = Integer.parseInt(txtPrazo.getText());
					String dataInicioString = txtDataInicio.getText();

					id = id.replace(",", "");
					nome = nome.replace(",", "");
					descricao = descricao.replace(",", "");
					func = func.replace(",", "");
					dataInicioString = dataInicioString.replace(",", "");

					Tarefa tarefa = new Tarefa(id, nome, descricao, func, prazo, dataInicioString);

					LocalDate dataPrevistaEntrega = tarefa.getDataPrevistaEntrega();
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String dataPrevistaString = dataPrevistaEntrega.format(formato);

					txtDataEntrega.setText(dataPrevistaString);

					tarefa.getStatus();

					txtStatus.setText(tarefa.getStatus());

					TarefaDao dao = new TarefaDao();
					boolean confirmarcao = dao.gravarTarefa(tarefa);

					if (confirmarcao) {
						JOptionPane.showMessageDialog(telaTarefa, "Tarefa gravada com sucesso");
						limparFormulario();

					} else {
						JOptionPane.showMessageDialog(telaTarefa,
								"Ocorreu um erro na gravação da Tarefa\n Entre em contato com o suporte");
					}

				} catch (Exception e2) {
					System.out.println("error");
				}

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

				try {
					int i = 0;
					while (botaoOpcoes[i].isSelected() == false) {
						i++;
					}

					txtResponsavel.setText(botaoOpcoes[i].getText());
					painelEscolha.setVisible(false);
					btnEscolherResponsavel.setVisible(false);

				} catch (Exception e2) {
					System.out.println("ERROR");
				}

			}
		});

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(telaTarefa, "Atenção", "Sair do Sistema?",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					telaTarefa.dispose();
				}
			}
		});

		painel.add(id);
		painel.add(txtId);
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
		painel.add(btnSair);
		painel.add(btnSelecionarFuncionario);
		painel.add(painelEscolha);
		painel.add(btnEscolherResponsavel);
		painel.add(scroll);

		telaTarefa.setVisible(true);

	}

	public void limparFormulario() {

		txtId.setText(Utils.gerarUUID8());
		txtNome.setText(null);
		txtDescricao.setText(null);
		txtResponsavel.setText(null);
		txtDataInicio.setText(null);
		txtStatus.setText(null);
		txtPrazo.setText(null);
		txtDataEntrega.setText(null);
	}

}
