package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import logica.LogicaEquilibria;

import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaAgregarIncompatibilidades extends JFrame {

	LogicaEquilibria logicaEquilibria;
	PanelIncompatibilidades panelAgregarIncompatibilidades;

	public VentanaAgregarIncompatibilidades(LogicaEquilibria logica, PanelIncompatibilidades panelIncompatibilidades) {
		this.logicaEquilibria = logica;
		this.panelAgregarIncompatibilidades = panelIncompatibilidades;
		initialize();
	}

	private void initialize() {

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Persona 1");
		lblNewLabel.setBounds(84, 64, 78, 14);
		this.getContentPane().add(lblNewLabel);

		JLabel lblPersona = new JLabel("Persona 2");
		lblPersona.setBounds(84, 102, 78, 14);
		this.getContentPane().add(lblPersona);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 60, 154, 22);
		this.getContentPane().add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(172, 98, 154, 22);
		this.getContentPane().add(comboBox_1);

		JButton btnNewButton = new JButton("Generar incopatibilidad");
		btnNewButton.setBounds(132, 165, 178, 23);
		this.getContentPane().add(btnNewButton);

	}

}
