package gui.PanelPersona;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entidades.Requerimiento;
import gui.VentanaPrincipal;
import gui.VentanasEmergentes.VentanaAgregarRequerimientos;
import logica.LogicaEquilibria;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelRequerimientos extends JPanel {
	private static final long serialVersionUID = 1L;
	private VentanaPrincipal ventanaPrincipal;
	private JTable tabla;
	private LogicaEquilibria logicaEquilibria;

	public PanelRequerimientos(LogicaEquilibria logica, VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.logicaEquilibria = logica;
		initialize();
	}

	private void initialize() {

		setBorder(new TitledBorder("3. Requerimientos"));
		setLayout(null);
		
		//TABLA 
		tabla = new JTable();
		tabla.setRowSelectionAllowed(false);
		tabla.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Rol", "Cantidad" }));

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(6, 52, 434, 242);

		add(scrollPane);
		
		//BOTONES
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

	public void eliminarRequerimiento() {
		ventanaPrincipal.deshabilitarComboBoxResultado();
		ventanaPrincipal.habilitarBotonesPanelResolver();
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
			int filaModelo = tabla.convertRowIndexToModel(filaSeleccionada);
			logicaEquilibria.eliminarRequerimiento(filaModelo);
			refrescarTabla();
			JOptionPane.showMessageDialog(null, "Eliminado con éxito.");
		}
	}

	public void abrirVentanaRequerimiento() {
		ventanaPrincipal.deshabilitarComboBoxResultado();
		ventanaPrincipal.habilitarBotonesPanelResolver();
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