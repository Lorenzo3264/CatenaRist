package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControllerManager;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinManager extends JFrame {

	private JPanel contentPane;
	private ControllerManager controller;

	public WinManager(ControllerManager controllerManager) {
		controller = controllerManager;
		setTitle("Men\u00F9 Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btn_ordini = new JButton("Visualizza ordini");
		btn_ordini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.visualizzaOrdini();
			}
		});
		contentPane.add(btn_ordini);
		
		JButton btn_dipendenti = new JButton("Visualizza dipendenti");
		btn_dipendenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerManager.visualizzaDipendenti();
			}
		});
		contentPane.add(btn_dipendenti);
		
		JButton btn_update = new JButton("Cambia informazioni account");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.info();
			}
		});
		contentPane.add(btn_update);
		
		JButton btn_logout = new JButton("Esci");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		contentPane.add(btn_logout);
	}

}
