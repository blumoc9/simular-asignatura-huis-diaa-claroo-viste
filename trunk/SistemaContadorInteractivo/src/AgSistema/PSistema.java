//Integrantes:
// Santana, Adriana CI: 18.801.197
// Paez, Maria CI: 19.618.874
// Arteaga, Luis CI: 19.696.160
// Colmenarez, Fernando CI: 18.923.926 
package AgSistema;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class PSistema extends javax.swing.JFrame {
	private JPanel lienzo;
	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public PSistema() {
		super();
		initGUI();
	}
	
	public JPanel getLienzo() {
		return lienzo;
	}
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Sistema Contador Interactivo");
			getContentPane().setBackground(new java.awt.Color(173,216,230));
			this.setResizable(false);
			{
				lienzo = new JPanel();
				getContentPane().add(lienzo, BorderLayout.NORTH);
				lienzo.setLayout(null);
				lienzo.setPreferredSize(new java.awt.Dimension(406, 298));
				lienzo.setBackground(new java.awt.Color(173,216,230));
			}
			pack();
			this.setSize(406, 329);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
}
