package ProjetDame;

import javax.swing.JFrame;
import javax.swing.UIManager;

//Initialise l'interface graphique
public class Lanceur {

	
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){}
		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new Plateau(9));
		f.setVisible(true);

	}

}
