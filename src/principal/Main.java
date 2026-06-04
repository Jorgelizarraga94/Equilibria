package principal;


import gui.VentanaPrincipal;
import logica.CargaDatos;
import logica.LogicaEquilibria;

public class Main {

	public static void main(String[] args) {
		
		LogicaEquilibria equilibria = new LogicaEquilibria();
		CargaDatos.cargarDatosDePrueba(equilibria);
		VentanaPrincipal ventana = new VentanaPrincipal(equilibria);
		ventana.setVisible(true);
		
	}

}
