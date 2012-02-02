//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maira CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgScrollbar;

import java.awt.Color;
import java.awt.Scrollbar;

public class PScrollbar extends Scrollbar{
	public PScrollbar(AScrollbar abstraccion){
		super(HORIZONTAL, abstraccion.getValor(), 1, abstraccion.getMinimo(),abstraccion.getMaximo());
		this.setBackground(Color.WHITE);
	}
}
