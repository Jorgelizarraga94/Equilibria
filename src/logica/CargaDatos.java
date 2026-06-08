package logica;

public class CargaDatos {

	public static void cargarDatosDePrueba(LogicaEquilibria logicaEquilibria) {
		
		logicaEquilibria.agregarPersona("Carlos", "Lider", 5, "imagenes/Hombre1.png");
        logicaEquilibria.agregarPersona("Ana", "Lider", 4, "imagenes/Mujer2.png");
        logicaEquilibria.agregarPersona("Elena", "Arquitecto", 5, "imagenes/Mujer3.png");
        logicaEquilibria.agregarPersona("Marcos", "Arquitecto", 4, "imagenes/Hombre2.png");
        logicaEquilibria.agregarPersona("Lucas", "Arquitecto", 2, "imagenes/Hombre3.png");
        logicaEquilibria.agregarPersona("Sofía", "Programador", 5, "imagenes/Mujer4.png");
        logicaEquilibria.agregarPersona("Juan", "Programador", 4, "imagenes/Hombre4.png");
        logicaEquilibria.agregarPersona("Pedro", "Programador", 3, "imagenes/Hombre5.png");
        logicaEquilibria.agregarPersona("Lucía", "Programador", 3, "imagenes/Mujer5.png");
        logicaEquilibria.agregarPersona("Bruno", "Programador", 1, "imagenes/Hombre6.png");
        logicaEquilibria.agregarPersona("Mía", "Tester", 5, "imagenes/Mujer6.png");
        logicaEquilibria.agregarPersona("Facundo", "Tester", 4, "");
        logicaEquilibria.agregarPersona("Rocío", "Tester", 4, "imagenes/Mujer7.png");
        logicaEquilibria.agregarPersona("Tomás", "Tester", 3, "");
        logicaEquilibria.agregarPersona("Mateo", "Tester", 2, "");

	        //Agregar requerimientos del ejemplo lider 1, arquitecto 2, programador 4, tester 5
	       
	        logicaEquilibria.agregarRequerimientos("Arquitecto", 2);
	        logicaEquilibria.agregarRequerimientos("Programador", 4);
	        logicaEquilibria.agregarRequerimientos("Tester", 5);
	        logicaEquilibria.agregarRequerimientos("Lider", 1);
	        
	        
	    }
}
