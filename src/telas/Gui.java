package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;

public class Gui {

	private JFrame frmMultiplicadorBinrio;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblResultado;
	private Component horizontalStrut;
	private Component verticalStrut;
	private JPanel panel;

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
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMultiplicadorBinrio = new JFrame();
		frmMultiplicadorBinrio.setTitle("Multiplicador Bin\u00E1rio - Bruno Noleto");
		frmMultiplicadorBinrio.setResizable(false);
		frmMultiplicadorBinrio.setBounds(100, 100, 640, 480);
		frmMultiplicadorBinrio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMultiplicadorBinrio.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("253px:grow"),
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("11px"),
				ColumnSpec.decode("31px"),
				ColumnSpec.decode("252px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("31px"),
				RowSpec.decode("14px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("22px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblMultiplicando = new JLabel("Multiplicando");
		frmMultiplicadorBinrio.getContentPane().add(lblMultiplicando, "2, 2, fill, top");
		
		JLabel lblMultiplicador = new JLabel("Multiplicador");
		frmMultiplicadorBinrio.getContentPane().add(lblMultiplicador, "6, 2, fill, top");
		
		textField = new JTextField();
		frmMultiplicadorBinrio.getContentPane().add(textField, "2, 4, fill, center");
		textField.setColumns(10);
		
		JLabel lblX = new JLabel("x");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmMultiplicadorBinrio.getContentPane().add(lblX, "4, 4, left, top");
		
		textField_1 = new JTextField();
		frmMultiplicadorBinrio.getContentPane().add(textField_1, "6, 4, fill, center");
		textField_1.setColumns(10);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		frmMultiplicadorBinrio.getContentPane().add(horizontalStrut, "7, 4, 2, 1");
		
		lblResultado = new JLabel("Resultado:");
		frmMultiplicadorBinrio.getContentPane().add(lblResultado, "2, 6");
		
		panel = new JPanel();
		frmMultiplicadorBinrio.getContentPane().add(panel, "2, 8, 5, 5, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("15dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),
				ColumnSpec.decode("25dlu"),},
			new RowSpec[] {}));
		
		verticalStrut = Box.createVerticalStrut(20);
		frmMultiplicadorBinrio.getContentPane().add(verticalStrut, "2, 13, 1, 22");
	}

}
