package de.sdzhang.tetrix;

public enum Level {
	LEVEL0(1000),
	LEVEL1(900),
	LEVEL2(800),
	LEVEL3(700),
	LEVEL4(600),
	LEVEL5(500),
	LEVEL6(400),
	LEVEL7(300),
	LEVEL8(200);
	private int geschwindigkeit;
	Level(int g){
		geschwindigkeit=g;
	}
	public int getGeschwindigkeit() {
		return geschwindigkeit;
	}
	public void setGeschwindigkeit(int geschwindigkeit) {
		this.geschwindigkeit = geschwindigkeit;
	}
}
