package entidades;

public class Persona {
	private static Long acumulador = 0L;
	private Long id;
	private String nombre;
	private String rol;
	private int calificacion;
	private String foto;
	
	//constructor
	public Persona(String nombre, String rol, int calificacion, String foto) {
		this.id = ++acumulador;
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
		this.foto = foto;
	}
	
	//constructor sin foto para los test y el algoritmo fuerza bruta, backtracking y heuristica
	public Persona(String nombre, String rol, int calificacion) {
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
	}
	
	//Getters
	public String getNombre() {
		return nombre;
	}
	public Long getId() {
		return id;
	}

	public String getRol() {
		return rol;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public String getFoto() {
		return foto;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	
}