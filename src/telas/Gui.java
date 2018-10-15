package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;

import codigos.Calculadora;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.SystemColor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;


public class Gui extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmMultiplicadorBinrio;
	private JTextField tf_multiplicando;
	private JTextField tf_multiplicador;
	private JLabel lblResultado;
	private JTable tab_label_bits;
	private JTable tab_multis;
	private JLabel verticalStrut;
	private JTable tab_label_bits2;
	private JTable tab_resultado;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private JLabel lblNewLabel;
	private Calculadora calc = new Calculadora(16);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmMultiplicadorBinrio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public void atualizar_tabelas() {

		calc.set_labels(verticalStrut, lblNewLabel);
		
		int multiplicador = (tf_multiplicando.getText().isEmpty() || (tf_multiplicando.getText().length() == 1 && tf_multiplicando.getText().charAt(0) == '-')) ? 0 : Integer.parseInt(tf_multiplicando.getText());
		int multiplicando = (tf_multiplicador.getText().isEmpty() || (tf_multiplicador.getText().length() == 1 && tf_multiplicador.getText().charAt(0) == '-')) ? 0 : Integer.parseInt(tf_multiplicador.getText());
		
		String resultado = calc.multiplicar(multiplicador, multiplicando);
		
		//atualizará o multiplicando
		for(int i = 0; i<tab_multis.getModel().getColumnCount(); i++)
		tab_multis.getModel().setValueAt(calc.get_binario(0).charAt(i), 0, i);
		
		//atualizará o multiplicador
		for(int i = 0; i<tab_multis.getModel().getColumnCount(); i++)
		tab_multis.getModel().setValueAt(calc.get_binario(1).charAt(i), 1, i);
		
		//atualizará o resultado
		for(int i = 0; i<tab_multis.getModel().getColumnCount(); i++)
		tab_resultado.getModel().setValueAt(resultado.charAt(i), 0, i);
		
	}
	
	private DefaultTableModel funcoes_tabela(String[][] linhas, String[] colunas) {
		String[] nomes_colunas = colunas;
		String[][] valor_linhas = linhas;
		
		int qtd_colunas = colunas.length;
		
		//Object[][] tabela = new Object[qtd_linhas][qtd_colunas];
		boolean[] colunas_editaveis = new boolean[qtd_colunas];
		/*
		for(int i = 0; i<qtd_linhas; i++) {
			for(int j = 0; j<qtd_colunas; j++) {
				tabela[i][j]= '0';
			}
		}*/
		
		for(int i = 0; i<qtd_colunas; i++) {
			colunas_editaveis[i] = false;
		}
		
		DefaultTableModel model = new DefaultTableModel(
				valor_linhas,
				nomes_colunas
					) {
				/**
						 * 
						 */
						private static final long serialVersionUID = 1L;
				boolean[] columnEditables = colunas_editaveis;
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			
		return model;
	}
	
	private DefaultTableModel novaTabela(int qtd_linhas, int qtd_colunas) {
		String[] nomes_colunas = new String[qtd_colunas];
		String[][] valor_linhas = new String[qtd_linhas][qtd_colunas];
		for(int i = 0; i<qtd_colunas; i++) {
			nomes_colunas[i] = String.valueOf(i);
		}
		for(int i = 0; i<qtd_linhas; i++) {
			for(int j = 0; j<qtd_colunas; j++) {
				valor_linhas[i][j]= "0";
			}
		}
		
		return funcoes_tabela(valor_linhas, nomes_colunas);
		
	}
	
	private DefaultTableModel novaTabela(String[][] tabela) {
		String[] colunas = new String[tabela[0].length];
		for(int i = 0; i<tabela[0].length; i++) {
			colunas[i] = String.valueOf(i);
		}
		return funcoes_tabela(tabela, colunas);
	}
	
    public Gui() {
        
        initialize();
    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		calc.set_labels(verticalStrut, lblNewLabel);
		
		frmMultiplicadorBinrio = new JFrame();
		frmMultiplicadorBinrio.getContentPane().setBackground(SystemColor.control);
		frmMultiplicadorBinrio.setTitle("Multiplicador Bin\u00E1rio - Bruno Noleto");
		frmMultiplicadorBinrio.setResizable(false);
		frmMultiplicadorBinrio.setBounds(100, 100, 640, 480);
		frmMultiplicadorBinrio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {71, 71, 71, 71, 71, 71, 71, 71, 71, 71};
		gridBagLayout.rowHeights = new int[] {30, 30, 60, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmMultiplicadorBinrio.getContentPane().setLayout(gridBagLayout);
		
		verticalStrut_2 = Box.createVerticalStrut(30);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 0;
		frmMultiplicadorBinrio.getContentPane().add(verticalStrut_2, gbc_verticalStrut_2);
		
		JLabel lbl_multiplicando = new JLabel("Multiplicando");
		GridBagConstraints gbc_lbl_multiplicando = new GridBagConstraints();
		gbc_lbl_multiplicando.gridwidth = 2;
		gbc_lbl_multiplicando.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_multiplicando.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_multiplicando.gridx = 1;
		gbc_lbl_multiplicando.gridy = 1;
		frmMultiplicadorBinrio.getContentPane().add(lbl_multiplicando, gbc_lbl_multiplicando);
		
		JLabel lbl_multiplicador = new JLabel("Multiplicador");
		GridBagConstraints gbc_lbl_multiplicador = new GridBagConstraints();
		gbc_lbl_multiplicador.gridwidth = 2;
		gbc_lbl_multiplicador.anchor = GridBagConstraints.WEST;
		gbc_lbl_multiplicador.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_multiplicador.gridx = 6;
		gbc_lbl_multiplicador.gridy = 1;
		frmMultiplicadorBinrio.getContentPane().add(lbl_multiplicador, gbc_lbl_multiplicador);
		
		tf_multiplicando = new JTextField();
		tf_multiplicando.setHorizontalAlignment(SwingConstants.CENTER);
		tf_multiplicando.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				atualizar_tabelas();
			}
		});
		tf_multiplicando.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				JTextField caixa = (JTextField) arg0.getSource();
				
				if((caixa.getText().length() > 0 && caixa.getText().charAt(0) == '-' && arg0.getKeyChar() == '-') ||
						//caixa.getCaretPosition() != 0 && arg0.getKeyChar() == '-' ||
						(!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '-') ||
						caixa.getText().length() > 6 ||
						caixa.getText().contains("-") && Character.isDigit(arg0.getKeyChar()) && caixa.getCaretPosition() == 0) {
					arg0.consume();
					return;
				}
				if(arg0.getKeyChar() == '-') {
					caixa.setText("-"+caixa.getText());
					arg0.consume();
				}
				atualizar_tabelas();
			}
		});
		tf_multiplicando.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_tf_multiplicando = new GridBagConstraints();
		gbc_tf_multiplicando.fill = GridBagConstraints.BOTH;
		gbc_tf_multiplicando.gridwidth = 2;
		gbc_tf_multiplicando.insets = new Insets(0, 0, 5, 5);
		gbc_tf_multiplicando.gridx = 1;
		gbc_tf_multiplicando.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(tf_multiplicando, gbc_tf_multiplicando);
		tf_multiplicando.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 28));
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.fill = GridBagConstraints.BOTH;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 4;
		gbc_lblX.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(lblX, gbc_lblX);
		
		tf_multiplicador = new JTextField();
		tf_multiplicador.setHorizontalAlignment(SwingConstants.CENTER);
		tf_multiplicador.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				atualizar_tabelas();
			}
		});
		tf_multiplicador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				JTextField caixa = (JTextField) arg0.getSource();
				
				if((caixa.getText().length() > 0 && caixa.getText().charAt(0) == '-' && arg0.getKeyChar() == '-') ||
				//		caixa.getCaretPosition() != 0 && arg0.getKeyChar() == '-' ||
						(!Character.isDigit(arg0.getKeyChar()) && arg0.getKeyChar() != '-') ||
						caixa.getText().length() > 6 ||
						caixa.getText().contains("-") && Character.isDigit(arg0.getKeyChar()) && caixa.getCaretPosition() == 0
						) {
					arg0.consume();
					return;
				}
				if(arg0.getKeyChar() == '-') {
					caixa.setText("-"+caixa.getText());
					arg0.consume();
				}
				atualizar_tabelas();
			}
		});
		tf_multiplicador.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_tf_multiplicador = new GridBagConstraints();
		gbc_tf_multiplicador.gridwidth = 2;
		gbc_tf_multiplicador.fill = GridBagConstraints.BOTH;
		gbc_tf_multiplicador.insets = new Insets(0, 0, 5, 5);
		gbc_tf_multiplicador.gridx = 6;
		gbc_tf_multiplicador.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(tf_multiplicador, gbc_tf_multiplicador);
		tf_multiplicador.setColumns(10);
		
		verticalStrut = new JLabel("Ok!");
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.gridwidth = 7;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 6;
		frmMultiplicadorBinrio.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		lblResultado = new JLabel("Resultado:");
		GridBagConstraints gbc_lblResultado = new GridBagConstraints();
		gbc_lblResultado.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblResultado.insets = new Insets(0, 0, 5, 5);
		gbc_lblResultado.gridx = 1;
		gbc_lblResultado.gridy = 7;
		frmMultiplicadorBinrio.getContentPane().add(lblResultado, gbc_lblResultado);
		
		tab_label_bits = new JTable() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();

		    { // initializer block
		    	renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		    	renderCenter.setVerticalAlignment(SwingConstants.TOP);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
		        return renderCenter;
		    }
		};
		tab_label_bits.setEnabled(false);
		tab_label_bits.setFont(new Font("Tahoma", Font.BOLD, 11));
		tab_label_bits.setBackground(SystemColor.control);
		tab_label_bits.setShowGrid(false);
		tab_label_bits.setShowHorizontalLines(false);
		tab_label_bits.setShowVerticalLines(false);
		tab_label_bits.setRowSelectionAllowed(false);
		tab_label_bits.setRowHeight(30);
		String[][] label_bits = {{"S", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"}};
		tab_label_bits.setModel(novaTabela(label_bits));
		for(int i = 0; i<16; i++)
		tab_label_bits.getColumnModel().getColumn(i).setResizable(false);
		GridBagConstraints gbc_tab_label_bits = new GridBagConstraints();
		gbc_tab_label_bits.fill = GridBagConstraints.BOTH;
		gbc_tab_label_bits.insets = new Insets(0, 0, 5, 5);
		gbc_tab_label_bits.gridwidth = 7;
		gbc_tab_label_bits.gridx = 1;
		gbc_tab_label_bits.gridy = 4;
		frmMultiplicadorBinrio.getContentPane().add(tab_label_bits, gbc_tab_label_bits);
		
		tab_multis = new JTable() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();

		    { // initializer block
		    	renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		    	renderCenter.setVerticalAlignment(SwingConstants.TOP);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
		        return renderCenter;
		    }
		};
		tab_multis.setEnabled(false);
		tab_multis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab_multis.setBackground(SystemColor.control);
		tab_multis.setShowGrid(false);
		tab_multis.setShowHorizontalLines(false);
		tab_multis.setShowVerticalLines(false);
		tab_multis.setRowHeight(30);
		tab_multis.setModel(novaTabela(2,16));
		for(int i = 0; i<16; i++)
		tab_multis.getColumnModel().getColumn(i).setResizable(false);
		GridBagConstraints gbc_tab_multis = new GridBagConstraints();
		gbc_tab_multis.fill = GridBagConstraints.BOTH;
		gbc_tab_multis.insets = new Insets(0, 0, 5, 5);
		gbc_tab_multis.gridwidth = 7;
		gbc_tab_multis.gridx = 1;
		gbc_tab_multis.gridy = 5;
		frmMultiplicadorBinrio.getContentPane().add(tab_multis, gbc_tab_multis);
		
		tab_label_bits2 = new JTable() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();

		    { // initializer block
		    	renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		    	renderCenter.setVerticalAlignment(SwingConstants.TOP);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
		        return renderCenter;
		    }
		};
		tab_label_bits2.setEnabled(false);
		tab_label_bits2.setFont(new Font("Tahoma", Font.BOLD, 8));
		tab_label_bits2.setBackground(SystemColor.control);
		tab_label_bits2.setShowGrid(false);
		tab_label_bits2.setShowHorizontalLines(false);
		tab_label_bits2.setShowVerticalLines(false);
		tab_label_bits2.setRowSelectionAllowed(false);
		tab_label_bits2.setRowHeight(30);
		
		String[][] label_bits2 = {
				{"S","30","29","28","27","26","25","24","23","22","21","20","19","18","17","16", "15", "14", "13", "12", "11", "10", "9", "8", "7", "6", "5", "4", "3", "2", "1"}
				};
		
		tab_label_bits2.setModel(novaTabela(label_bits2));
		for(int i = 0; i<31; i++)
		tab_label_bits2.getColumnModel().getColumn(i).setResizable(false);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 8;
		frmMultiplicadorBinrio.getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);
		GridBagConstraints gbc_tab_label_bits2 = new GridBagConstraints();
		gbc_tab_label_bits2.fill = GridBagConstraints.BOTH;
		gbc_tab_label_bits2.insets = new Insets(0, 0, 5, 5);
		gbc_tab_label_bits2.gridwidth = 7;
		gbc_tab_label_bits2.gridx = 1;
		gbc_tab_label_bits2.gridy = 9;
		frmMultiplicadorBinrio.getContentPane().add(tab_label_bits2, gbc_tab_label_bits2);
		
		tab_resultado = new JTable() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();

		    { // initializer block
		    	renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		    	renderCenter.setVerticalAlignment(SwingConstants.TOP);
		    }

		    @Override
		    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
		        return renderCenter;
		    }
		};
		tab_resultado.setEnabled(false);
		tab_resultado.setForeground(SystemColor.textHighlight);
		tab_resultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tab_resultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab_resultado.setBackground(SystemColor.control);
		tab_resultado.setShowGrid(false);
		tab_resultado.setShowHorizontalLines(false);
		tab_resultado.setShowVerticalLines(false);
		tab_resultado.setRowHeight(30);
		tab_resultado.setModel(novaTabela(1,31));
		for(int i = 0; i<31; i++)
		tab_resultado.getColumnModel().getColumn(i).setResizable(false);
		GridBagConstraints gbc_tab_resultado = new GridBagConstraints();
		gbc_tab_resultado.fill = GridBagConstraints.BOTH;
		gbc_tab_resultado.insets = new Insets(0, 0, 5, 5);
		gbc_tab_resultado.gridwidth = 7;
		gbc_tab_resultado.gridx = 1;
		gbc_tab_resultado.gridy = 10;
		frmMultiplicadorBinrio.getContentPane().add(tab_resultado, gbc_tab_resultado);
		
		lblNewLabel = new JLabel("0 (base 10)");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 7;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 11;
		frmMultiplicadorBinrio.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
	}

}
