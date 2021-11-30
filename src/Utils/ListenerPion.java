package Utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ProjetDame.Plateau;
import model.Pion;

//Prise sur internet pour l'extension awt pour permettre la selection des pions a la souris
public class ListenerPion implements MouseListener {
	
	private Plateau plateau;
	private Pion pion;
	
	public ListenerPion(Pion pion, Plateau plateau){
		this.plateau=plateau;
		this.pion=pion;
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
	
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		plateau.afficherPossibilites(pion);
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

}
