package br.com.capes.view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.capes.controller.Pessoa;
import br.com.capes.controller.Programa;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Classe responsável pela interface do sistema.
 *
 * @author Bruno E. Silveira
 * @version 1.0
 */
public class Tela {

	private JFrame frame;

	/**
	 * O atributo é inicializado para o uso do JOptionPane.
	 */
	static ImageIcon iconDisplay = new ImageIcon();
	private JTextField txtNome;
	private JTextField txtMedAnual;
	private JTextField txtAnoConsultaBolsaZero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("CAPES - Bolsistas");
		frame.setBounds(100, 100, 700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Programa programa = new Programa();

		JButton btnSair = new JButton("5) Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton btnRanking = new JButton("4) Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Pessoa> rankingAlto = programa.rankingValoresAltos();
				List<Pessoa> rankingBaixo = programa.rankingValoresBaixos();
				msgTela("[Ranking das bolsas com maior valor]\n" + "1º " + rankingAlto.get(0).getNome() + " - R$ "
						+ rankingAlto.get(0).getValor() + "\n" + "2º " + rankingAlto.get(1).getNome() + " - R$ "
						+ rankingAlto.get(1).getValor() + "\n" + "3º " + rankingAlto.get(2).getNome() + " - R$ "
						+ rankingAlto.get(2).getValor() + "\n\n" + "[Ranking das bolsas com menor valor]\n" + "1º "
						+ rankingBaixo.get(0).getNome() + " - R$ " + rankingBaixo.get(0).getValor() + "\n" + "2º "
						+ rankingBaixo.get(1).getNome() + " - R$ " + rankingBaixo.get(1).getValor() + "\n" + "3º "
						+ rankingBaixo.get(2).getNome() + " - R$ " + rankingBaixo.get(2).getValor() + "\n" + "");
			}
		});

		JButton btnMedAnual = new JButton("3) M\u00E9dia do Valor da Bolsa");
		btnMedAnual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarDigito(txtMedAnual.getText()) && txtMedAnual.getText().length() > 1) {
					int ano = Integer.parseInt(txtMedAnual.getText());
					int valor = programa.mediaAnual(ano);
					if (valor > 0)
						msgTela("A média do ano [" + ano + "] é de R$ " + valor);
					else
						msgTela("Não há dados disponíveis no ano informado.");
				} else
					msgTela("Você só pode digitar números no campo Ano e o mesmo não pode estar vazio.");
			}
		});

		JList<Object> list = new JList<Object>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 13));
		list.setVisibleRowCount(100);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Pessoa p = (Pessoa) list.getSelectedValue();
				msgTela("Nome Codificado: " + programa.codificarNome(p.getNome()) + "\n" + "Ano: " + p.getAno() + "\n" + "Entidade de Ensino: "
						+ p.getEntidade() + "\n" + "Valor da Bolsa: " + p.getValor());

			}
		});

		txtNome = new JTextField();
		txtNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				list.setVisible(false);
			}
		});
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				list.setVisible(true);
				List<Pessoa> lista = programa.pesquisarNome(txtNome.getText());
				if (lista != null) {
					Object[] array = lista.toArray();
					list.setListData(array);
				}
			}
		});

		JButton btnPesquisarNome = new JButton("2) Pesquisar Nome");
		btnPesquisarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Pessoa> lista = programa.pesquisarNome(txtNome.getText());
				if (lista == null) {
					msgTela("Não foi encontrado nenhum bolsista.");
				} else if (txtNome.getText().length() < 1)
					msgTela("O campo não pode estar vazio");
				else if (txtNome.getText().length() < 4)
					msgTela("Você deve escrever pelo menos 4 letras");
				else if (programa.pesquisarNome(txtNome.getText()).get(0) == null)
					msgTela("Bolsista não encontrado");
				else {
					Pessoa p = programa.pesquisarNome(txtNome.getText()).get(0);
					msgTela("Nome Codificado: " + programa.codificarNome(p.getNome()) + "\n" + "Ano: " + p.getAno() + "\n"
							+ "Entidade de Ensino: " + p.getEntidade() + "\n" + "Valor da Bolsa: " + p.getValor());
				}

			}
		});

		JButton btnBolsaZero = new JButton("1) Consultar Bolsa Zero");
		btnBolsaZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificarDigito(txtAnoConsultaBolsaZero.getText()) && txtAnoConsultaBolsaZero.getText().length() > 1) {
					int ano = Integer.parseInt(txtAnoConsultaBolsaZero.getText());
					Pessoa pessoa = programa.buscarBolsaZero(ano);
					if (pessoa != null)
						msgTela("O primeiro bolsista do ano de " + ano + ".\n" + "Nome: " + pessoa.getNome() + "\n"
								+ "CPF: " + pessoa.getCpf() + "\n" + "Entidade de Ensino: " + pessoa.getEntidade()
								+ "\n" + "Valor da Bolsa: " + pessoa.getValor());
					else
						msgTela("Não há dados disponíveis no ano informado.");
				} else
					msgTela("Você só pode digitar números no campo Ano e o mesmo não pode estar vazio.");
			}
		});
		btnBolsaZero.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnBolsaZero.setBounds(38, 50, 214, 35);
		frame.getContentPane().add(btnBolsaZero);

		txtAnoConsultaBolsaZero = new JTextField();
		txtAnoConsultaBolsaZero.setText("2013");
		txtAnoConsultaBolsaZero.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnoConsultaBolsaZero.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtAnoConsultaBolsaZero.setColumns(10);
		txtAnoConsultaBolsaZero.setBounds(262, 50, 86, 35);
		frame.getContentPane().add(txtAnoConsultaBolsaZero);

		JLabel lblAno_1 = new JLabel("Ano");
		lblAno_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAno_1.setBounds(358, 60, 46, 14);
		frame.getContentPane().add(lblAno_1);

		btnPesquisarNome.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnPesquisarNome.setBounds(38, 100, 214, 35);
		frame.getContentPane().add(btnPesquisarNome);
		txtNome.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtNome.setBounds(262, 100, 390, 35);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		list.setBounds(262, 136, 390, 149);
		frame.getContentPane().add(list);
		list.setVisible(false);
		btnMedAnual.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnMedAnual.setBounds(38, 150, 214, 35);
		frame.getContentPane().add(btnMedAnual);

		txtMedAnual = new JTextField();
		txtMedAnual.setHorizontalAlignment(SwingConstants.CENTER);
		txtMedAnual.setText("2013");
		txtMedAnual.setFont(new Font("Verdana", Font.PLAIN, 13));
		txtMedAnual.setColumns(10);
		txtMedAnual.setBounds(262, 150, 86, 35);
		frame.getContentPane().add(txtMedAnual);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAno.setBounds(358, 160, 46, 14);
		frame.getContentPane().add(lblAno);
		btnRanking.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnRanking.setBounds(38, 200, 214, 35);
		frame.getContentPane().add(btnRanking);
		btnSair.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnSair.setBounds(38, 250, 214, 35);
		frame.getContentPane().add(btnSair);
	}

	/**
	 * Método para verificar se o que foi digitado é número.
	 * 
	 * @param string Parâmetro do dado que foi passado.
	 * @return Retorno booleano para informar se há algum caractere diferente de
	 *         número.
	 */
	boolean verificarDigito(String string) {

		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método simplicado do JOptionPane.showInputDialog para retornar uma
	 * confirmação na tela do usuário.
	 * 
	 * @param string Parâmetro responsável pelo texto que vai aparecer na tela.
	 * @return Retorna uma tela de confirmação para o usuário.
	 */
	public Object inputTela(String string) {
		return JOptionPane.showInputDialog(null, string, "CAPES - Bolsistas", JOptionPane.DEFAULT_OPTION, iconDisplay,
				null, null);
	}

	/**
	 * Método simplicado do JOptionPane.showMessageDialog para retornar uma mensagem
	 * na tela do usuário.
	 * 
	 * @param string Parâmetro responsável pelo texto que vai aparecer na tela.
	 */
	public void msgTela(String string) {
		JOptionPane.showMessageDialog(null, string, "CAPES - Bolsistas", JOptionPane.INFORMATION_MESSAGE, iconDisplay);
	}
}
