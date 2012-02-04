package AgScrollbar;

import java.awt.event.AdjustmentEvent;

import Recurso.Agente;
import Recurso.Notificacion;

/** Clase Controladora para el Agente Scrollbar
 *  esta clase contiene la presentacion y abstraccion del agente
 *  ademas de un supervisor que puede ser cualquier objeto que implemente
 *  la interface de agente creada en este proyecto
 *
 * 
 * @author Elam Torres
 * @author Santana, Adriana CI: 18.801.197
 * @author Paez, Maria CI: 19.618.874
 * @author Arteaga, Luis CI: 19.696.160
 * @author Colmenarez, Fernando CI: 18.923.926 
 * @version 1.2
 */

public class CScrollbar implements Agente{
	private PScrollbar dibujo;
	private AScrollbar abstracion;
	private Agente supervisor;
	/**
	 * @param valor Valor de inicializacion del intervalo
	 * @param max Valor maximo que puede tomar el intervalo
	 * @param min Valor minimo que puede tomar el intervalo
	 * @param Agente Agente supervisos a quien responde el agente(CScrollbar)
	 * @author Elam Torres
	 * @author Adriana Santana
	 */
	public CScrollbar(int valor,int max,int min,Agente supervisor) {
		super();
		this.abstracion = new AScrollbar(min,valor,max);
		this.dibujo = new PScrollbar(abstracion,this);
		this.supervisor = supervisor;
	}
	/**
	 * Este metodo se encargar de hacer todo el proceso que implica decrementar
	 * el valo del intervalo que posee la abstraccion del agente
	 * 
	 */
	private void Decrementar() {
		if (abstracion.esDecrementable()){
			this.dibujo.setValue(this.getAbstracion().getValor()-1);
			this.abstracion.decrementar();
		}
	}
	/**
	 * Metodod abstracto que permite al agente enviar notificaciones de cambio
	 * a otros agentes en el sistema
	 * 
	 * @param notificacion objeto que contiene informacion referente a la notificacion
	 * como lo es el tipo de notificacion
	 * @param receptor Agente quien se encargara de de recibir procesar y comunicar la 
	 * notificacion al resto de sus agentes subordinados
	 */
	@Override
	public void Enviar(Notificacion notificacion,Agente receptor) {
		receptor.Recibir(notificacion,this);
	}
	/**
	 * Metodo que permite realizar todo el proceso de envio de una 
	 * notificacion a su agente supervisor
	 * @param accion evento enviado por la presentacion al controlador para
	 * que este lo procese
	 */
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
	/**
	 * Permite al controlador devolver la abstraccion del agente al cual el controla
	 * @return objeto abstraccion del agente
	 */
	public AScrollbar getAbstracion() {
		return abstracion;
	}
	/**
	 * 
	 * @return
	 */
	public PScrollbar getDibujo() {
		return dibujo;
	}

	private void Incrementar() {
		if (abstracion.esIncrementable()){
			this.dibujo.setValue(this.getAbstracion().getValor()+1);
			this.abstracion.incrementar();
		}
	}

	private void Posicione(int nueva_posicion) {
		this.dibujo.setValue(nueva_posicion);
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

	public void Recibir_Evento(AdjustmentEvent accion){
		EnviarAccion(accion);
	}

	public void setAbstracion(AScrollbar abstracion) {
		this.abstracion = abstracion;
	}
	public void setDibujo(PScrollbar dibujo) {
		this.dibujo = dibujo;
	}
}
