//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgScrollbar;

import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class PScrollbar extends Scrollbar{
	private CScrollbar controlador;
	public PScrollbar(AScrollbar abstraccion,CScrollbar controlador){
		super(HORIZONTAL, abstraccion.getValor(), 1, abstraccion.getMinimo(),abstraccion.getMaximo());
		this.controlador=controlador;
		this.setBackground(Color.WHITE);
		this.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				Notificar_alControlador(arg0);
			}
		});
	}
	public void Notificar_alControlador(AdjustmentEvent evento) {
		this.controlador.Recibir_Evento(evento);
	}
}
