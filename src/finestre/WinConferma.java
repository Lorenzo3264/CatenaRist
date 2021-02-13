package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControllerCliente;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinConferma extends JFrame {
	
	
	private JPanel contentPane;
	private ControllerCliente controller;
	private JTextField textField;
	private JTextField textField_1;
	

	/**
	 * Create the frame.
	 * @param controllerCliente 
	 */
	public WinConferma(ControllerCliente controllerCliente) {
		setResizable(false);
		controller = controllerCliente;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rb_contanti = new JRadioButton("Contanti");
		rb_contanti.setBounds(10, 141, 109, 23);
		contentPane.add(rb_contanti);
		
		JRadioButton rb_cartaC = new JRadioButton("Carta di credito");
		rb_cartaC.setBounds(10, 167, 109, 23);
		contentPane.add(rb_cartaC);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rb_contanti);
		group.add(rb_cartaC);
		
		JLabel lbl_metodoP = new JLabel("Inserire metodo di pagamento");
		lbl_metodoP.setBounds(14, 118, 248, 14);
		contentPane.add(lbl_metodoP);
		
		JLabel lbl_prezzotot = new JLabel("Prezzo totale: ");
		lbl_prezzotot.setBounds(10, 11, 165, 14);
		contentPane.add(lbl_prezzotot);
		
		JLabel lbl_via = new JLabel("inserisci via");
		lbl_via.setBounds(14, 55, 105, 14);
		contentPane.add(lbl_via);
		
		textField = new JTextField();
		textField.setBounds(129, 52, 299, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 83, 299, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("inserisci civico");
		lblNewLabel.setBounds(14, 86, 105, 14);
		contentPane.add(lblNewLabel);
		
		JButton btn_conferma = new JButton("Conferma");
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.confermaC();
			}
		});
		btn_conferma.setBounds(335, 227, 89, 23);
		contentPane.add(btn_conferma);
		
		JButton btn_indietro = new JButton("Indietro");
		btn_indietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.indietroC();
			}
		});
		btn_indietro.setBounds(6, 227, 89, 23);
		contentPane.add(btn_indietro);
	}
}
