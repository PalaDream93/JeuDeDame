package Utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ProjetDame.Plateau;
import model.Case;

//Prise sur internet pour permettre la selection des pions a la souris
public class ListenerCase implements MouseListener{
	
	private Case case1;
	private Plateau plateau;

	
	public ListenerCase(Case case1, Plateau plateau) {
		super();
		this.case1 = case1;
		this.plateau = plateau;
	}


	public void mouseClicked(MouseEvent arg0) {
	
	}


	public void mouseEntered(MouseEvent arg0) {
		
	}


	public void mouseExited(MouseEvent arg0) {
		
	}


	public void mousePressed(MouseEvent arg0) {
		if(case1.isSelectionnee()){
			plateau.deplacer(case1);
		}
	}


	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	

}
