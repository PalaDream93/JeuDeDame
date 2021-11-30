package model;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;


public class Pion extends JPanel {

	private static final long serialVersionUID = 1436178861615738480L;

	private Couleur couleur;
	private boolean monte;


	public Pion(Couleur couleur, boolean monte) {
		this.monte=monte;
		this.couleur = couleur;
		setOpaque(false);
		switch (couleur) {
		case BLANC :
			setForeground(Color.WHITE);
			setBackground(new Color(220, 220, 220));
			break;
		case NOIR :
			setForeground(new Color(70, 70, 70));
			setBackground(new Color(200, 200, 200));
			break;
		}
	}
	
	//Permet d'afficher l'interface graphique correctement
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
		g.fillOval(5, 5, getWidth()-10, getHeight()-10);

	}

	public Couleur getCouleur() {
		return couleur;
	}

	public boolean isMonte() {
		return monte;
	}

	public void setMonte(boolean monte) {
		this.monte = monte;
	}

}
