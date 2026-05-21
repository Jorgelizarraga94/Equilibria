package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class PanelResolver extends JPanel {

    private JButton btnResolver;

    private JLabel lblTiempo;
    private JLabel lblNodos;
    private JLabel lblCasosBase;
    private JLabel lblPodas;
    private JLabel lblPuntaje;

    private JProgressBar barra;

    public PanelResolver() {

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

        btnResolver =
                new JButton("GENERAR EQUIPO");

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