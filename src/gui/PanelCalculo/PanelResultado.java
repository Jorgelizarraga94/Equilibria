package gui.PanelCalculo;

import entidades.Persona;
import logica.LogicaEquilibria;
import logica.ReporteEjecucion;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class PanelResultado extends JPanel {

	private static final long serialVersionUID = 1L;
	private LogicaEquilibria logicaEquilibria;
	private JTable tabla;
	private JLabel lblTiempo;
	private JLabel lblNodos;
	private JLabel lblCasosBase;
	private JLabel lblPodas;
	private JLabel lblPuntaje;

	// Constructor
	public PanelResultado(LogicaEquilibria logicaEquilibria) {
		setFocusable(false);
		this.logicaEquilibria = logicaEquilibria;
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		setBorder(new TitledBorder("5. Equipo Resultante"));
		setLayout(null);

		tabla = new JTable();
		tabla.setEnabled(false);
		tabla.setFocusTraversalKeysEnabled(false);
		tabla.setFocusable(false);
		tabla.setRowSelectionAllowed(false);

		tabla.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Rol", "Calificación", "Foto" }) {
					@Override
					public Class<?> getColumnClass(int column) {
						if (column == 3) {
							return ImageIcon.class;
						}
						return Object.class;
					}
				});

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(6, 34, 894, 355);

		add(scrollPane);

		JLabel lblNewLabel = new JLabel("New label");
		scrollPane.setColumnHeaderView(lblNewLabel);

		@SuppressWarnings("rawtypes")
		JComboBox comboBoxSeleccionAlgoritmo = new JComboBox();
		comboBoxSeleccionAlgoritmo
				.setModel(new DefaultComboBoxModel(new String[] { "BackTracking", "FuerzaBruta", "Heuristica" }));
		comboBoxSeleccionAlgoritmo.setBounds(734, 11, 166, 22);
		add(comboBoxSeleccionAlgoritmo);

		comboBoxSeleccionAlgoritmo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String algoritmoSeleccionado = (String) comboBoxSeleccionAlgoritmo.getSelectedItem();
				java.awt.Container padre = comboBoxSeleccionAlgoritmo.getParent();
				while (padre != null) {
					for (java.awt.Component comp : padre.getComponents()) {
						if (comp.getClass().getSimpleName().equals("PanelResolver")) {
							try {
								JLabel txtTiempo = (JLabel) comp.getClass().getMethod("getLblTiempo").invoke(comp);
								JLabel txtNodos = (JLabel) comp.getClass().getMethod("getLblNodos").invoke(comp);
								JLabel txtCasos = (JLabel) comp.getClass().getMethod("getLblCasosBase").invoke(comp);
								JLabel txtPodas = (JLabel) comp.getClass().getMethod("getLblPodas").invoke(comp);
								JLabel txtPuntaje = (JLabel) comp.getClass().getMethod("getLblPuntaje").invoke(comp);
								actualizarResultado(algoritmoSeleccionado, txtTiempo, txtNodos, txtCasos, txtPodas,
										txtPuntaje);
								return;
							} catch (Exception ex) {
								break;
							}
						}
					}
					padre = padre.getParent();
				}
				actualizarResultado(algoritmoSeleccionado, lblTiempo, lblNodos, lblCasosBase, lblPodas, lblPuntaje);
			}
		});
	}

	public void mostrarEquipo(List<Persona> equipo) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);

		for (Persona p : equipo) {
			ImageIcon icono = null;
			if (p.getFoto() != null && !p.getFoto().isEmpty()) {
				ImageIcon original = new ImageIcon(p.getFoto());
				Image imagenEscalada = original.getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH);
				icono = new ImageIcon(imagenEscalada);
			}
			modelo.addRow(new Object[] { p.getNombre(), p.getRol(), p.getCalificacion(), icono });
		}
		tabla.setRowHeight(100);
	}

	public void actualizarResultado(String algoritmoSeleccionado, JLabel tiempo, JLabel nodos, JLabel casos,
			JLabel podas, JLabel puntaje) {
		String algoritmo = algoritmoSeleccionado.trim();

		switch (algoritmo) {
		case "BackTracking":
			ReporteEjecucion reporteBT = logicaEquilibria.getReporte("BackTracking");
			if (reporteBT != null) {
				mostrarEquipo(reporteBT.getEquipoGanador());
				mostrarMetricas(reporteBT, tiempo, nodos, casos, podas, puntaje);
			}
			break;

		case "FuerzaBruta":
			ReporteEjecucion reporteFB = logicaEquilibria.getReporte("FuerzaBruta");
			if (reporteFB != null) {
				mostrarEquipo(reporteFB.getEquipoGanador());
				mostrarMetricas(reporteFB, tiempo, nodos, casos, podas, puntaje);
			}
			break;

		case "Heuristica":
			ReporteEjecucion reporteAH = logicaEquilibria.getReporte("Heuristica");
			if (reporteAH != null) {
				mostrarEquipo(reporteAH.getEquipoGanador());
				mostrarMetricas(reporteAH, tiempo, nodos, casos, podas, puntaje);
			}
			break;

		default:
			break;
		}

		if (tiempo != null && tiempo.getParent() != null) {
			tiempo.getParent().revalidate();
			tiempo.getParent().repaint();
		}
	}

	public void mostrarMetricas(ReporteEjecucion reporte, JLabel tiempo, JLabel nodos, JLabel casos, JLabel podas,
			JLabel puntaje) {
		if (reporte != null) {
			if (tiempo != null)
				tiempo.setText("Tiempo: " + reporte.getTiempoDeEjecucionMs() + " ms");
			if (nodos != null)
				nodos.setText("Nodos recorridos: " + reporte.getNodosRecorridos());
			if (casos != null)
				casos.setText("Casos base: " + reporte.getCasosBaseContados());
			if (podas != null)
				podas.setText("Podas realizadas: " + reporte.getPodasRealizadas());
			if (puntaje != null)
				puntaje.setText("Mejor puntaje: " + reporte.getPuntajeMaximoObtenido());
		}
	}

	public JTable getTabla() {
		return tabla;
	}

	public JLabel getLblTiempo() {
		return this.lblTiempo;
	}

	public JLabel getLblNodos() {
		return this.lblNodos;
	}

	public JLabel getLblCasosBase() {
		return this.lblCasosBase;
	}

	public JLabel getLblPodas() {
		return this.lblPodas;
	}

	public JLabel getLblPuntaje() {
		return this.lblPuntaje;
	}
}