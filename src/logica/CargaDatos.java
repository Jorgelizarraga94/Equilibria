package logica;

import entidades.Persona;

public class CargaDatos {
	private LogicaEquilibria logicaEquilibria;

	public static void cargarDatosDePrueba(LogicaEquilibria logicaEquilibria) {
		
	        // Los agregas al gestor que usará la interfaz
	        logicaEquilibria.agregarPersona("Carlos", "lider", 5, "D:\\Desktop\\Equilibria\\imagenes\\Hombre1.png");
	        logicaEquilibria.agregarPersona("Ana", "lider", 4,"D:\\Desktop\\Equilibria\\imagenes\\Mujer2.png");
	        logicaEquilibria.agregarPersona("Elena", "arquitecto", 5,"D:\\Desktop\\Equilibria\\imagenes\\Mujer3.png");
	        logicaEquilibria.agregarPersona("Marcos", "arquitecto", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre2.png");
	        logicaEquilibria.agregarPersona("Lucas", "arquitecto", 2,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre3.png");	        logicaEquilibria.agregarPersona("Sofía", "programador", 5,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer4.png");
	        logicaEquilibria.agregarPersona("Juan", "programador", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre4.png");
	        logicaEquilibria.agregarPersona("Pedro", "programador", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre5.png");
	        logicaEquilibria.agregarPersona("Lucía", "programador", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer5.png");
	        logicaEquilibria.agregarPersona("Bruno", "programador", 1,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre6.png");
	        logicaEquilibria.agregarPersona("Mía", "tester", 5,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer6.png");
	        logicaEquilibria.agregarPersona("Facundo", "tester", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre7.png");
	        logicaEquilibria.agregarPersona("Rocío", "tester", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer7.png");
	        logicaEquilibria.agregarPersona("Tomás", "tester", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre8.png");
	        logicaEquilibria.agregarPersona("Mateo", "tester", 2,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre9.png");
	        
	        //Agregar requerimientos del ejemplo lider 1, arquitecto 2, programador 4, tester 5//para solo agregar un lider en la gui
	       
	        logicaEquilibria.agregarRequerimientos("Arquitecto", 2);
	        logicaEquilibria.agregarRequerimientos("Programador", 4);
	        logicaEquilibria.agregarRequerimientos("Tester", 5);
	        logicaEquilibria.agregarRequerimientos("lider", 1);
	        
	        
	    }
}
