package gui;

import javax.swing.JFrame;

import entidades.Incompatibilidad;
import entidades.Persona;
import gui.PanelCalculo.PanelResolver;
import gui.PanelCalculo.PanelResultado;
import gui.PanelPersona.PanelIncompatibilidades;
import gui.PanelPersona.PanelPersonas;
import gui.PanelPersona.PanelRequerimientos;
import logica.AlgoritmoFuerzaBruta;
import logica.LogicaEquilibria;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private PanelPersonas panelPersonas;
	private PanelIncompatibilidades panelIncompatibilidades;
	private PanelRequerimientos panelRequerimientos;
	private PanelResolver panelResolver;
	private PanelResultado panelResultado;
	LogicaEquilibria logicaEquilibria;

	public VentanaPrincipal(LogicaEquilibria logica) {
		this.logicaEquilibria = logica;
		initialize();

	}

	private void initialize() {

		setTitle("Sofware Factoy Equilibria");

		setBounds(100, 100, 1400, 800);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(null);

		// PERSONAS
		panelPersonas = new PanelPersonas(logicaEquilibria);
		panelPersonas.refrescarTabla();

		panelPersonas.setBounds(10, 10, 450, 300);

		getContentPane().add(panelPersonas);

		// INCOMPATIBILIDADES
		panelIncompatibilidades = new PanelIncompatibilidades(logicaEquilibria);

		panelIncompatibilidades.setBounds(470, 10, 450, 300);

		getContentPane().add(panelIncompatibilidades);

		// REQUERIMIENTOS
		panelRequerimientos = new PanelRequerimientos();

		panelRequerimientos.setBounds(930, 10, 430, 300);

		getContentPane().add(panelRequerimientos);

		// RESULTADO
		panelResultado = new PanelResultado(logicaEquilibria);
		panelResultado.getTabla().setBounds(7, 77, 548, 0);

		panelResultado.setBounds(434, 321, 940, 400);

		getContentPane().add(panelResultado);

		// RESOLVER
		panelResolver = new PanelResolver(logicaEquilibria, panelRequerimientos, panelResultado);

		panelResolver.setBounds(10, 320, 400, 400);

		getContentPane().add(panelResolver);

	}

}