package gui.PanelPersona;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Persona;
import entidades.Requerimiento;
import gui.VentanasEmergentes.VentanaAgregarRequerimientos;
import logica.LogicaEquilibria;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelRequerimientos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabla;
	VentanaAgregarRequerimientos ventanaAgregarRequerimientos;
	LogicaEquilibria logicaEquilibria;

	public PanelRequerimientos(LogicaEquilibria logica) {
		this.logicaEquilibria = logica;
		initialize();
	}

	private void initialize() {

		setBorder(new TitledBorder("3. Requerimientos"));
		setLayout(null);

		tabla = new JTable();

		tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Rol", "Cantidad" }));

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(6, 52, 434, 242);

		add(scrollPane);

		JButton btnNewAgregarRequerimientos = new JButton("Agregar");
		btnNewAgregarRequerimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaRequerimiento();
			}
		});
		btnNewAgregarRequerimientos.setBounds(10, 18, 89, 23);
		add(btnNewAgregarRequerimientos);

		JButton btnNewEliminarRequerimientos = new JButton("Eliminar");
		btnNewEliminarRequerimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRequerimiento();
			}
		});
		btnNewEliminarRequerimientos.setBounds(109, 18, 89, 23);
		add(btnNewEliminarRequerimientos);
	}

	protected void eliminarRequerimiento() {	
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int filaModelo = tabla.convertRowIndexToModel(filaSeleccionada);
			logicaEquilibria.eliminarRequerimiento(filaModelo);
			refrescarTabla();
			JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
		}
	}

	public void abrirVentanaRequerimiento() {
		VentanaAgregarRequerimientos ventanaAgregarRequerimientos = new VentanaAgregarRequerimientos(logicaEquilibria,
				this);
		ventanaAgregarRequerimientos.setVisible(true);
		ventanaAgregarRequerimientos.setLocationRelativeTo(null);
	}

	public JTable getTabla() {
		return tabla;
	}

	public void refrescarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);
		for (Requerimiento requerimiento : logicaEquilibria.getRequerimientos()) {
			modelo.addRow(new Object[] { requerimiento.getRol(), requerimiento.getCantidad() });
		}
	}

}