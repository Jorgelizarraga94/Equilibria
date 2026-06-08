package gui.VentanasEmergentes;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.PanelPersona.PanelRequerimientos;
import logica.LogicaEquilibria;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarRequerimientos extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCantidadRequerimiento;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxRolRequerimiento;
	private PanelRequerimientos panelRequerimientos;
	private LogicaEquilibria logicaEquilibria;

	public VentanaAgregarRequerimientos(LogicaEquilibria logica, PanelRequerimientos panelRequerimientos) {
		this.logicaEquilibria = logica;
		this.panelRequerimientos = panelRequerimientos;
		ventanaAgregarRequerimientos();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void ventanaAgregarRequerimientos() {
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		comboBoxRolRequerimiento = new JComboBox();
		comboBoxRolRequerimiento
				.setModel(new DefaultComboBoxModel(new String[] { "Lider", "Tester", "Arquitecto", "Programador" }));
		comboBoxRolRequerimiento.setBounds(180, 49, 126, 22);
		contentPanel.add(comboBoxRolRequerimiento);

		textFieldCantidadRequerimiento = new JTextField();
		textFieldCantidadRequerimiento.setBounds(180, 95, 126, 20);
		contentPanel.add(textFieldCantidadRequerimiento);
		textFieldCantidadRequerimiento.setColumns(10);

		JLabel lblNewLabel = new JLabel("Rol");
		lblNewLabel.setBounds(100, 53, 70, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cantidad");
		lblNewLabel_1.setBounds(100, 98, 70, 14);
		contentPanel.add(lblNewLabel_1);

		JButton btnAgregarRequerimiento = new JButton("Agregar");
		btnAgregarRequerimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarRequerimientos();
			}
		});
		btnAgregarRequerimiento.setBounds(180, 159, 89, 23);
		contentPanel.add(btnAgregarRequerimiento);
	}

	public void agregarRequerimientos() {
		logicaEquilibria.agregarRequerimientos(comboBoxRolRequerimiento.getSelectedItem().toString(),
				Integer.parseInt(textFieldCantidadRequerimiento.getText()));
		panelRequerimientos.refrescarTabla();
		this.dispose();
	}
}
