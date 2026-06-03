package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;


import entidades.Incompatibilidad;
import entidades.Persona;
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

public class PanelResolver extends JPanel {

    private JButton btnResolver;

    private JLabel lblTiempo;
    private JLabel lblNodos;
    private JLabel lblCasosBase;
    private JLabel lblPodas;
    private JLabel lblPuntaje;
    private LogicaEquilibria logicaEquilibria;
    private PanelRequerimientos panelRequerimientos;

    private JProgressBar barra;

    public PanelResolver(LogicaEquilibria logica, PanelRequerimientos panel) {

        this.logicaEquilibria=logica;
        this.panelRequerimientos=panel;
        initialize();
    }

    private void initialize() {

        setLayout(new BorderLayout());

        setBorder(
                new TitledBorder("4. Resolver")
        );

        // BOTON
        JPanel panelSuperior =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnResolver = new JButton("GENERAR EQUIPO");
        btnResolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) panelRequerimientos.getTabla().getModel();
                Object[][] datosMatriz = new Object[4][2];
                // LOGICA NECESARIA PARA EL FUNCIONAMIENTO DE LA INTERFAZ
                for (int i = 0; i < 4; i++) {
                    datosMatriz[i][0] = modelo.getValueAt(i, 0);
                    datosMatriz[i][1] = modelo.getValueAt(i, 1);
                }
                
                btnResolver.setEnabled(false);
                
                logicaEquilibria.calcularEquipoOptimo(datosMatriz, new Consumer<List<Persona>>() {
                    @Override
                    public void accept(List<Persona> equipoGanador) {
                    	// LOGICA NECESARIA PARA EL FUNCIONAMIENTO DE LA INTERFAZ
                        if (equipoGanador.isEmpty()) {
                            javax.swing.JOptionPane.showMessageDialog(PanelResolver.this, 
                                "No es posible formar un equipo con esos requerimientos.", 
                                "Sin Solución", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            
                        }
                        btnResolver.setEnabled(true);
                    }
                });
            }
        });
        


        panelSuperior.add(btnResolver);

        add(panelSuperior, BorderLayout.NORTH);

        // ESTADISTICAS
        //Se deberia cambiar los labels por 
        JPanel panelStats =
                new JPanel(new GridLayout(5, 1));

        lblTiempo = new JLabel("Tiempo: 0 ms");

        lblNodos = new JLabel("Nodos recorridos: 0");

        lblCasosBase = new JLabel("Casos base: 0");

        lblPodas = new JLabel("Podas: 0");

        lblPuntaje = new JLabel("Mejor puntaje: 0");

        panelStats.add(lblTiempo);
        panelStats.add(lblNodos);
        panelStats.add(lblCasosBase);
        panelStats.add(lblPodas);
        panelStats.add(lblPuntaje);

        add(panelStats, BorderLayout.CENTER);

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