package gui.PanelCalculo;

import entidades.Persona;
import logica.LogicaEquilibria;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PrivateKey;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelResultado extends JPanel {
	private LogicaEquilibria logicaEquilibria;
	private JTable tabla;

	public PanelResultado(LogicaEquilibria logicaEquilibria) {
		this.logicaEquilibria = logicaEquilibria;
		initialize();
	}

	private void initialize() {

		setBorder(new TitledBorder("5. Equipo Resultante"));
		setLayout(null);

		tabla = new JTable();

		tabla.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Rol", "Calificación", "Foto" }));

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(6, 34, 916, 355);

		add(scrollPane);

		JComboBox comboBoxSeleccionAlgoritmo = new JComboBox();
		comboBoxSeleccionAlgoritmo
				.setModel(new DefaultComboBoxModel(new String[] { "BackTracking", "Fuerza Bruta", "Heuristica" }));
		comboBoxSeleccionAlgoritmo.setBounds(756, 11, 166, 22);
		add(comboBoxSeleccionAlgoritmo);

		comboBoxSeleccionAlgoritmo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String algoritmoSeleccionado = (String) comboBoxSeleccionAlgoritmo.getSelectedItem();

				actualizarResultado(algoritmoSeleccionado);

			}

		});
	}

	public void mostrarEquipo(List<Persona> equipo) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);

		for (Persona p : equipo) {
			Object[] fila = new Object[] { p.getNombre(), p.getRol(), p.getCalificacion(), p.getFoto() };
			modelo.addRow(fila);
		}
	}

	public void actualizarResultado(String algoritmoSeleccionado) {
		switch (algoritmoSeleccionado) {
		case "Backtracking":
			mostrarEquipo(logicaEquilibria.getResultadoBT());

			// 1. Conseguís los datos que calculó Backtracking
			// 2. Pasás los datos a la tabla (como vimos con el Map o lista)
			// Ejemplo: cargarDatosTabla(resultadoBacktracking, table);
			System.out.println("Mostrando resultado de Backtracking...");
			break;

		case "Fuerza Bruta":
			mostrarEquipo(logicaEquilibria.getResultadoFB());

			// Lo mismo para Fuerza Bruta
			// Ejemplo: cargarDatosTabla(resultadoFuerzaBruta, table);
			System.out.println("Mostrando resultado de Fuerza Bruta...");
			break;

		case "Heuristica":
			// Lo mismo para la Heurística
			// Ejemplo: cargarDatosTabla(resultadoHeuristica, table);
			System.out.println("Mostrando resultado de Heurística...");
			break;

		default:
			break;
		}
	}

	public JTable getTabla() {
		return tabla;
	}
}