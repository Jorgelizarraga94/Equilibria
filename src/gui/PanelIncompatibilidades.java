package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelIncompatibilidades extends JPanel {

    private JTable tabla;
    private JButton btnAgregar;
    private JButton btnEliminar;

    public PanelIncompatibilidades() {

        initialize();
    }

    private void initialize() {

        setLayout(new BorderLayout());

        setBorder(
                new TitledBorder("2. Incompatibilidades")
        );

        // TABLA
        tabla = new JTable();

        tabla.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Persona 1", "Persona 2"}
        ));

        JScrollPane scrollPane =
                new JScrollPane(tabla);

        add(scrollPane, BorderLayout.CENTER);

        // BOTONES
        JPanel panelBotones =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

       
        btnAgregar = new JButton("Agregar");
        
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnEliminar);
        panelBotones.add(btnAgregar);

        add(panelBotones, BorderLayout.NORTH);
    }

    public JTable getTabla() {
        return tabla;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }
}