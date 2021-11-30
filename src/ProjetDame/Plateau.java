package ProjetDame;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Utils.ListenerCase;
import Utils.ListenerPion;
import model.Case;
import model.Couleur;
import model.Pion;


public class Plateau extends JPanel {

	private static final long serialVersionUID = 6726708245444190460L;

	private static final int TAILLE=9;

	private Case caseActive;

	private boolean tourNoir;

	public Plateau(int taille){
		tourNoir=false;
		//Ajouter les cases noir et blanche sur le plateau
		setLayout(new GridLayout(TAILLE, TAILLE));
		for(int i=0; i<TAILLE; i++){
			for(int j=0; j<TAILLE; j++){
				if((j%2==0 && i%2==0) || (j%2!=0 && i%2!=0)){
					ajouterCase(Couleur.NOIR);
				}
				else{
					ajouterCase(Couleur.BLANC);
				}
			}
		}
		init();
	}
//Permet d'ajouter une case
	private void ajouterCase(Couleur couleur){
		Case case1 = new Case(couleur);
		case1.addMouseListener(new ListenerCase(case1, this));
		add(case1);
	}
//Creer un pion
	private Pion creerPion(Couleur couleur, boolean monte){
		Pion pion = new Pion(couleur, monte);
		pion.addMouseListener(new ListenerPion(pion, this));
		return pion;
	}
//Ajouter un pion toute les 2 cases en fonction de la couleur
	private void init(){
		for(int j=0; j<TAILLE*3; j+=2){
			getCase(j).add(creerPion(Couleur.NOIR, false));
			getCase(TAILLE*TAILLE-j-1).add(creerPion(Couleur.BLANC, true));
		}
	}

	public Case getCase(int i, int j){
		return (Case) getComponent(j+i*TAILLE);
	}

	public Case getCase(int i){
		return (Case) getComponent(i);
	}
//Affiche les cases jouable après selection du pion (code trouvé en ligne a modifier)
	public void afficherPossibilites(Pion p){
		if((p.getCouleur().equals(Couleur.NOIR) && tourNoir) || (p.getCouleur().equals(Couleur.BLANC) && !tourNoir)){
			int i=0;
			int j=0;
			for(int k=0; k<TAILLE*TAILLE; k++){
				getCase(k).setSelectionnee(false);
				if(getCase(k).getComponentCount()!=0 && getCase(k).getComponent(0).equals(p)){
					caseActive=getCase(k);
					i=k/TAILLE;
					j=k%TAILLE;

				}
			}
			selectionnerCases(i, j, p.getCouleur());
		}
	}

	public void selectionnerCases(int i, int j, Couleur couleur){
		Pion pion = (Pion)(getCase(i, j).getComponent(0));
		//Selectionne la case en fonction de la TAILLE et des pos i et j du pion (cf premier commit ebauche)
		if(pion.isMonte()){
			if(i-1>=0 && j-1>=0 && getCase(i-1, j-1).getComponentCount()==0){
				getCase(i-1, j-1).setSelectionnee(true);
			}
			else if(i-2>=0 && j-2>=0 && getCase(i-2, j-2).getComponentCount()==0 && !((Pion)(getCase(i-1, j-1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i-2, j-2).setSelectionnee(true);
			}
			if(i-1>=0 && j+1<TAILLE && getCase(i-1, j+1).getComponentCount()==0){
				getCase(i-1, j+1).setSelectionnee(true);
			}
			else if(i-2>=0 && j+2<TAILLE && getCase(i-2, j+2).getComponentCount()==0 && !((Pion)(getCase(i-1, j+1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i-2, j+2).setSelectionnee(true);
			}
		}
		else{
			if(i+1<TAILLE && j+1<TAILLE && getCase(i+1, j+1).getComponentCount()==0){
				getCase(i+1, j+1).setSelectionnee(true);
			}
			else if(i+2<TAILLE && j+2<TAILLE && getCase(i+2, j+2).getComponentCount()==0 && !((Pion)(getCase(i+1, j+1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i+2, j+2).setSelectionnee(true);
			}
			if(i+1<TAILLE && j-1>=0 && getCase(i+1, j-1).getComponentCount()==0){
				getCase(i+1, j-1).setSelectionnee(true);
			}
			else if(i+2<TAILLE && j-2>=0 && getCase(i+2, j-2).getComponentCount()==0 && !((Pion)(getCase(i+1, j-1).getComponent(0))).getCouleur().equals(couleur)){
				getCase(i+2, j-2).setSelectionnee(true);
			}
			
		}
	}
//Fonction pour se déplacer (adapter du projet dame js)
	public void deplacer(Case case1){
		case1.add(caseActive.getComponent(0));
		//verif si la case est bien un pion
		for(int k=0; k<TAILLE*TAILLE; k++){
			getCase(k).setSelectionnee(false);
		}
		//Si c'est le cas on update(remove,validate,repaint) la case de depart et d'arrivé
		if(Math.abs(getLigne(case1)-getLigne(caseActive))==2){
			int i = (getLigne(case1)+getLigne(caseActive))/2;
			int j = (getColonne(case1)+getColonne(caseActive))/2;
			getCase(i, j).removeAll();
			getCase(i, j).validate();
			getCase(i, j).repaint();
		}
		tourNoir=!tourNoir;
		caseActive.removeAll();
		caseActive.repaint();
		caseActive=null;
		case1.repaint();
		//Verif si la case est prise
		if(getLigne(case1)==0){
			Pion p=(Pion)(case1.getComponent(0));
			p.setMonte(false);
		}
		if(getLigne(case1)==TAILLE-1){
			Pion p=(Pion)(case1.getComponent(0));
			p.setMonte(true);
		}
	}
//Obtenir la pos des ligne et colonne (remplacer le tab[][])
	private int getLigne(Case case1){
		int res=0;
		for(int i=0; i<TAILLE*TAILLE; i+=2){
			if(getCase(i).equals(case1)){
				res=i/TAILLE;
			}
		}
		return res;
	}

	private int getColonne(Case case1){
		int res=0;
		for(int i=0; i<TAILLE*TAILLE; i+=2){
			if(getCase(i).equals(case1)){
				res=i%TAILLE;
			}
		}
		return res;
	}
	
	


}
