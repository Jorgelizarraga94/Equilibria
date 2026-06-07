package entidades;

public class Requerimiento {
	private String rol;
	private int cantidad;
	
	//constructor
	public Requerimiento(String rol, int cantidad) {
		this.rol = rol;
		this.cantidad = cantidad;
	}

	//Getters
	public String getRol() {
		return rol;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	
}
