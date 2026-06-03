package entidades;

//Creo la clase incompatibilidad para representar la incompatibilidad entre dos personas, con getters y setters
public class Incompatibilidad {
	private static Long acumulador = 0L;
	private Long id;
	private Persona persona1;
	private Persona persona2;
	

	public Incompatibilidad(Persona persona1, Persona persona2) {
		this.id= ++acumulador;
		this.persona1 = persona1;
		this.persona2 = persona2;
	}

	public Persona getPersona1() {
		return persona1;
	}

	public void setPersona1(Persona persona1) {
		this.persona1 = persona1;
	}

	public Persona getPersona2() {
		return persona2;
	}

	public void setPersona2(Persona persona2) {
		this.persona2 = persona2;
	}

	public Long getid() {
		return this.id;
	}

	
}
