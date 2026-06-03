package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logica.LogicaEquilibria;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

//Importamos para las imagenes
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VentanaAgregarPersona extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldRol;
	private JTextField textFieldCalificación;

	LogicaEquilibria logicaEquilibria;
	PanelPersonas panelPersonas;
	private JTextField textFieldFoto;

	public VentanaAgregarPersona(LogicaEquilibria logica, PanelPersonas panelPersonas) {
		this.logicaEquilibria = logica;
		this.panelPersonas = panelPersonas;
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // para que no se cierre toda la aplicacion al cerrar la ventana
		setBounds(100, 100, 407, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel(" Nombre");
		lblNombre.setBounds(45, 64, 65, 14);
		contentPane.add(lblNombre);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(45, 89, 65, 14);
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

		textFieldRol = new JTextField();
		textFieldRol.setColumns(10);
		textFieldRol.setBounds(180, 86, 123, 20);
		contentPane.add(textFieldRol);

		textFieldCalificación = new JTextField();
		textFieldCalificación.setColumns(10);
		textFieldCalificación.setBounds(180, 111, 123, 20);
		contentPane.add(textFieldCalificación);

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

		//Para agregar una foto
		JButton btnAgregarImagen = new JButton("Agregar Imagen");
		btnAgregarImagen.setBounds(180, 180, 123, 23);
		contentPane.add(btnAgregarImagen);

		btnAgregarImagen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        seleccionarImagen();
		    }
		});
		
	}

	private void agregarPersona() {
		logicaEquilibria.agregarPersona(textFieldNombre.getText(), textFieldRol.getText(),
				Integer.parseInt(textFieldCalificación.getText()), textFieldFoto.getText());
		panelPersonas.refrescarTabla();
		this.dispose();
	}
	
	//Metodo para seleccionar una imagen desde el sistema de archivos
	private void seleccionarImagen() {
	    JFileChooser selector = new JFileChooser();

	    FileNameExtensionFilter filtro =
	            new FileNameExtensionFilter(
	                    "Imágenes (*.jpg, *.png, *.jpeg)",
	                    "jpg", "jpeg", "png");

	    selector.setFileFilter(filtro);

	    int opcion = selector.showOpenDialog(this);

	    if (opcion == JFileChooser.APPROVE_OPTION) {

	        File archivo = selector.getSelectedFile();

	        textFieldFoto.setText(archivo.getAbsolutePath());
	    }
	}
}
