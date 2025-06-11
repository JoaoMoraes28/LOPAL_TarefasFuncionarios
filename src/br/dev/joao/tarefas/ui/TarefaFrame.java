package br.dev.joao.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.dev.joao.tarefas.dao.TarefaDao;
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
	
	public  TarefaFrame() {
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
		
		descricao =  new JLabel();
		descricao.setText("Descrição");
		descricao.setBounds(10, 90, 90, 30);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 120, 400, 40);
		
		responsavel = new JLabel();
		responsavel.setText("Funcionário responsável");
		responsavel.setBounds(10, 170, 190, 30);
		
		txtResponsavel = new JTextField();
		txtResponsavel.setBounds(10, 200, 400, 40);
		
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
		
		
		btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				String nome = txtNome.getText();
				String descricao = txtDescricao.getText();
				String funcionario = txtResponsavel.getText();
				int prazo = Integer.parseInt(txtPrazo.getText());
//				LocalDate dataInicio = LocalDate.parse(txtDataInicio.getText());
				
				Tarefa tarefa = new Tarefa(nome, descricao, funcionario, prazo);
				
				new TarefaDao(tarefa);
				
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
		
		telaTarefa.setVisible(true);
		
		
		
		
	}







}

