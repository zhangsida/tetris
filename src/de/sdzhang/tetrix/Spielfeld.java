package de.sdzhang.tetrix;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Spielfeld extends SteinElement {
	
	private static final int EINHEIT = 16;

	private Point offset;//in pixel zu Spielfenster
	
	private int breite;//relativ Breite von Einheit 16
	private int hoehe;//relativ Hoehe von Einheit 16
	
	private boolean steinAufBoden=false;
	private List<Point> boden=new ArrayList<Point>();
	private int bodenHoehe;
	
	private Stein1 aStein;//aktuellStein
	private Stein1 naechstStein;

	private Point faellPunkt;//von wo auf dem Fenster Stein faellt

	public Spielfeld(Point offset,int breite,int hoehe){
		super(offset);
		setOffset(offset);
		setBreite(breite);
		setHoehe(hoehe);	
		setFaellPunkt();
		setAktuellStein(makeStein1());
		setNaechstStein(makeStein1());
	}

	public void drawBoden(Graphics g){	
		try{
		if(!boden.isEmpty()){
			for(Iterator<Point> i=boden.iterator();i.hasNext();){
				Point p=new Point(i.next());
				int x=p.x*16+offset.x;
				int y=p.y*16+offset.y;
				Point point=new Point(x,y);
				drawSteinElement(g,point);
			}
		}
		}catch(Exception e){System.err.println(e.toString());}
	}
	
//	public Stein makeStein(){
//		int i=(int)(Math.random()*7);//7 verschiedene Steine
//		int j=(int)(Math.random()*4);//4 verschiedene Grad
//		return new Stein(SteinTyp.values()[i],
//				Grad.values()[j],this.faellPunkt);
//	}
	
	public Stein1 makeStein1(){
		int i=(int)(Math.random()*7);//7 verschiedene Steine
		int j=(int)(Math.random()*4);//4 verschiedene Grad
		return new Stein1(SteinTyp.values()[i],
				Grad.values()[j],this.faellPunkt,offset);
		
	}
	
	public void nachOben(){
			aStein.nachOben();
	}
	
	public void nachLinks(){
		//pruef, ob ueber rechte Grenze uebergeht
		boolean b1=getAktuellStein().getAnfangPunkt().x-1>=0;
		//pruef ,ob nach der Bewegung in den Stein reingeht
		boolean b2=false;
		
		if(b1){
			aStein.nachLinks();
		}
		
		//////////////////////pruefObSteinAufDenBoden()
		Point offset=aStein.getAnfangPunkt();
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point point=new Point(i.next());
			Point p=new Point(point.x+offset.x,point.y+offset.y);
			if(!boden.isEmpty()){
				if(this.boden.contains(p)){
					b2=true;
					break;
				}
			}
			else{break;}
		}
		if(b2){
			aStein.nachRechts();
		}
	}
	
	public void nachRechts(){
		//pruef, ob ueber rechte Grenze uebergeht
		boolean b1=getAktuellStein().getAnfangPunkt().x
					+getAktuellStein().getBreite()+1
					<=getBreite();
		//pruef ,ob nach der Bewegung in den Stein reingeht
		boolean b2=false;
		if(b1){
			aStein.nachRechts();
		}
		
		//////////////////////pruefObSteinInDenBoden()
		Point offset=aStein.getAnfangPunkt();
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point point=new Point(i.next());
			Point p=new Point(point.x+offset.x,point.y+offset.y);
			if(!boden.isEmpty()){
				if(this.boden.contains(p)){
					b2=true;
					break;
				}
			}
			else{break;}
		}
		if(b2){
			aStein.nachLinks();
		}
	}
	
	public void nachUnten(){
		//b1 beschraenkt, dass der Stein nicht durch untere Grenze hindurch
		boolean b1=getAktuellStein().getAnfangPunkt().y
					+getAktuellStein().getHoehe()+1
					<=getHoehe();
		if(b1){
			aStein.nachUnten();
		}
		//b2 beschraenkt, dass der Stein nicht in den Boden reinfaellt.
		//////////////////////pruefObSteinInDenBoden()
		boolean b2=false;
		Point offset=aStein.getAnfangPunkt();
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point point=new Point(i.next());
			Point p=new Point(point.x+offset.x,point.y+offset.y);
			if(!boden.isEmpty()){
				if(this.boden.contains(p)){
					b2=true;
					break;
				}
			}
			else{break;}
		}
		
		
		if(b2){
			aStein.nachOben();
		}
	}
	
	public void doDrehen(){
		//nach Drehen anfangPunkt bleibt unveraendert
		//nur rechte und untere Rand veraendert
		aStein.drehen();
		
		boolean b1=getAktuellStein().getAnfangPunkt().x
					+getAktuellStein().getBreite()
					>getBreite();
		boolean b2=false;
		
		//////////////////////pruefObSteinInDenBoden()
		Point offset=aStein.getAnfangPunkt();
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point point=new Point(i.next());
			Point p=new Point(point.x+offset.x,point.y+offset.y);
			if(!boden.isEmpty()){
				if(this.boden.contains(p)){
					b2=true;
					break;
				}
			}
			else{break;}
		}
		
		if(b1||b2){
			aStein.zurueckDrehen();
		}
	}

	public void setAktuellStein(Stein1 stein) {
		this.aStein=stein;
	}
	
	public Stein1 getAktuellStein(){
		return this.aStein;
	}
	public Stein1 getNaechstStein() {
		return naechstStein;
	}
	public void setNaechstStein(Stein1 naechstStein) {
		this.naechstStein = naechstStein;
	}
	public Point getFaellPunkt() {
		return faellPunkt;
	}
	
	public void setFaellPunkt(){
		int x, y;
		x=breite/2;
		y=-4;
		setMittelPunkt(new Point(x,y));
	}
	
	public void setMittelPunkt(Point mittelPunkt) {
		this.faellPunkt = mittelPunkt;
	}
	
	public int getBreite() {
		return breite;
	}
	public void setBreite(int breite) {
		this.breite = breite/EINHEIT;
	}
	public int getHoehe() {
		return hoehe;
	}
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe/EINHEIT;
	}

	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
	}

	public void draw(Graphics g) {
		getAktuellStein().drawStein(g);
	}

	
	public void pruefObSteinAufBoden() {
		boolean b1=(aStein.getAnfangPunkt().y+aStein.getHoehe()==this.hoehe);
		boolean b2=false;
		Point offset=aStein.getAnfangPunkt();
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point point=new Point(i.next());
			Point p=new Point(point.x+offset.x,point.y+offset.y+1);
			if(!boden.isEmpty()){
				if(this.boden.contains(p)){
					b2=true;
					break;
				}
			}
			else{break;}
		}
		if(boden.isEmpty()){//alle erst mal
			if(b1){
				setSteinAufBoden(true);
			}else{
				setSteinAufBoden(false);
			}
		}else{
			if(b1||b2){
				setSteinAufBoden(true);
			}else{
				setSteinAufBoden(false);
			}
		}
		
	}
	
	public void setSteinAufBoden(boolean b){
		this.steinAufBoden=b;
	}
	public boolean isSteinAufBoden() {
		return steinAufBoden;
	}

	public void neuBoden() {
		for(Iterator<Point> i=aStein.getPunkteXY().iterator();i.hasNext();){
			Point p=new Point(i.next());
			Point point=new Point(p.x+aStein.getAnfangPunkt().x,p.y+aStein.getAnfangPunkt().y);
			this.boden.add(point);
		}
		scanSpielfeld();
		setBodenHoehe();
//		drawBoden(g);
	}
	
	private void scanSpielfeld() {
		List<Integer> reiheZuLoeschen=new ArrayList<Integer>();
		int x,y;
		for(y=0; y<this.hoehe;y++){
			for(x=0; x<this.breite;x++){
				if(boden.contains(new Point(x,y))){
					continue;
				}else{
					break;
				}
			}
			if(x==breite){//nach for-Schleife ist x = breite
					reiheZuLoeschen.add(y);
				}
		}
		if(!reiheZuLoeschen.isEmpty()){
			loeschReihe(reiheZuLoeschen);
		}
	}

	private void loeschReihe(List<Integer> reiheZuLoeschen) {
		
	
		
		List<Point> neuBoden=new ArrayList<Point>();
		
	
		Iterator<Integer> reiheIterator=reiheZuLoeschen.iterator();
		for(;reiheIterator.hasNext();){
			int y=(int)reiheIterator.next();
			for(int x=0;x<breite;x++){
				boden.remove(new Point(x,y));
			}
		}
		
		for(Iterator<Point> i=boden.iterator();i.hasNext();){
			int var=0;
			Point point=new Point(i.next());
		
			reiheIterator=reiheZuLoeschen.iterator();
			for(;reiheIterator.hasNext();){
				int y=reiheIterator.next();
			
				if(point.y<y){
					var++;
		
				}else{
					//do nothing
				}
			}
			int x=point.x;
			int y=point.y+var;
			point=new Point(x,y);
			
			
	
			
			neuBoden.add(point);
			
		}
		this.boden=neuBoden;
//		this.boden=new ArrayList<Point>(neuBoden);
	}

	public void gibStein() {
		aStein=getNaechstStein();
		setNaechstStein(makeStein1());
	}

	public List<Point> getBoden() {
		return this.boden;
	}

	public int getBodenHoehe() {
		return bodenHoehe;
	}

	public void setBodenHoehe() {
		int maxY=this.hoehe-1;
		int minY=this.hoehe-1;
		for(Iterator<Point> i=boden.iterator();i.hasNext();){
			Point p=i.next();
			if(p.y<minY)minY=p.y;
		}
		int bodenHoehe=maxY-minY+1;
		this.bodenHoehe = bodenHoehe;
	}
}
