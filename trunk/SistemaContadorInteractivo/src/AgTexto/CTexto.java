//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maria CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgTexto;

import java.awt.event.KeyEvent;

import Recurso.Agente;
import Recurso.Notificacion;

public class CTexto implements Agente {
	private ATexto abstraccion;
	private PTexto presentacion;
	private Agente supervisor;
	
	public CTexto(int min,int val,int max,Agente supervisor) {
		super();
		this.abstraccion = new ATexto(min, val, max);
		this.presentacion = new PTexto(abstraccion,this);
		this.supervisor = supervisor;
	}
	
	private void Decrementar() {
		System.out.println("Tratando de Decrementar Texto: "+this);
		if(this.abstraccion.esDecrementable()){
			this.abstraccion.decrementar();
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
			System.out.println("Nuevo valor: "+this.abstraccion.getValor());
		}
		System.out.println("");
	}
	
	@Override
	public void Enviar(Notificacion notificacion,Agente receptor ) {
		receptor.Recibir( notificacion,this);
	}
	public ATexto getAbstraccion() {
		return abstraccion;
	}
	public PTexto getPresentacion() {
		return presentacion;
	}

	public Agente getSupervisor() {
		return supervisor;
	}
	private void Incremenrar() {
		System.out.println("Tratando de incrementar Texto: "+this);
		if(this.abstraccion.esIncrementable()){
			this.abstraccion.incrementar();
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
			System.out.println("Nuevo Valor: "+this.abstraccion.getValor());
		}
		System.out.println("");
	}
	private void PocisionarContador() {
		System.out.println("Tratando de pocisionar Texto: "+this);
		if(Validar_Pocisionar()){
			Notificacion notificacion = new Notificacion(Notificacion.POCISIONAR);
			if (!presentacion.getText().equals("")){
				notificacion.setNueva_posicion(Integer.valueOf(this.presentacion.getText()));
			}else{
				notificacion.setNueva_posicion(0);
			}
			this.abstraccion.setValor(notificacion.getNueva_posicion());
			System.out.println("Nuevo valor: "+this.abstraccion.getValor());
			Enviar(notificacion,this.supervisor);
		}else{
			System.out.println("Pocision no valida");
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
		}
		System.out.println("");
	}
	private void Posicionar_Bloque(int nueva_posicion) {
		System.out.println("Tratando de pocisionar Texot: "+this);
		if(this.abstraccion.isValorCorrecto(nueva_posicion)){
			this.abstraccion.setValor(nueva_posicion);
			this.presentacion.setText(String.valueOf(nueva_posicion));
			System.out.println("Nuevo Valor: "+this.abstraccion.getValor());
		}
		System.out.println("");
	}
	private void Posicionar_Track(int nueva_posicion) {
		Posicionar_Bloque(nueva_posicion);
	}
	@Override
	public void Recibir(Notificacion notificacion,Agente emisor) {
		switch (notificacion.getTipo()) {
			case Notificacion.DECREMENTAR:Decrementar();break;
			case Notificacion.INCREMENTAR:Incremenrar();break;
			case Notificacion.INCREMENTAR_BLOQUE:Posicionar_Bloque(notificacion.getNueva_posicion());break;
			case Notificacion.DECREMENTAR_BLOQUE:Posicionar_Bloque(notificacion.getNueva_posicion());break;
			case Notificacion.TRACK:Posicionar_Track(notificacion.getNueva_posicion());break;
			default:System.out.println("Notificacion desconocida");break;
		}
	}
	public void Recibir_Evento(KeyEvent accion){
		PocisionarContador();
	}
	private boolean Validar_Pocisionar() {
		if(!presentacion.getText().equals("")){
			return this.abstraccion.isValorCorrecto(Integer.valueOf(presentacion.getText()));
		}else{
			return true;
		}
	}
}
