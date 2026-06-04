package logica;

import entidades.Persona;

public class CargaDatos {
	private LogicaEquilibria logicaEquilibria;

	public static void cargarDatosDePrueba(LogicaEquilibria logicaEquilibria) {
		
	        // Los agregas al gestor que usará la interfaz
	        logicaEquilibria.agregarPersona("Carlos", "lider", 5, "");
	        logicaEquilibria.agregarPersona("Ana", "lider", 4,"");
	        logicaEquilibria.agregarPersona("Elena", "arquitecto", 5,"");
	        logicaEquilibria.agregarPersona("Marcos", "arquitecto", 4,"");
	        logicaEquilibria.agregarPersona("Lucas", "arquitecto", 2,"");	        logicaEquilibria.agregarPersona("Sofía", "programador", 5,"");
	        logicaEquilibria.agregarPersona("Juan", "programador", 4,"");
	        logicaEquilibria.agregarPersona("Pedro", "programador", 3,"");
	        logicaEquilibria.agregarPersona("Lucía", "programador", 3,"");
	        logicaEquilibria.agregarPersona("Bruno", "programador", 1,"");
	        logicaEquilibria.agregarPersona("Mía", "tester", 5,"");
	        logicaEquilibria.agregarPersona("Facundo", "tester", 4,"");
	        logicaEquilibria.agregarPersona("Rocío", "tester", 4,"");
	        logicaEquilibria.agregarPersona("Tomás", "tester", 3,"");
	        logicaEquilibria.agregarPersona("Mateo", "tester", 2,"");
	
	        
	    }
}
