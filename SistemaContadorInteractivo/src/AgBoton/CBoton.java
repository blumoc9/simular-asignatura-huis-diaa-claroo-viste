package AgBoton;

import Recurso.Agente;
import Recurso.Notificacion;

public class CBoton implements Agente{
	private ABoton abstraccion;
	private PBoton presentacion;
	private Agente supervisor;
	private int tipo;
	public final static int BOTON_DECREMENTO=1;
	public final static int BOTON_INCREMENTO=2;
	
	public CBoton(int min,int val, int max,int tipo_boton,Agente supervisor) {
		super();
		this.tipo=tipo_boton;
		this.supervisor = supervisor;
		this.abstraccion = new ABoton(min, val, max);
		if(this.tipo==CBoton.BOTON_INCREMENTO){
			this.presentacion = new PBoton("+", this);
		}else{
			this.presentacion = new PBoton("-", this);
		}
	}

	public void Procesar_Evento(){
		Notificacion notificacion = new Notificacion();
		if (this.tipo==CBoton.BOTON_INCREMENTO){
			Incrementar();
			notificacion.setTipo(Notificacion.INCREMENTAR);
		}else{
			Decrementar();
			notificacion.setTipo(Notificacion.DECREMENTAR);
		}
		this.Enviar(notificacion, this.supervisor);
	}
	
	private void Decrementar() {
		System.out.println("Tratando de Decrementar Boton: "+this);
		if (this.abstraccion.esDecrementable()){
			this.abstraccion.decrementar();
			System.out.println("Nuevo Valor: "+this.abstraccion.getValor());
		}else{
			System.out.println("No se pudo decrementar Boton...");
			System.out.println("Valor Actual: "+this.abstraccion.getValor());
		}
		switch (this.tipo) {
			case CBoton.BOTON_INCREMENTO:this.presentacion.setEnabled(true);break;
			case CBoton.BOTON_DECREMENTO:
				if (!this.abstraccion.esDecrementable()){
					this.presentacion.setEnabled(false);
				}
				break;
			default:break;
		}
		System.out.println("");
	}

	private void Incrementar() {
		System.out.println("Tratando de Incrementar Boton: "+this);
		if (this.abstraccion.esIncrementable()){
			this.abstraccion.incrementar();
			System.out.println("Nuevo valor: "+this.abstraccion.getValor());
		}else{
			System.out.println("No se pudo incrementar Boton: "+this);
			System.out.println("Valor Actual: "+this.abstraccion.getValor());
		}
		switch (this.tipo) {
			case CBoton.BOTON_INCREMENTO:
				if (!this.abstraccion.esIncrementable()){
					this.presentacion.setEnabled(false);
				}
			break;
			case CBoton.BOTON_DECREMENTO: this.presentacion.setEnabled(true);
			break;
			default:break;
		}
		System.out.println("");
	}

	@Override
	public void Enviar(Notificacion notificacion, Agente receptor) {
		receptor.Recibir(notificacion, this);
	}

	@Override
	public void Recibir(Notificacion notificacion, Agente eminsor) {
		Procesa_Notificacion(notificacion);
	}

	private void Procesa_Notificacion(Notificacion notificacion) {
		//System.out.println("Procesanto..:");
		switch (notificacion.getTipo()) {
			case Notificacion.INCREMENTAR:Incrementar();break;
			case Notificacion.DECREMENTAR:Decrementar();break;
			case Notificacion.DECREMENTAR_BLOQUE:Pocisionar(notificacion.getNueva_posicion());break;
			case Notificacion.INCREMENTAR_BLOQUE:Pocisionar(notificacion.getNueva_posicion());break;
			case Notificacion.TRACK:Pocisionar(notificacion.getNueva_posicion());break;
			case Notificacion.POCISIONAR:Pocisionar(notificacion.getNueva_posicion());break;
			default:break;
		}
	}

	private void Pocisionar(int nueva_posicion) {
		//this.presentacion.setEnabled(false);
		System.out.println("Tratando de Pocisionar Boton... "+this);
		System.out.println("Nueva  lugar: "+nueva_posicion);
		if (abstraccion.isValorCorrecto(nueva_posicion)){
			this.abstraccion.setValor(nueva_posicion);
			System.out.println("Nueva Pocision: "+this.abstraccion.getValor());
		}
		switch (this.tipo) {
			case CBoton.BOTON_INCREMENTO:
				if (!abstraccion.esIncrementable()){
					//System.out.println("Cambio estado del boton a: "+!this.abstraccion.esIncrementable());
					this.presentacion.setEnabled(false);
				}else{
					this.presentacion.setEnabled(true);
				}
			break;
			case CBoton.BOTON_DECREMENTO:
				if (!abstraccion.esDecrementable()){
					this.presentacion.setEnabled(false);
				}else {
					this.presentacion.setEnabled(true);
				}
				break;
		}
		System.out.println("");
	}

	public ABoton getAbstraccion() {
		return abstraccion;
	}

	public void setAbstraccion(ABoton abstraccion) {
		this.abstraccion = abstraccion;
	}

	public PBoton getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(PBoton presentacion) {
		this.presentacion = presentacion;
	}
	
	
}
