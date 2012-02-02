package AgScrollbar;

import java.awt.Color;
import java.awt.Scrollbar;

public class PScrollbar extends Scrollbar{
	public PScrollbar(AScrollbar abstraccion){
		super(HORIZONTAL, abstraccion.getValor(), 1, abstraccion.getMinimo(),abstraccion.getMaximo());
		this.setBackground(Color.WHITE);
	}
}
