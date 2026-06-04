package gui.PanelCalculo;

import entidades.Persona;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

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

    public void mostrarEquipo(List<Persona> equipo) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (Persona p : equipo) {
            Object[] fila = new Object[] {
                p.getNombre(),
                p.getRol(),
                p.getCalificacion(),
                p.getFoto()
            };
            modelo.addRow(fila);
        }
    }

    public JTable getTabla() {
        return tabla;
    }
}