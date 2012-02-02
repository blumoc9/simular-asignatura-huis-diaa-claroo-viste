//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgTexto;

import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import bean.JTextFieldValidator;

public class PTexto extends JTextFieldValidator {
	private CTexto controlador;
	public PTexto(ATexto abstraccion,CTexto controlador){
		super();
		this.setText(String.valueOf(abstraccion.getValor()));
		this.setFont(new java.awt.Font("Bodoni MT Poster Compressed",1,72));
		this.setTipoCaracteresPermitidos(JTextFieldValidator.SOLO_NUMEROS);
		this.setMaximaLongitud(2);
		this.controlador=controlador;
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				Notificar_alControlador(arg0);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void Notificar_alControlador(KeyEvent evento) {
		this.controlador.Recibir_Evento(evento);
	}
}
