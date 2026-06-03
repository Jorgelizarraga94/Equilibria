package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.LogicaEquilibria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelIncompatibilidades extends JPanel {

	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEliminar;
	LogicaEquilibria logicaEquilibria;

	public PanelIncompatibilidades(LogicaEquilibria logica) {
		this.logicaEquilibria = logica;
		initialize();
	}

	private void initialize() {

		setLayout(new BorderLayout());

		setBorder(new TitledBorder("2. Incompatibilidades"));

		// TABLA
		tabla = new JTable();

		tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Persona 1", "Persona 2" }));

		JScrollPane scrollPane = new JScrollPane(tabla);

		add(scrollPane, BorderLayout.CENTER);

		// BOTONES
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarIncopatibilidades(e);
			}
		});

		btnEliminar = new JButton("Eliminar");

		panelBotones.add(btnEliminar);
		panelBotones.add(btnAgregar);

		add(panelBotones, BorderLayout.NORTH);
	}

	private void agregarIncopatibilidades(ActionEvent accion) {
		VentanaAgregarIncompatibilidades ventanaAgregarIncompatibilidades = new VentanaAgregarIncompatibilidades(
				logicaEquilibria, this);
		ventanaAgregarIncompatibilidades.setVisible(true);
		ventanaAgregarIncompatibilidades.setLocationRelativeTo(null);
	}

	public JTable getTabla() {
		return tabla;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
}