package AgTexto;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Recurso.Agente;
import Recurso.Solicitud;

public class CTexto implements Agente {
	private ATexto abstraccion;
	private PTexto presentacion;
	private Agente supervisor;
	
	public CTexto(int min,int val,int max,Agente supervisor) {
		super();
		this.abstraccion = new ATexto(min, val, max);
		this.presentacion = new PTexto(abstraccion);
		this.supervisor = supervisor;
		AgregarEscuchador();
	}
	private void AgregarEscuchador() {
		this.presentacion.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyChar()==arg0.VK_ENTER){
					PocisionarContador();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				//PocisionarContador();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void PocisionarContador() {
		if(Validar_Pocisionar()){
			Solicitud solicitud = new Solicitud(Solicitud.POCISIONAR);
			solicitud.setNueva_posicion(Integer.valueOf(this.presentacion.getText()));
			this.abstraccion.setValor(Integer.valueOf(this.presentacion.getText()));
			Enviar_Solicitud(this.supervisor, solicitud);
		}else{
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
		}
	}
	private boolean Validar_Pocisionar() {
		return (this.abstraccion.getMinimo()<=Integer.valueOf(this.presentacion.getText()))&&(Integer.valueOf(this.presentacion.getText())<this.abstraccion.getMaximo());
	}
	@Override
	public void Recibir_Solitud(Agente agente, Solicitud solicitud) {
		switch (solicitud.getTipo()) {
			case Solicitud.DECREMENTAR:Decrementar();break;
			case Solicitud.INCREMENTAR:Incremenrar();break;
			case Solicitud.INCREMENTAR_BLOQUE:Posicionar_Bloque(solicitud.getNueva_posicion());break;
			case Solicitud.DECREMENTAR_BLOQUE:Posicionar_Bloque(solicitud.getNueva_posicion());break;
			default:break;
		}
	}

	private void Posicionar_Bloque(int nueva_posicion) {
		if((this.abstraccion.getMinimo()<=nueva_posicion)&&(nueva_posicion<=this.abstraccion.getMaximo())){
			this.presentacion.setText(String.valueOf(nueva_posicion));
		}
	}
	private void Incremenrar() {
		if(this.abstraccion.esIncrementable()){
			this.abstraccion.incrementar();
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
		}
	}
	private void Decrementar() {
		if(this.abstraccion.esDecrementable()){
			this.abstraccion.decrementar();
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
		}
	}
	@Override
	public void Enviar_Solicitud(Agente agente, Solicitud solicitud) {
		agente.Recibir_Solitud(this, solicitud);
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
}
