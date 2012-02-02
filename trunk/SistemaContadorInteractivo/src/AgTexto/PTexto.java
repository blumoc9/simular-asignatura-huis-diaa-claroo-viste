package AgTexto;

import java.awt.Font;

import javax.swing.JTextField;

import bean.JTextFieldValidator;

public class PTexto extends JTextFieldValidator {
	public PTexto(ATexto abstraccion){
		super();
		this.setText(String.valueOf(abstraccion.getValor()));
		this.setFont(new java.awt.Font("Bodoni MT Poster Compressed",1,72));
		this.setTipoCaracteresPermitidos(JTextFieldValidator.SOLO_NUMEROS);
	}
}
