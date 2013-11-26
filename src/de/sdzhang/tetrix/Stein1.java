package de.sdzhang.tetrix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stein1 extends SteinElement{
	
	private final int DREHGRAD=90;
	
	/*@Wichtige Variable*/
	private Point anfangPunkt;//die relativ (x,y) zu Spielfeld
	private Point offset;//offset von spielfeld in Pixel
	
	private int breite;//einheit pixel
	private int hoehe;//einheit pixel
	
	private SteinTyp steine;
	
	private List<Point> punkteXY=new ArrayList<Point>();//indexListe von relativ Punkte in TPunkte

	private Grad grad=Grad.GRAD0;//wie ein Stein erscheinen soll

	public Stein1(SteinTyp stein,Grad gr,Point anfangPunkt,Point offset){
		super(offset);
		this.offset=offset;	
		setAnfangPunkt(anfangPunkt);
		setSteinTyp(stein);
		setGrad(gr);
	}
	
	//drawStein macht Stein nach seine PunkteXY 
	public void drawStein(Graphics g){		
		for(Iterator<Point> i=punkteXY.iterator();i.hasNext();){
			Point p=i.next();
			int x=(p.x+anfangPunkt.x)*16+offset.x;
			int y=(p.y+anfangPunkt.y)*16+offset.y;
			Point point=new Point(x,y);
			drawSteinElement(g,point);
		}
	}
	
	//wenn stein auf spielfeld bewegt, anfangPunkt von Stein bewegt
	public void makeOffset(int xOffset,int yOffset){
			this.setAnfangPunkt(new Point(anfangPunkt.x+xOffset,anfangPunkt.y+yOffset));
		
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
	
	public Point getAnfangPunkt() {
		return anfangPunkt;
	}
	public void setAnfangPunkt(Point anfangPunkt) {
		this.anfangPunkt = anfangPunkt;
	}
	public int getBreite() {
		return breite;
	}

	public void setBreite(){
		int maxX=0;
		for(Iterator<Point> i=punkteXY.iterator();i.hasNext();){
			int x=i.next().x;
			if(x>=maxX){
				maxX=x;
			}
		}
		this.breite=(maxX+1);//*getEinheit();
	}
	public int getHoehe() {
		return hoehe;
	}
	
	public void setHoehe(){
		int maxY=0;
		for(Iterator<Point> i=punkteXY.iterator();i.hasNext();){
			int y=i.next().y;
			if(y>=maxY){
				maxY=y;
			}
		}
		this.hoehe=(maxY+1);//*getEinheit();
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
		setBreite();
		setHoehe();
	}
	
	public void zurueckDrehen(){
		int grad=(getGrad().getWert()+3*this.DREHGRAD)%360;//falsch gedrehter Stein durch noch mal 270 drehung zurueck
		for(Grad gr: Grad.values()){
			if(gr.getWert()==grad){
				this.setGrad(gr);//durch Drehen wird der Wert von Grad geaendert
			}
		}
		setBreite();
		setHoehe();
	}
	
	public Grad getGrad() {
		return grad;
	}

	public void setGrad(Grad g){//nun bei der Inizialisierung aufgerufen
		this.grad = g;
		switch(this.steine){
		case I_Stein:
			switch(grad){
			case GRAD0:
			case GRAD180:
				setPunkteXY(SteinTyp1.I_Stein.getPunkte());
				break;
			case GRAD90:
			case GRAD270:
				setPunkteXY(SteinTyp1.I_Stein90.getPunkte());
				break;
			}
			break;
		case L_Stein:
			switch(grad){
			case GRAD0:
				setPunkteXY(SteinTyp1.L_Stein.getPunkte());
				break;
			case GRAD90:
				setPunkteXY(SteinTyp1.L_Stein90.getPunkte());
				break;
			case GRAD180:
				setPunkteXY(SteinTyp1.L_Stein180.getPunkte());
				break;
			case GRAD270:
				setPunkteXY(SteinTyp1.L_Stein270.getPunkte());
				break;
			}
			break;
		case Quadrat_Stein:
			switch(grad){
			case GRAD0:
			case GRAD90:
			case GRAD180:
			case GRAD270:
				setPunkteXY(SteinTyp1.Quadrat_Stein.getPunkte());
				break;
			}
			break;
		case S_Stein:
			switch(grad){
			case GRAD0:
			case GRAD180:
				setPunkteXY(SteinTyp1.S_Stein.getPunkte());
				break;
			case GRAD90:
			case GRAD270:
				setPunkteXY(SteinTyp1.S_Stein90.getPunkte());
				break;
			}
			break;
		case T_Stein:
			switch(grad){
			case GRAD0:
				setPunkteXY(SteinTyp1.T_Stein.getPunkte());
				break;
			case GRAD90:
				setPunkteXY(SteinTyp1.T_Stein90.getPunkte());
				break;
			case GRAD180:
				setPunkteXY(SteinTyp1.T_Stein180.getPunkte());
				break;
			case GRAD270:
				setPunkteXY(SteinTyp1.T_Stein270.getPunkte());
				break;
			}
			break;
		case WL_Stein:
			switch(grad){
			case GRAD0:
				setPunkteXY(SteinTyp1.WL_Stein.getPunkte());
				break;
			case GRAD90:
				setPunkteXY(SteinTyp1.WL_Stein90.getPunkte());
				break;
			case GRAD180:
				setPunkteXY(SteinTyp1.WL_Stein180.getPunkte());
				break;
			case GRAD270:
				setPunkteXY(SteinTyp1.WL_Stein270.getPunkte());
				break;
			}
			break;
		case WS_Stein:
			switch(grad){
			case GRAD0:
			case GRAD180:
				setPunkteXY(SteinTyp1.WS_Stein.getPunkte());
				break;
			case GRAD90:
			case GRAD270:
				setPunkteXY(SteinTyp1.WS_Stein90.getPunkte());
				break;
			}
			break;
		}
	}

	public List<Point> getPunkteXY() {
		return punkteXY;
	}

	public void setPunkteXY(List<Point> punkteXY) {
		this.punkteXY = punkteXY;
		setBreite();
		setHoehe();
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


}
