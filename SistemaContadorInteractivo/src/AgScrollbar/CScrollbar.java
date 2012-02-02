//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 

package AgScrollbar;

import java.awt.event.AdjustmentEvent;

import Recurso.Agente;
import Recurso.Notificacion;

public class CScrollbar implements Agente{
	private PScrollbar dibujo;
	private AScrollbar abstracion;
	private Agente supervisor;
	
	public CScrollbar(int valor,int max,int min,Agente supervisor) {
		super();
		this.abstracion = new AScrollbar(min,valor,max);
		this.dibujo = new PScrollbar(abstracion,this);
		this.supervisor = supervisor;
	}
	
	public void EnviarAccion(AdjustmentEvent accion){
		Notificacion notificacion = new Notificacion();
		if(accion.getAdjustmentType()==accion.UNIT_INCREMENT){
			notificacion.setTipo(Notificacion.INCREMENTAR);
		}else if (accion.getAdjustmentType()==accion.UNIT_DECREMENT){
			notificacion.setTipo(Notificacion.DECREMENTAR);
		}else if (accion.getAdjustmentType()==accion.BLOCK_DECREMENT){
			notificacion.setTipo(Notificacion.DECREMENTAR_BLOQUE);
			notificacion.setNueva_posicion(this.dibujo.getValue());
		}else if (accion.getAdjustmentType()==accion.BLOCK_INCREMENT){
			notificacion.setTipo(Notificacion.INCREMENTAR_BLOQUE);
			notificacion.setNueva_posicion(this.dibujo.getValue());
		}else if (accion.getAdjustmentType()==accion.TRACK){
			notificacion.setTipo(Notificacion.TRACK);
			notificacion.setNueva_posicion(this.dibujo.getValue());
		}
		Enviar(notificacion,this.supervisor);
	}
	
	public void Recibir_Evento(AdjustmentEvent accion){
		EnviarAccion(accion);
	}

	public PScrollbar getDibujo() {
		return dibujo;
	}

	public void setDibujo(PScrollbar dibujo) {
		this.dibujo = dibujo;
	}

	public AScrollbar getAbstracion() {
		return abstracion;
	}

	public void setAbstracion(AScrollbar abstracion) {
		this.abstracion = abstracion;
	}

	@Override
	public void Recibir(Notificacion notificacion,Agente emisor) {
		if(notificacion.getTipo()==Notificacion.INCREMENTAR){
			Incrementar();
		}else if (notificacion.getTipo()==Notificacion.DECREMENTAR){
			Decrementar();
		}else if ((notificacion.getTipo()==Notificacion.DECREMENTAR_BLOQUE)||(notificacion.getTipo()==Notificacion.INCREMENTAR_BLOQUE)){
			Posicione(notificacion.getNueva_posicion());
		}else if (notificacion.getTipo()==Notificacion.POCISIONAR){
			Posicione(notificacion.getNueva_posicion());
		}else if(notificacion.getTipo()==Notificacion.TRACK){
			Posicione(notificacion.getNueva_posicion());
		}
	}

	private void Posicione(int nueva_posicion) {
		this.dibujo.setValue(nueva_posicion);
	}

	private void Decrementar() {
		if (abstracion.esDecrementable()){
			this.dibujo.setValue(this.getAbstracion().getValor()-1);
			this.abstracion.decrementar();
		}
	}

	private void Incrementar() {
		if (abstracion.esIncrementable()){
			this.dibujo.setValue(this.getAbstracion().getValor()+1);
			this.abstracion.incrementar();
		}
	}
	@Override
	public void Enviar(Notificacion notificacion,Agente receptor) {
		receptor.Recibir(notificacion,this);
	}
}
