package AgSistema;
import java.awt.BorderLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Sistema Contador Interactivo");
			getContentPane().setBackground(new java.awt.Color(173,216,230));
			{
				lienzo = new JPanel();
				getContentPane().add(lienzo, BorderLayout.NORTH);
				lienzo.setLayout(null);
				lienzo.setPreferredSize(new java.awt.Dimension(390, 270));
				lienzo.setBackground(new java.awt.Color(173,216,230));
			}
			pack();
			this.setSize(402, 322);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	public JPanel getLienzo() {
		return lienzo;
	}
}
