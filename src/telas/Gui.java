package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.builder.DefaultFormBuilder;

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

@SuppressWarnings("serial")
public class Gui extends JPanel{

	private JFrame frmMultiplicadorBinrio;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblResultado;
	private JTable tab_label_bits;
	private JTable tab_multis;
	private Component verticalStrut;
	private JTable tab_multis_decimal;

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
	@SuppressWarnings("serial")
	private void initialize() {
		frmMultiplicadorBinrio = new JFrame();
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
		
		JLabel lblMultiplicador = new JLabel("Multiplicador");
		GridBagConstraints gbc_lblMultiplicador = new GridBagConstraints();
		gbc_lblMultiplicador.gridwidth = 2;
		gbc_lblMultiplicador.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMultiplicador.insets = new Insets(0, 0, 5, 5);
		gbc_lblMultiplicador.gridx = 1;
		gbc_lblMultiplicador.gridy = 1;
		frmMultiplicadorBinrio.getContentPane().add(lblMultiplicador, gbc_lblMultiplicador);
		
		JLabel lblMultiplicando = new JLabel("Multiplicando");
		GridBagConstraints gbc_lblMultiplicando = new GridBagConstraints();
		gbc_lblMultiplicando.gridwidth = 2;
		gbc_lblMultiplicando.anchor = GridBagConstraints.WEST;
		gbc_lblMultiplicando.insets = new Insets(0, 0, 5, 5);
		gbc_lblMultiplicando.gridx = 6;
		gbc_lblMultiplicando.gridy = 1;
		frmMultiplicadorBinrio.getContentPane().add(lblMultiplicando, gbc_lblMultiplicando);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 28));
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.fill = GridBagConstraints.BOTH;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 4;
		gbc_lblX.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(lblX, gbc_lblX);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 2;
		frmMultiplicadorBinrio.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
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
		
		tab_multis_decimal = new JTable() {
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
		tab_multis_decimal.setEnabled(false);
		tab_multis_decimal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab_multis_decimal.setBackground(SystemColor.control);
		tab_multis_decimal.setShowGrid(false);
		tab_multis_decimal.setShowHorizontalLines(false);
		tab_multis_decimal.setShowVerticalLines(false);
		tab_multis_decimal.setRowHeight(30);
		tab_multis_decimal.setModel(novaTabela(2, 1));
		for(int i = 0; i<tab_multis_decimal.getModel().getColumnCount(); i++)
			tab_multis_decimal.getColumnModel().getColumn(i).setResizable(false);
		GridBagConstraints gbc_tab_multis_decimal = new GridBagConstraints();
		gbc_tab_multis_decimal.insets = new Insets(0, 0, 5, 5);
		gbc_tab_multis_decimal.gridx = 8;
		gbc_tab_multis_decimal.gridy = 5;
		frmMultiplicadorBinrio.getContentPane().add(tab_multis_decimal, gbc_tab_multis_decimal);
	}

}
