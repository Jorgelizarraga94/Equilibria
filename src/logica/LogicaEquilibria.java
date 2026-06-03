package logica;

import java.util.ArrayList;
import java.util.HashMap;

//Una lista de personas

import java.util.List;
import java.util.Map;

import entidades.Incompatibilidad;
import entidades.Persona;

//Una lista de personas incompatibles entre si (si es que hay alguna)


//Una lista de equipos que me dio luego de llamar a generar equipo (calcular backtracking o heuristica)


//Un MAP de requirimientos (String) y cantidad (int) necesaria de personas con ese requerimiento (ejemplo: "Programador" -> 2, "Diseñador" -> 1, etc.)
//Para llamarla en el algoritomo backtracking, para saber cuantas personas con cada requerimiento necesito para generar el equipo.



public class LogicaEquilibria {
	Map<Long, Persona> personas = new HashMap<>();
	//List<Persona> personas; //Lista de personas disponibles para formar equipos
	Map<String, Integer> requerimientos; //Requerimientos necesarios para formar un equipo
	//List<Equipo> equiposGenerados; //Lista de equipos generados luego de llamar a generar equipo (calcular backtracking o heuristica)
	private List<Incompatibilidad> incompatibilidades = new ArrayList<>(); //Lista de incompatibilidades entre personas (si es que hay alguna)
	//Lo manejo con la clase incompatibilidad, que tiene dos personas, y si esas dos personas estan en el mismo equipo, ese equipo no es valido

	//Creo que se deberia hacer lo mismo con la lista de equipos
	//Crear una nueva clase que sea equipos y esta tenga los requerimientos necesarios para formar ese equipo
	//luego el algoritomo tendra que hacer lo suyo con esa informacion, para generar los equipos que cumplan con esos requerimientos.
	
	public void agregarPersona(String nombre, String rol, int calificación, String foto) {
		Persona persona = new Persona(nombre, rol, calificación, foto);
		personas.put(persona.getId(), persona);
	}
	
	public void eliminarPersona(Long id) {
		personas.remove(id);
	}

	public Map<Long, Persona> getPersonas() {
		return personas;
	}

	//Para agregar dos personas incompatibles entre si, se agrega a la lista de incompatibilidades una nueva
	public void agregarIncompatibilidad(Persona persona1, Persona persona2) {
		incompatibilidades.add(new Incompatibilidad(persona1, persona2));
		
	}
	//Para obtener la lista de incompatibilidades, se devuelve la lista de incompatibilidades
	public List<Incompatibilidad> getIncompatibilidades() {
		return incompatibilidades;
	}

	
	//Agregar funcion de eliminar 
	//Agregar funcion de agregar
	//Llamado a generar equipo (calcular backtracking o heuristica)
	//

	
	
}
