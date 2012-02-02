package AgScrollbar;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import Recurso.Agente;
import Recurso.Solicitud;

public class CScrollbar implements Agente{
	private PScrollbar dibujo;
	private AScrollbar abstracion;
	private Agente supervisor;
	
	public CScrollbar(int valor,int max,int min,Agente supervisor) {
		super();
		this.abstracion = new AScrollbar(min,valor,max);
		this.dibujo = new PScrollbar(abstracion);
		this.supervisor = supervisor;
		Escuchador();
	}
	
	public void Escuchador(){
		this.dibujo.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				EnviarAccion(arg0);
			}
		});
	}
	
	public void EnviarAccion(AdjustmentEvent accion){
		Solicitud solicitud = new Solicitud();
		if(accion.getAdjustmentType()==accion.UNIT_INCREMENT){
			solicitud.setTipo(Solicitud.INCREMENTAR);
		}else if (accion.getAdjustmentType()==accion.UNIT_DECREMENT){
			solicitud.setTipo(Solicitud.DECREMENTAR);
		}else if (accion.getAdjustmentType()==accion.BLOCK_DECREMENT){
			solicitud.setTipo(Solicitud.DECREMENTAR_BLOQUE);
			solicitud.setNueva_posicion(this.dibujo.getValue());
		}else if (accion.getAdjustmentType()==accion.BLOCK_INCREMENT){
			solicitud.setTipo(Solicitud.INCREMENTAR_BLOQUE);
			solicitud.setNueva_posicion(this.dibujo.getValue());
		}
		Enviar_Solicitud(this.supervisor,solicitud);
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
	public void Recibir_Solitud(Agente agente,Solicitud solicitud) {
		if(solicitud.getTipo()==Solicitud.INCREMENTAR){
			Incrementar();
		}else if (solicitud.getTipo()==Solicitud.DECREMENTAR){
			Decrementar();
		}else if ((solicitud.getTipo()==Solicitud.DECREMENTAR_BLOQUE)||(solicitud.getTipo()==Solicitud.INCREMENTAR_BLOQUE)){
			Posicione(solicitud.getNueva_posicion());
		}else if (solicitud.getTipo()==Solicitud.POCISIONAR){
			Posicione(solicitud.getNueva_posicion());
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
	public void Enviar_Solicitud(Agente agente,Solicitud solicitud) {
		agente.Recibir_Solitud(this,solicitud);
	}

	
}
