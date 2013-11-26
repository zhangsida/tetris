package de.sdzhang.tetrix;

//Steine koennen zu originalen variieren mit Grad
public enum Grad {
	GRAD0(0),
	GRAD90(90),
	GRAD180(180),
	GRAD270(270);
	
	Grad(int g){
		this.wert=g;
	}
	
	private int wert;
	
	public int getWert(){
		return wert;
	}
}
