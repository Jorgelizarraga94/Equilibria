package logica;

import java.util.HashMap;

//Una lista de personas

import java.util.List;
import java.util.Map;

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
	//List<Incompatibilidad> incompatibilidades; //Lista de incompatibilidades entre personas (si es que hay alguna)
	
	
	public void agregarPersona(String nombre, String rol, int calificación, String foto) {
		Persona persona = new Persona(nombre, rol, calificación, foto);
		personas.put(persona.getId(), persona);
	}
	
	public void eliminarPersona(Long id) {
		personas.remove(id);
	}

	public Map<Long, Persona> getPersonas() {
		return personas;
	};
	//Agregar funcion de eliminar 
	//Agregar funcion de agregar
	//Llamado a generar equipo (calcular backtracking o heuristica)
	//

	
	
}
