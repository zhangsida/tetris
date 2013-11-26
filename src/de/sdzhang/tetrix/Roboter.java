package de.sdzhang.tetrix;

public class Roboter {
	
	
	private int Fensterbeite;
	private int Fensterhoehe;
	private Level level;
	private int punkte;
	private boolean gameOver;
	private int einheit=16;//pixel
//	private SteinTyp aktuellerStein;
//	private SteinTyp naechsterStein;
	
	//unter Windows
	public Roboter(){
		setFensterbreite(40);
		setFensterhoehe(20);
		setLevel(Level.LEVEL0);
		setPunkte(0);
		setGameOver(false);	
	}
	
	public int getFensterbreite() {
		return Fensterbeite;
	}
	public void setFensterbreite(int breite) {
		this.Fensterbeite = breite*einheit+8;//+fensterrand
	}
	public int getFensterhoehe() {
		return Fensterhoehe;
	}
	public void setFensterhoehe(int hoehe) {
		this.Fensterhoehe = hoehe*einheit+30+4;//+fensterRand
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
	public boolean isGameOver() {
		return gameOver;
	}
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getEinheit() {
		return einheit;
	}

	public void setEinheit(int einheit) {
		this.einheit = einheit;
	}
	
}
