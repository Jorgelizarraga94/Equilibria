package gui.VentanasEmergentes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.PanelPersona.PanelPersonas;
import logica.LogicaEquilibria;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

//Importamos para las imagenes
import java.awt.event.ActionListener;
import java.io.File;

public class VentanaAgregarPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxRol;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxCalificacion;
	private LogicaEquilibria logicaEquilibria;
	private PanelPersonas panelPersonas;
	private JTextField textFieldFoto;

	public VentanaAgregarPersona(LogicaEquilibria logica, PanelPersonas panelPersonas) {
		this.logicaEquilibria = logica;
		this.panelPersonas = panelPersonas;
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para que no se cierre toda la aplicacion al cerrar la
															// ventana
		setBounds(100, 100, 407, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel(" Nombre");
		lblNombre.setBounds(45, 64, 65, 14);
		contentPane.add(lblNombre);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(45, 89, 75, 14);
		contentPane.add(lblRol);

		JLabel lblCalificacin = new JLabel("Calificación");
		lblCalificacin.setBounds(45, 114, 84, 14);
		contentPane.add(lblCalificacin);

		JLabel lblFoto = new JLabel("Foto");
		lblFoto.setBounds(45, 150, 65, 14);
		contentPane.add(lblFoto);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(180, 61, 123, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		comboBoxRol = new JComboBox();
		comboBoxRol.setModel(new DefaultComboBoxModel(new String[] { "Lider", "Arquitecto", "Programador", "Tester" }));
		comboBoxRol.setBounds(180, 85, 123, 22);
		contentPane.add(comboBoxRol);

		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPersona();
			}
		});
		btnNewButton.setBounds(145, 232, 89, 23);
		contentPane.add(btnNewButton);

		textFieldFoto = new JTextField();
		textFieldFoto.setColumns(10);
		textFieldFoto.setBounds(180, 147, 123, 20);
		contentPane.add(textFieldFoto);

		// Para agregar una foto
		JButton btnAgregarImagen = new JButton("Agregar Imagen");
		btnAgregarImagen.setBounds(180, 180, 123, 23);
		contentPane.add(btnAgregarImagen);

		comboBoxCalificacion = new JComboBox();
		comboBoxCalificacion.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		comboBoxCalificacion.setBounds(180, 110, 123, 22);
		contentPane.add(comboBoxCalificacion);

		btnAgregarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarImagen();
			}
		});
	}

	private void agregarPersona() {
		logicaEquilibria.agregarPersona(textFieldNombre.getText(), comboBoxRol.getModel().getSelectedItem().toString(),
				Integer.parseInt(comboBoxCalificacion.getSelectedItem().toString()), textFieldFoto.getText());
		panelPersonas.refrescarTabla();
		this.dispose();
	}

	// Metodo para seleccionar una imagen desde el sistema de archivos
	private void seleccionarImagen() {
		java.io.File carpetaProyecto = new java.io.File("./imagenes");
		JFileChooser selector = new JFileChooser(carpetaProyecto);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes (*.jpg, *.png, *.jpeg)", "jpg", "jpeg",
				"png");

		selector.setFileFilter(filtro);
		int opcion = selector.showOpenDialog(this);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			File archivo = selector.getSelectedFile();
			textFieldFoto.setText(archivo.getAbsolutePath());
		}
	}
}
