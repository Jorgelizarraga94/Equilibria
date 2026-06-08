package gui.PanelCalculo;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

import entidades.Persona;
import entidades.Requerimiento;
import logica.LogicaEquilibria;
import logica.ReporteEjecucion;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelResolver extends JPanel {
	private static final long serialVersionUID = 1L;
	private LogicaEquilibria logicaEquilibria;
	private PanelResultado panelRes;
	private JButton btnGenerarEquipo;
	private JLabel lblTiempo;
	private JLabel lblNodos;
	private JLabel lblCasosBase;
	private JLabel lblPodas;
	private JLabel lblPuntaje;
	private JProgressBar barra;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	// Constructor
	public PanelResolver(LogicaEquilibria logica, PanelResultado panelres) {
		this.logicaEquilibria = logica;
		this.panelRes = panelres;
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		setLayout(new BorderLayout());

		setBorder(new TitledBorder("4. Resolver"));

		// ESTADISTICAS
		JPanel panelStats = new JPanel();

		lblTiempo = new JLabel("Tiempo: 0 ms");
		lblTiempo.setBounds(0, 46, 438, 45);

		lblNodos = new JLabel("Nodos recorridos: 0");
		lblNodos.setBounds(0, 80, 438, 45);

		lblCasosBase = new JLabel("Casos base: 0");
		lblCasosBase.setBounds(0, 124, 438, 45);

		lblPodas = new JLabel("Podas: 0");
		lblPodas.setBounds(0, 165, 438, 45);

		lblPuntaje = new JLabel("Mejor puntaje: 0");
		lblPuntaje.setBounds(0, 205, 438, 45);
		panelStats.setLayout(null);

		panelStats.add(lblTiempo);
		panelStats.add(lblNodos);
		panelStats.add(lblCasosBase);
		panelStats.add(lblPodas);
		panelStats.add(lblPuntaje);

		add(panelStats, BorderLayout.CENTER);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "BackTracking", "FuerzaBruta", "Heuristica" }));
		comboBox.setBounds(0, 12, 174, 23);

		panelStats.add(comboBox);

		btnGenerarEquipo = new JButton("GENERAR EQUIPO");
		btnGenerarEquipo.setBounds(206, 12, 153, 23);
		panelStats.add(btnGenerarEquipo);
		btnGenerarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				barra.setIndeterminate(false);
				barra.setValue(100);
				barra.setString("Calculando...");

				String algoritmoSeleccionado = comboBox.getSelectedItem().toString();

				List<Requerimiento> requerimientos = logicaEquilibria.getRequerimientos();

				logicaEquilibria.calcularEquipo(algoritmoSeleccionado, requerimientos,
						new Consumer<ReporteEjecucion>() {
							@Override
							public void accept(ReporteEjecucion reporte) {

								barra.setIndeterminate(false);
								barra.setValue(100);
								barra.setString("Completado");

								List<Persona> equipoGanador = reporte.getEquipoGanador();

								if (equipoGanador.isEmpty()) {
									javax.swing.JOptionPane.showMessageDialog(PanelResolver.this,
											"No es posible formar un equipo con esos requerimientos.", "Sin Solución",
											javax.swing.JOptionPane.INFORMATION_MESSAGE);
								} else {
									panelRes.mostrarEquipo(equipoGanador);
									panelRes.mostrarMetricas(reporte, lblTiempo, lblNodos, lblCasosBase, lblPodas,
											lblPuntaje);
								}

							}
						});
			}
		});

		barra = new JProgressBar();
		barra.setStringPainted(true);
		barra.setString("Esperando...");
		barra.setValue(0);
		add(barra, BorderLayout.SOUTH);

	}

	public JButton getBtnGenerarEquipo() {
		return btnGenerarEquipo;
	}

	public JLabel getLblTiempo() {
		return lblTiempo;
	}

	public JLabel getLblNodos() {
		return lblNodos;
	}

	public JLabel getLblCasosBase() {
		return lblCasosBase;
	}

	public JLabel getLblPodas() {
		return lblPodas;
	}

	public JLabel getLblPuntaje() {
		return lblPuntaje;
	}

	public JProgressBar getBarra() {
		return barra;
	}
}