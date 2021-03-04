package finestre;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.ControllerRider;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinRider extends JFrame {

	private JPanel contentPane;
	private ControllerRider controller;

	public WinRider(ControllerRider contr) {
		controller=contr;
		setTitle("Men\u00F9 Rider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		setContentPane(contentPane);
		
		JButton btn_consegne = new JButton("Visualizza consegne");
		btn_consegne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.consegna();
			}
		});
		contentPane.add(btn_consegne);
		
		JButton btn_ordini = new JButton("Visualizza ordini");
		btn_ordini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ordini();
			}
		});
		contentPane.add(btn_ordini);
		
		JButton btn_cambiainfo = new JButton("Cambia informazioni account");
		btn_cambiainfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.info();
			}
		});
		contentPane.add(btn_cambiainfo);
		
		JButton btn_logout = new JButton("Esci");
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.logout();
			}
		});
		contentPane.add(btn_logout);
	}

}
