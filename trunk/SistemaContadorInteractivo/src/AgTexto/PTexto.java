//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
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
		this.setMaximaLongitud(2);
	}
}
