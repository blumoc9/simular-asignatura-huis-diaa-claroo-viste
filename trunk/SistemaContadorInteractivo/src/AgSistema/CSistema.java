//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgSistema;

import java.awt.Scrollbar;
import java.util.Vector;

import Recurso.Agente;
import Recurso.Notificacion;

import AgScrollbar.CScrollbar;
import AgTexto.CTexto;

public class CSistema implements Agente{
	private ASistema abstraccion;
	private PSistema presentacion;
	private Vector<Agente> subordinados;
	private CScrollbar scrollbar;
	private CScrollbar scrollbar2;
	private CTexto texto;
	
	public CSistema() {
		super();
		abstraccion = new ASistema();
		presentacion = new PSistema();
		AgregarAgentes();
		MostrarPresentacion();
	}
	

	private void MostrarPresentacion() {
		this.presentacion.setVisible(true);
		this.presentacion.setLocationRelativeTo(null);
	}

	private void AgregarAgentes() {
		this.subordinados = new Vector<Agente>();
		scrollbar = new CScrollbar(10, 20, 0,this);
		scrollbar2 = new CScrollbar(10, 20, 0,this);
		texto = new CTexto(0, 10, 20, this);
		this.subordinados.add(scrollbar);
		this.subordinados.add(scrollbar2);
		this.subordinados.add(texto);
		presentacion.getLienzo().add(scrollbar.getDibujo());
		scrollbar.getDibujo().setBounds(10, 10, this.getPresentacion().getLienzo().getWidth()-80, 50);
		presentacion.getLienzo().add(scrollbar2.getDibujo());
		scrollbar2.getDibujo().setOrientation(Scrollbar.VERTICAL);
		scrollbar2.getDibujo().setBounds(this.getPresentacion().getLienzo().getWidth()-60, 10, 50, this.getPresentacion().getLienzo().getHeight()-20);
		presentacion.getLienzo().add(texto.getPresentacion());
		texto.getPresentacion().setBounds(10, 70, scrollbar.getDibujo().getWidth(), scrollbar2.getDibujo().getHeight()-60);
	}

	public ASistema getAbstraccion() {
		return abstraccion;
	}

	public PSistema getPresentacion() {
		return presentacion;
	}

	@Override
	public void Recibir(Notificacion notificacion ,Agente emisor) {
		Comunicar_Cambios(emisor,notificacion);
	}

	private void Comunicar_Cambios(Agente agente,Notificacion notificacion) {
		for(int i=0;i<this.subordinados.size();i++){
			Enviar(notificacion,this.subordinados.get(i));
		}
	}

	@Override
	public void Enviar(Notificacion notificacion,Agente receptor) {
		receptor.Recibir(notificacion,this );
	}
}
