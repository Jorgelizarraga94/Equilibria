package gui.PanelPersona;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import gui.VentanasEmergentes.VentanaAgregarIncompatibilidades;
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

		tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id", "Persona 1", "Persona 2" }));

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
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarIncompatibilidad(e);
			}
		});

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

	// Actualizar la tabla
	public void actualizarTabla() {

		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

		modelo.setRowCount(0);

		for (var incompatibilidad : logicaEquilibria.getIncompatibilidades()) {

			modelo.addRow(new Object[] { incompatibilidad.getid(), incompatibilidad.getPersona1().getNombre(),
					incompatibilidad.getPersona2().getNombre() });
		}
	}

	private void eliminarIncompatibilidad(ActionEvent e) {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int filaModelo = tabla.convertRowIndexToModel(filaSeleccionada);
			logicaEquilibria.eliminarIncopatibilidad(filaModelo);
			actualizarTabla();

			JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
		}
	}

	public JTable getTabla() {
		return tabla;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
}