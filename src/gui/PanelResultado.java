package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class PanelResultado extends JPanel {

    private JTable tabla;

    public PanelResultado() {

        initialize();
    }

    private void initialize() {

        setLayout(new BorderLayout());

        setBorder(
                new TitledBorder("5. Equipo Resultante")
        );

        tabla = new JTable();

        tabla.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Nombre", "Rol", "Calificación", "Foto"}
        ));

        JScrollPane scrollPane =
                new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTabla() {
        return tabla;
    }
}