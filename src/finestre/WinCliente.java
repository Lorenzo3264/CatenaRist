package finestre;
import controllers.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinCliente extends JFrame {

	private JPanel contentPane;
	private ControllerCliente controller;
	


	/**
	 * Create the frame.
	 */
	public WinCliente(ControllerCliente c) {
		controller = c;
		setResizable(false);
		setTitle("Men\u00F9 Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 253, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_ordina = new JButton("Ordina");
		btn_ordina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ordina();
			}
		});
		btn_ordina.setBounds(10, 35, 217, 23);
		contentPane.add(btn_ordina);
		
		JButton btnNewButton = new JButton("Cambia informazioni account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.info();
			}
		});
		btnNewButton.setBounds(10, 80, 217, 23);
		contentPane.add(btnNewButton);
		
		JButton btn_logout = new JButton("Esci");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		btn_logout.setBounds(10, 127, 217, 23);
		contentPane.add(btn_logout);
	}
}
