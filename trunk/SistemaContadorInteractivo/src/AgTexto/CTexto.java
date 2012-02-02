package AgTexto;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Recurso.Agente;
import Recurso.Notificacion;

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
					//PocisionarContador();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				PocisionarContador();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private void PocisionarContador() {
		if(Validar_Pocisionar()){
			Notificacion notificacion = new Notificacion(Notificacion.POCISIONAR);
			if (!presentacion.getText().equals("")){
				notificacion.setNueva_posicion(Integer.valueOf(this.presentacion.getText()));
			}else{
				notificacion.setNueva_posicion(0);
			}
			this.abstraccion.setValor(notificacion.getNueva_posicion());
			Enviar(notificacion,this.supervisor);
		}else{
			this.presentacion.setText(String.valueOf(this.abstraccion.getValor()));
		}
	}
	private boolean Validar_Pocisionar() {
		if(!presentacion.getText().equals("")){
			return (this.abstraccion.getMinimo()<=Integer.valueOf(this.presentacion.getText()))&&(Integer.valueOf(this.presentacion.getText())<this.abstraccion.getMaximo());
		}else{
			return true;
		}
	}
	@Override
	public void Recibir(Notificacion notificacion,Agente emisor) {
		switch (notificacion.getTipo()) {
			case Notificacion.DECREMENTAR:Decrementar();break;
			case Notificacion.INCREMENTAR:Incremenrar();break;
			case Notificacion.INCREMENTAR_BLOQUE:Posicionar_Bloque(notificacion.getNueva_posicion());break;
			case Notificacion.DECREMENTAR_BLOQUE:Posicionar_Bloque(notificacion.getNueva_posicion());break;
			case Notificacion.TRACK:Posicionar_Track(notificacion.getNueva_posicion());break;
			default:break;
		}
	}

	private void Posicionar_Track(int nueva_posicion) {
		Posicionar_Bloque(nueva_posicion);
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
}
