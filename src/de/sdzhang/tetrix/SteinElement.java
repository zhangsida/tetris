package de.sdzhang.tetrix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class SteinElement {
	
	private int einheit=16;
	private Color Randfarbe;
	private Color Fuellungfarbe;
	private Point punkt;//die tatsaechliche (x,y) auf dem TetrixWindow
	
	public SteinElement(Point p){
		setPunkt(p);
		setFuellungfarbe(Color.red);
		setRandfarbe(Color.black);		
	}

	public SteinElement(Point p, Color randcolor,Color incolor){
		setPunkt(p);
		setRandfarbe(randcolor);
		setFuellungfarbe(incolor);
	}
	
	public void drawSteinElement(Graphics g){
		g.setColor(getFuellungfarbe());
		g.fillRoundRect(punkt.x,punkt.y, getEinheit(), getEinheit(), 6, 6);
		g.setColor(getRandfarbe());
		g.drawRoundRect(punkt.x,punkt.y, getEinheit(), getEinheit(), 6, 6);
	}
	public void drawSteinElement(Graphics g, Point ap ){
		setPunkt(ap);
		drawSteinElement(g);
	}
	
	public void drawSteinElement(Graphics g, int x, int y){
		this.drawSteinElement(g, new Point(x,y));
	}
//	//addiert den letzten PaintAnfangpunkt (ax, ay) 
//	//mit Vielfach von {$einheit}
//	public void makeOffset(int xOffset,int yOffset){
//		setAnfangpunkt(new Point(anfangpunkt.x+xOffset*getEinheit(),
//				anfangpunkt.y+yOffset*getEinheit()));
//	}
	
	public int getEinheit() {
		return einheit;
	}
	
	public void setEinheit(int einheit) {
		this.einheit = einheit;
	}
	public Color getRandfarbe() {
		return Randfarbe;
	}
	public void setRandfarbe(Color randfarbe) {
		Randfarbe = randfarbe;
	}
	public Color getFuellungfarbe() {
		return Fuellungfarbe;
	}
	public void setFuellungfarbe(Color fuellungfarbe) {
		Fuellungfarbe = fuellungfarbe;
	}

	public Point getPunkt() {
		return punkt;
	}


	public void setPunkt(Point anfangpunkt) {
		this.punkt = anfangpunkt;
	}
}
