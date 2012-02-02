package Recurso;

public class Solicitud {
	private int tipo;
	private int nueva_posicion;
	public static final int INCREMENTAR=1;
	public static final int DECREMENTAR=2;
	public static final int TRACK=5;
	public static final int INCREMENTAR_BLOQUE=3;
	public static final int DECREMENTAR_BLOQUE=4;
	public static final int POCISIONAR=9;
	
	public Solicitud(int tipo) {
		super();
		this.tipo = tipo;
	}

	public Solicitud() {
		super();
	}

	public int getNueva_posicion() {
		return nueva_posicion;
	}

	public void setNueva_posicion(int nueva_pocision) {
		this.nueva_posicion = nueva_pocision;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
