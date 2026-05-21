package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class PanelRequerimientos extends JPanel {

    private JTable tabla;

    public PanelRequerimientos() {

        initialize();
    }

    private void initialize() {

        setLayout(new BorderLayout());

        setBorder(
                new TitledBorder("3. Requerimientos")
        );

        tabla = new JTable();

        tabla.setModel(new DefaultTableModel(
                new Object[][] {{"Líder", 1}, {"Arquitecto", 2}, {"Programador", 4}, {"Tester", 5}},
                new String[] {
                        "Rol",
                        "Cantidad"
                }
        ));

        JScrollPane scrollPane =
                new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTabla() {
        return tabla;
    }
}