package model;


import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;

import javax.swing.JPanel;


public class Case extends JPanel {
	//Ligne obligatoire(?)
	// private static final long serialVersionUID = -1839026893240660968L;
	
	private Couleur couleur;
	private boolean selectionnee;

	public Case(Couleur couleur){
		setLayout(new GridLayout(1,0));
		this.couleur=couleur;
		initCouleur();
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public boolean isSelectionnee() {
		return selectionnee;
	}

	public void setSelectionnee(boolean selectionnee) {
		this.selectionnee = selectionnee;
		if(selectionnee){
			setBackground(Color.BLUE);
			setForeground(Color.LIGHT_GRAY);
		}
		else {
			initCouleur();
		}
	}
	
	private void initCouleur(){
		switch(couleur){
		case BLANC :
			setBackground(Color.WHITE);
			setForeground(new Color(200, 200, 200));
			
			break;
		case NOIR :
			setBackground(Color.GRAY);
			setForeground(new Color(20, 20, 20));
			break;
		}
	}
	
	//Permet l'affiche graphique en couleur des cases
	@Override
	public void paintComponent(Graphics g){
		Paint paint;
		Graphics2D g2d;
		if (g instanceof Graphics2D) {
			g2d = (Graphics2D) g;
		}
		else {
			System.out.println("Error");
			return;
		}
		paint = new GradientPaint(0,0, getBackground(), getWidth(), getHeight(), getForeground());
		g2d.setPaint(paint);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
