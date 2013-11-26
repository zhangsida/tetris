package de.sdzhang.tetrix;


import java.awt.Point;
import java.util.ArrayList;
import de.sdzhang.tetrix.TPunkte;

public enum SteinTyp1 {
	/*
	 * nummerierte Punkte in Tetrix 
	 *  0-- 1-- 2-- 3
	 * 	4-- 5-- 6-- 7
	 *  8-- 9--10--11
	 * 12--13--14--15
	 * */
	Quadrat_Stein(TPunkte.p[0],TPunkte.p[1],TPunkte.p[4],TPunkte.p[5]),
	L_Stein(TPunkte.p[0],TPunkte.p[4],TPunkte.p[8],TPunkte.p[9]),
	L_Stein90(TPunkte.p[0],TPunkte.p[1],TPunkte.p[2],TPunkte.p[4]),
	L_Stein180(TPunkte.p[0],TPunkte.p[1],TPunkte.p[5],TPunkte.p[9]),
	L_Stein270(TPunkte.p[2],TPunkte.p[4],TPunkte.p[5],TPunkte.p[6]),
	WL_Stein(TPunkte.p[1],TPunkte.p[5],TPunkte.p[8],TPunkte.p[9]),
	WL_Stein90(TPunkte.p[0],TPunkte.p[4],TPunkte.p[5],TPunkte.p[6]),
	WL_Stein180(TPunkte.p[0],TPunkte.p[1],TPunkte.p[4],TPunkte.p[8]),
	WL_Stein270(TPunkte.p[0],TPunkte.p[1],TPunkte.p[2],TPunkte.p[6]),
	T_Stein(TPunkte.p[0],TPunkte.p[1],TPunkte.p[2],TPunkte.p[5]),
	T_Stein90(TPunkte.p[1],TPunkte.p[4],TPunkte.p[5],TPunkte.p[9]),
	T_Stein180(TPunkte.p[1],TPunkte.p[4],TPunkte.p[5],TPunkte.p[6]),
	T_Stein270(TPunkte.p[0],TPunkte.p[4],TPunkte.p[5],TPunkte.p[8]),
	I_Stein(TPunkte.p[0],TPunkte.p[4],TPunkte.p[8],TPunkte.p[12]),
	I_Stein90(TPunkte.p[0],TPunkte.p[1],TPunkte.p[2],TPunkte.p[3]),
	S_Stein(TPunkte.p[1],TPunkte.p[2],TPunkte.p[4],TPunkte.p[5]),
	S_Stein90(TPunkte.p[0],TPunkte.p[4],TPunkte.p[5],TPunkte.p[9]),
	WS_Stein(TPunkte.p[0],TPunkte.p[1],TPunkte.p[5],TPunkte.p[6]),
	WS_Stein90(TPunkte.p[1],TPunkte.p[4],TPunkte.p[5],TPunkte.p[8]),;
	
	SteinTyp1(Point a, Point b, Point c, Point d){
		punkte.add(a);
		punkte.add(b);
		punkte.add(c);
		punkte.add(d);
	}
	
	private ArrayList<Point> punkte=new ArrayList<Point>();//speichert die Index von TPunkte
	
	public ArrayList<Point> getPunkte() {
		return punkte;
	}

}
