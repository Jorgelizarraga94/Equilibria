package entidades;

public class Persona {
	
	private String nombre;
	private String rol;
	private int calificacion;
	
	//Foto de la persona
	private String foto; // Ruta o URL de la foto, que va a estar en el paquete de recursos
	
	//constructor
	public Persona(String nombre, String rol, int calificacion, String foto) {
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
		this.foto = null; // O una ruta por defecto si se desea
	}
	
	//Getters y Setters
	
	public String getNombre() {
		return nombre;
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
	
	
}