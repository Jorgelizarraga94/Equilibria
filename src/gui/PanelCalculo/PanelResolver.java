package gui.PanelCalculo;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import entidades.Incompatibilidad;
import entidades.Persona;
import gui.PanelPersona.PanelRequerimientos;
import logica.AlgoritmoFuerzaBruta;
import logica.LogicaEquilibria;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelResolver extends JPanel {

	private JButton btnResolver;

	private JLabel lblTiempo;
	private JLabel lblNodos;
	private JLabel lblCasosBase;
	private JLabel lblPodas;
	private JLabel lblPuntaje;
	private LogicaEquilibria logicaEquilibria;
	private PanelRequerimientos panelRequerimientos;
	private PanelResultado panelRes;

	private JProgressBar barra;
	private JComboBox comboBox;

	public PanelResolver(LogicaEquilibria logica, PanelRequerimientos panel, PanelResultado panelres) {

		this.logicaEquilibria = logica;
		this.panelRequerimientos = panel;
		this.panelRes=panelres;
		initialize();
	}

	private void initialize() {

		setLayout(new BorderLayout());

		setBorder(new TitledBorder("4. Resolver"));

		// ESTADISTICAS
		// Se deberia cambiar los labels por
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"BackTracking", "FuerzaBruta", "Heuristica"}));
		comboBox.setBounds(0, 12, 174, 23);

		panelStats.add(comboBox);
		
		btnResolver = new JButton("GENERAR EQUIPO");
		btnResolver.setBounds(206, 12, 153, 23);
		panelStats.add(btnResolver);
        btnResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String algoritmoSeleccionado = comboBox.getSelectedItem().toString();
                
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) panelRequerimientos.getTabla().getModel();
                Object[][] datosMatriz = new Object[4][2];
                for (int i = 0; i < 4; i++) {
                    datosMatriz[i][0] = modelo.getValueAt(i, 0);
                    datosMatriz[i][1] = modelo.getValueAt(i, 1);
                }
                
                btnResolver.setEnabled(false);
                
                logicaEquilibria.calcularEquipo(algoritmoSeleccionado, datosMatriz, new Consumer<List<Persona>>() {
                    @Override
                    public void accept(List<Persona> equipoGanador) {
                        if (equipoGanador.isEmpty()) {
                            javax.swing.JOptionPane.showMessageDialog(PanelResolver.this, 
                                "No es posible formar un equipo con esos requerimientos.", 
                                "Sin Solución", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        } else {
                        	panelRes.mostrarEquipo(equipoGanador);
                        }
                        btnResolver.setEnabled(true);
                    }
                });
            }
        });

		// BARRA
		barra = new JProgressBar();

		barra.setStringPainted(true);

		add(barra, BorderLayout.SOUTH);
	}

	public JButton getBtnResolver() {
		return btnResolver;
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