package de.sdzhang.tetrix;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.KeyEvent;


//enthaelt auch das Spielfeld
public class TetrixSpiel implements TetrixWindowListener{
	
	private boolean newGame=true;
	private boolean gameOver=false;
	
	private TetrixWindow myFrame;
	private Spielfeld spielfeld;
	
	private Level level=Level.LEVEL0;
	private int punkte;
//	private Roboter myRoboter;
	
	private Insets windowGrenze;
	private int windowBreite;
	private int windowHoehe;
	
	
	public TetrixSpiel(){
//		Ausgabe.out("TrerixSpiel");
	}
	
	public void initSpiel(){
//		Ausgabe.out("initSpiel");
		this.setWindowGrenze(myFrame.getInsets());
		this.setWindowBreite(myFrame.getBreite());
		this.setWindowHoehe(myFrame.getHoehe());
	}
		
	public void makeSpielfeld() {
		initSpiel();
		Point offset=new Point(windowGrenze.left,windowGrenze.top);
		int breite=(windowBreite-windowGrenze.left-windowGrenze.right);
		int hoehe=(windowHoehe-windowGrenze.top-windowGrenze.bottom);
		this.spielfeld=new Spielfeld(offset,breite,hoehe);
		
	}

	public void nachOben(){
		if(spielfeld.getAktuellStein().getAnfangPunkt().y-1>=0)
			spielfeld.nachOben();
	}
	
	public void nachLinks(){
			spielfeld.nachLinks();
	}
	
	public void nachRechts(){
			spielfeld.nachRechts();
	}
	
	public void nachUnten(){
			spielfeld.nachUnten();
	}
	
	public void doDrehen(){
		
		spielfeld.doDrehen();
	}
	
	
	public boolean isNewGame() {
		return newGame;
	}
	public void setNewGame(boolean newGame) {
		this.newGame = newGame;
	}
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
 	
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public int getPunkte() {
		return punkte;
	}
	public void setPunkte(int punkte) {
		this.punkte = punkte;
	}


	public void setWindowGrenze(Insets insets) {
		this.windowGrenze=insets;
		
	}

	public void setWindowBreite(int breite) {
		this.windowBreite=breite;
		
	}

	public void setWindowHoehe(int hoehe) {
		this.windowHoehe=hoehe;		
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public void setSpielfeld(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}
	
	public void spielStart(){
		while(!isGameOver()){
			naechstZug();
		}
	
		int i=myFrame.getGraphics().getFont().getSize();
		this.myFrame.getGraphics().drawString(i+" Game Over!", 10, 50);
		
		
	}

	private void naechstZug() {
		
		spielfeld.pruefObSteinAufBoden();
		
		if(spielfeld.isSteinAufBoden()){
			pruefGameOver();
			if(!isGameOver()){
				spielfeld.neuBoden();
				repaint();
				spielfeld.gibStein();
			}else{
				//do nothing
			}
		}else{
			steinFaellt();
		}
	}

	private void pruefGameOver() {
		if(spielfeld.getBodenHoehe()>=spielfeld.getHoehe())//19
			setGameOver(true);
	}

	public void steinFaellt() {		
			repaint();
			spielfeld.getAktuellStein().makeOffset(0, 1);
			try{Thread.sleep(500);}catch(InterruptedException e){}
			repaint();//keinen Einfluss auf keyPressed
	}
	
	public void keyAction() {
		switch(myFrame.keyEvent.getKeyCode()){
		case KeyEvent.VK_SPACE:
			doDrehen();
			break;
		case KeyEvent.VK_LEFT:
			nachLinks();//getAktuellStein().makeOffset(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
			nachRechts();// getAktuellStein().makeOffset(1, 0);
			break;
		case KeyEvent.VK_DOWN:
			nachUnten();//getAktuellStein().makeOffset(0, 1);
			break;
		case KeyEvent.VK_UP:
			nachOben();//getAktuellStein().drehen();
			break;		
		case KeyEvent.VK_N:
			spielfeld.gibStein();
			break;
		}	
		spielfeld.pruefObSteinAufBoden();
		repaint();
	}
	private void repaint() {
//		Ausgabe.out("repaint");
		myFrame.repaint();//clear bild
//		spielfeld.getAktuellStein().drawStein(myFrame.getGraphics());
	}
	
	@Override
	public void setFrame(Frame tetrixWindow) {

		this.myFrame=(TetrixWindow)tetrixWindow;
	}
	
}
