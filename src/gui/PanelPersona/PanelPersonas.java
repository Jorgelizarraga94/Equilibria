package gui.PanelPersona;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Persona;
import gui.VentanaPrincipal;
import gui.VentanasEmergentes.VentanaAgregarPersona;
import logica.LogicaEquilibria;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPersonas extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private LogicaEquilibria logicaEquilibria;
	private VentanaPrincipal ventanaPrincipal;

	public PanelPersonas(LogicaEquilibria logica, VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.logicaEquilibria = logica;
		initialize();
	}

	private void initialize() {

		setLayout(new BorderLayout());

		setBorder(new TitledBorder("1. Personas Disponibles"));

		// TABLA
		tabla = new JTable();
		tabla.setRowSelectionAllowed(false);
		tabla.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nombre", "Rol", "Calificación"}));

		JScrollPane scrollPane = new JScrollPane(tabla);
		add(scrollPane, BorderLayout.CENTER);

		// BOTONES
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualizarVentanaAgregarPersona(e);
			}
		});

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPersonaInterfaz(e);
			}
		});

		panelBotones.add(btnAgregar);
		panelBotones.add(btnEliminar);
		add(panelBotones, BorderLayout.NORTH);
	}

	private void visualizarVentanaAgregarPersona(ActionEvent accion) {
		VentanaAgregarPersona agregarPersona = new VentanaAgregarPersona(logicaEquilibria, this);
		ventanaPrincipal.deshabilitarComboBoxResultado();
		ventanaPrincipal.habilitarBotonesPanelResolver();
		agregarPersona.setVisible(true);
		agregarPersona.setLocationRelativeTo(null);
	}

	private void eliminarPersonaInterfaz(ActionEvent e) {
		ventanaPrincipal.deshabilitarComboBoxResultado();
		ventanaPrincipal.habilitarBotonesPanelResolver();
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int filaModelo = tabla.convertRowIndexToModel(filaSeleccionada);
			Long dato = (Long) tabla.getModel().getValueAt(filaModelo, 0);
			logicaEquilibria.eliminarPersona(dato);
			refrescarTabla();
			JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
		}
	}

	public void refrescarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);
		for (Persona persona : logicaEquilibria.getPersonas().values()) {
			modelo.addRow(new Object[] { persona.getId(), persona.getNombre(), persona.getRol(),
					persona.getCalificacion(), persona.getFoto() });
		}
	}

	public JTable getTabla() {
		return tabla;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
}