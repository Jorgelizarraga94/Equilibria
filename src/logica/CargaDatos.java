package logica;

public class CargaDatos {

	public static void cargarDatosDePrueba(LogicaEquilibria logicaEquilibria) {
		
	        logicaEquilibria.agregarPersona("Carlos", "Lider", 5, "D:\\Desktop\\Equilibria\\imagenes\\Hombre1.png");
	        logicaEquilibria.agregarPersona("Ana", "Lider", 4,"D:\\Desktop\\Equilibria\\imagenes\\Mujer2.png");
	        logicaEquilibria.agregarPersona("Elena", "Arquitecto", 5,"D:\\Desktop\\Equilibria\\imagenes\\Mujer3.png");
	        logicaEquilibria.agregarPersona("Marcos", "Arquitecto", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre2.png");
	        logicaEquilibria.agregarPersona("Lucas", "Arquitecto", 2,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre3.png");	        logicaEquilibria.agregarPersona("Sofía", "Programador", 5,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer4.png");
	        logicaEquilibria.agregarPersona("Juan", "Programador", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre4.png");
	        logicaEquilibria.agregarPersona("Pedro", "Programador", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre5.png");
	        logicaEquilibria.agregarPersona("Lucía", "Programador", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer5.png");
	        logicaEquilibria.agregarPersona("Bruno", "Programador", 1,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre6.png");
	        logicaEquilibria.agregarPersona("Mía", "Tester", 5,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer6.png");
	        logicaEquilibria.agregarPersona("Facundo", "Tester", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre7.png");
	        logicaEquilibria.agregarPersona("Rocío", "Tester", 4,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Mujer7.png");
	        logicaEquilibria.agregarPersona("Tomás", "Tester", 3,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre8.png");
	        logicaEquilibria.agregarPersona("Mateo", "Tester", 2,"D:\\\\Desktop\\\\Equilibria\\\\imagenes\\\\Hombre9.png");
	        
	        //Agregar requerimientos del ejemplo lider 1, arquitecto 2, programador 4, tester 5
	       
	        logicaEquilibria.agregarRequerimientos("Arquitecto", 2);
	        logicaEquilibria.agregarRequerimientos("Programador", 4);
	        logicaEquilibria.agregarRequerimientos("Tester", 5);
	        logicaEquilibria.agregarRequerimientos("Lider", 1);
	        
	        
	    }
}
