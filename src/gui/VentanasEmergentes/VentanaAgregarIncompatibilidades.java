package gui.VentanasEmergentes;

import javax.swing.*;
import entidades.Persona;
import gui.PanelPersona.PanelIncompatibilidades;
import logica.LogicaEquilibria;

public class VentanaAgregarIncompatibilidades extends JFrame {

	private static final long serialVersionUID = 1L;
	private LogicaEquilibria logicaEquilibria;
	private PanelIncompatibilidades panelAgregarIncompatibilidades;

	public VentanaAgregarIncompatibilidades(LogicaEquilibria logica, PanelIncompatibilidades panelIncompatibilidades) {
		this.logicaEquilibria = logica;
		this.panelAgregarIncompatibilidades = panelIncompatibilidades;
		initialize();
	}

	private void initialize() {

		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para que no se cierre toda la aplicacion al cerrar la
																// ventana
		this.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Persona 1");
		lblNewLabel.setBounds(84, 64, 78, 14);
		this.getContentPane().add(lblNewLabel);

		JLabel lblPersona = new JLabel("Persona 2");
		lblPersona.setBounds(84, 102, 78, 14);
		this.getContentPane().add(lblPersona);

		JComboBox<Persona> comboBoxPersona1 = new JComboBox<>();
		comboBoxPersona1.setBounds(172, 60, 154, 22);
		this.getContentPane().add(comboBoxPersona1);

		JComboBox<Persona> comboBoxPersona2 = new JComboBox<>();
		comboBoxPersona2.setBounds(172, 98, 154, 22);
		this.getContentPane().add(comboBoxPersona2);

		JButton btnGenerarIncompatibilidad = new JButton("Generar incompatibilidad");
		btnGenerarIncompatibilidad.setBounds(132, 165, 178, 23);
		this.getContentPane().add(btnGenerarIncompatibilidad);

		// Accion del boton para agregar la incompatibilidad
		btnGenerarIncompatibilidad.addActionListener((var e) -> {
			agregarIncompatibilidad(comboBoxPersona1, comboBoxPersona2);
		});

		for (Persona persona : logicaEquilibria.getPersonas().values()) {
			comboBoxPersona1.addItem(persona);
			comboBoxPersona2.addItem(persona);
		}
	}

	@SuppressWarnings("rawtypes")
	public void agregarIncompatibilidad(JComboBox comboBoxPersona1, JComboBox comboBoxPersona2) {
		Persona persona1 = (Persona) comboBoxPersona1.getSelectedItem();
		Persona persona2 = (Persona) comboBoxPersona2.getSelectedItem();

		if (!logicaEquilibria.esIncompatibilidadValida(persona1, persona2)) {

			JOptionPane.showMessageDialog(null, "Debe seleccionar dos personas distintas");

			return;
		}

		/*
		 * Para evitar que se agregue la misma incompatibilidad dos veces, sin importar
		 * el orden de las personas. Se llama a un metodo en LogicaEquilibria que
		 * verifica si ya existe esa incompatibilidad
		 */
		if (logicaEquilibria.existeIncompatibilidad(persona1, persona2)) {
			JOptionPane.showMessageDialog(null, "Esa incompatibilidad ya existe");
			return;
		}

		logicaEquilibria.agregarIncompatibilidad(persona1, persona2);
		panelAgregarIncompatibilidades.actualizarTabla();
		this.dispose();
	}
}
