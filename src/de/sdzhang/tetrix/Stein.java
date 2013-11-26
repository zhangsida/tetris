package de.sdzhang.tetrix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Stein extends SteinElement{
	
	private final int DREHGRAD=90;
	
	//offset zu links oben Punkt des Fensters
	/*@Wichtige Variable*/
	private Point offset; 
	
	private int breite;//einheit pixel
	private int hoehe;//einheit pixel
	private SteinTyp steine;
	private Grad grad=Grad.GRAD0;

	public Stein(SteinTyp stein,Grad gr,Point offset){
		super(offset);
		setOffset(offset);
		setSteinTyp(stein);
		setGrad(gr);
	}
	
	public void drawStein(Graphics g){
		new DrawStein(g);
	}
	
	public void loeschStein(Graphics g,Color background){
		//die jetzigen Farben von Steinen werden gespeichert
		Color fuellfarbe=super.getFuellungfarbe();
		Color randfarbe=super.getRandfarbe();
		
		//ersetzen Steine mit Hintergrundfarbe
		super.setFuellungfarbe(background);
		super.setRandfarbe(background);
		drawStein(g);
		
		//nach Loeschen sind die originale Farben zurueckzusetzen
		super.setFuellungfarbe(fuellfarbe);
		super.setRandfarbe(randfarbe);
	}
	
	
	//	addiert den letzten PaintAnfangpunkt ap
	//mit Vielfach von {$einheit}
	public void makeOffset(int xOffset,int yOffset){
			this.setOffset(new Point(offset.x+xOffset*getEinheit(),offset.y+yOffset*getEinheit()));
		
	}
	
	public void nachLinks(){
		makeOffset(-1, 0);
	}
	
	public void nachRechts(){
		makeOffset(1, 0);
	}
	
	public void nachUnten(){
		makeOffset(0, 1);
	}
	
	public void nachOben(){
		makeOffset(0, -1);
	}
	
	public Point getOffset() {
		return offset;
	}
	public void setOffset(Point offset) {
		this.offset = offset;
	}
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite*getEinheit();
	}
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe*getEinheit();
	}
	public SteinTyp getSteinTyp() {
		return steine;
	}
	public void setSteinTyp(SteinTyp steine) {
		this.steine = steine;
	}

	public void drehen(){
		int grad=(getGrad().getWert()+this.DREHGRAD)%360;
		for(Grad gr: Grad.values()){
			if(gr.getWert()==grad){
				this.setGrad(gr);//durch Drehen wird der Wert von Grad geaendert
			}
		}
	}

	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad g) {
		this.grad = g;
	}
	class DrawStein{
		DrawStein(Graphics g) {
			
			switch(steine){
			
				case Quadrat_Stein:
					drawQuad_Stein(g);
					break;
				
				case L_Stein:
					drawL_Stein(g);
					break;
					
				case WL_Stein:
					drawWL_Stein(g);
					break;
					
				case T_Stein:
					drawT_Stein(g);
					break;
					
				case I_Stein:
					drawI_Stein(g);
					break;
				
				case S_Stein:
					drawS_Stein(g);
					break;
					
				case WS_Stein:
					drawWS_Stein(g);
					break;
			}
				
		}

		private void drawS_Stein(Graphics g) {
			switch(getGrad()){
			case GRAD0:
			case GRAD180:
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+getEinheit()+i*getEinheit(),offset.y);
				}
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y+getEinheit());
				}
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD90:
			case GRAD270:
				//S_Stein nach Uhrrichtung 90 Grad drehen////////////
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x,offset.y+i*getEinheit());
				}
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+getEinheit(),offset.y+getEinheit()+i*getEinheit());
				}
				setBreite(2);
				setHoehe(3);
				break;
			}
		}

		private void drawI_Stein(Graphics g) {
			switch(getGrad()){
			case GRAD0:
			case GRAD180:
				//I_Stein senkrecht///////////////////////////////
				for(int i=0;i<4;i++){
					drawSteinElement(g,offset.x,offset.y+i*getEinheit());
				}
				setBreite(1);
				setHoehe(4);
				break;
			case GRAD90:
			case GRAD270:
				//I_Stein waagerecht/////////////////////////////////
				for(int i=0;i<4;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y);
				}
				setBreite(4);
				setHoehe(1);
				break;
			}
		}

		private void drawT_Stein(Graphics g) {
			switch(getGrad()){
			case GRAD0:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y);
				}
				drawSteinElement(g,offset.x+getEinheit(),offset.y+getEinheit());
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD90:
				drawSteinElement(g,offset.x,offset.y+getEinheit());
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+getEinheit(),offset.y+i*getEinheit());
				}
				setBreite(2);
				setHoehe(3);
				break;
			case GRAD180:
				//T_Stein nach Uhrrichtung 180 Grad drehen///////////
				for(int i=0; i<3; i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y+getEinheit());
				}
				drawSteinElement(g,offset.x+getEinheit(),offset.y);
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD270:
				//T_Stein nach Uhrrichtung 270 Grad drehen////////////
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x,offset.y+i*getEinheit());
				}
				drawSteinElement(g,offset.x+getEinheit(),offset.y+getEinheit());
				setBreite(2);
				setHoehe(3);
				break;
			}
		}

		private void drawWL_Stein(Graphics g) {
			//Wiederspiegelung von L_Stein
			switch(getGrad()){
			case GRAD0:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+getEinheit(),offset.y+i*getEinheit());
				}
				drawSteinElement(g,offset.x,offset.y+2*getEinheit());
				setBreite(2);
				setHoehe(3);
				break;
			case GRAD90:
				drawSteinElement(g);
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y+getEinheit());
				}
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD180:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x,offset.y+i*getEinheit());
				}
				drawSteinElement(g, offset.x+getEinheit(), offset.y);
				setBreite(2);
				setHoehe(3);
				break;
			case GRAD270:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y);
				}
				drawSteinElement(g, offset.x+2*getEinheit(), offset.y+getEinheit());
				setBreite(3);
				setHoehe(2);
				break;
			}
		}		
		
		private void drawWS_Stein(Graphics g) {
			//Wiederspiegelung von S_Stein
			switch(getGrad()){
			case GRAD0:
			case GRAD180:
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y);
				}
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+getEinheit()+i*getEinheit(),offset.y+getEinheit());
				}
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD90:
			case GRAD270:
				//90 Grad von W_S_Stein
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x,offset.y+getEinheit()+i*getEinheit());
				}
				for(int i=0;i<2;i++){
					drawSteinElement(g,offset.x+getEinheit(),offset.y+i*getEinheit());
				}
				setBreite(2);
				setHoehe(3);
				break;
			}
		}

		private void drawL_Stein(Graphics g) {
			switch(grad){
			case GRAD0:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x,offset.y+i*getEinheit());
				}
				drawSteinElement(g,offset.x+getEinheit(),offset.y+2*getEinheit());
				
				setBreite(2);
				setHoehe(3);
				break;
				
			case GRAD90:
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y);
				}
				drawSteinElement(g, offset.x, offset.y+getEinheit());
				
				setBreite(3);
				setHoehe(2);
				break;
			case GRAD180:
				drawSteinElement(g,offset.x,offset.y);
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+getEinheit(),offset.y+i*getEinheit());
				}
				setBreite(2);
				setHoehe(3);
				break;
			case GRAD270:
				drawSteinElement(g,offset.x+2*getEinheit(),offset.y);
				for(int i=0;i<3;i++){
					drawSteinElement(g,offset.x+i*getEinheit(),offset.y+getEinheit());
				}
				setBreite(3);
				setHoehe(2);
				break;
			}	
		}

		private void drawQuad_Stein(Graphics g) {
			drawSteinElement(g);
			drawSteinElement(g,offset.x+getEinheit(),offset.y);
			drawSteinElement(g,offset.x,offset.y+getEinheit());
			drawSteinElement(g,offset.x+getEinheit(),offset.y+getEinheit());
			
			setBreite(2);
			setHoehe(2);
			
		}

	}

}
