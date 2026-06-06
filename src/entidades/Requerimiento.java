package entidades;

public class Requerimiento {
	private String rol;
	private int cantidad;
	
	public Requerimiento(String rol, int cantidad) {
		this.rol = rol;
		this.cantidad = cantidad;
	}

	public String getRol() {
		return rol;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	
}
