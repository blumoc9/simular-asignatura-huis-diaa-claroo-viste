package AgBoton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PBoton extends JButton {
	private CBoton controlador;

	public PBoton(String texto,CBoton controlador) {
		super();
		this.controlador = controlador;
		this.setText(texto);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evento) {
				Notifica_alControlador();				
			}
		});
	}

	protected void Notifica_alControlador() {
		this.controlador.Procesar_Evento();
	}
	
	
}
