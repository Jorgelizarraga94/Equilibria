package gui;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame {

    private PanelPersonas panelPersonas;
    private PanelIncompatibilidades panelIncompatibilidades;
    private PanelRequerimientos panelRequerimientos;
    private PanelResolver panelResolver;
    private PanelResultado panelResultado;

    public VentanaPrincipal() {

        initialize();
    }

    private void initialize() {

        setTitle("Sofware Factoy Equilibria");

        setBounds(100, 100, 1400, 800);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        // PERSONAS
        panelPersonas = new PanelPersonas();

        panelPersonas.setBounds(10, 10, 450, 300);

        getContentPane().add(panelPersonas);

        // INCOMPATIBILIDADES
        panelIncompatibilidades = new PanelIncompatibilidades();

        panelIncompatibilidades.setBounds(470, 10, 450, 300);

        getContentPane().add(panelIncompatibilidades);

        // REQUERIMIENTOS
        panelRequerimientos = new PanelRequerimientos();

        panelRequerimientos.setBounds(930, 10, 430, 300);

        getContentPane().add(panelRequerimientos);

        // RESOLVER
        panelResolver = new PanelResolver();

        panelResolver.setBounds(10, 320, 400, 400);

        getContentPane().add(panelResolver);

        panelResultado = new PanelResultado();

        panelResultado.setBounds(420, 320, 940, 400);

        getContentPane().add(panelResultado);
    }

    
}