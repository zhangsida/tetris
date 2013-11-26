package de.sdzhang.tetrix;

import java.util.ArrayList;
import java.util.List;

public enum SteinTypKoordinaten {
	/*
	 * nummerierte Punkte in Tetrix 
	 *  0-- 1-- 2-- 3
	 * 	4-- 5-- 6-- 7
	 *  8-- 9--10--11
	 * 12--13--14--15
	 * */
	
	Quadrat_Stein(0,1,4,5),
	L_Stein(0,4,8,9),
	L_Stein90(0,1,2,4),
	L_Stein180(0,1,5,9),
	L_Stein270(2,4,5,6),
	WL_Stein(1,5,8,9),
	WL_Stein90(0,4,5,6),
	WL_Stein180(0,1,4,8),
	WL_Stein270(0,1,2,6),
	T_Stein(0,1,2,5),
	T_Stein90(1,4,5,9),
	T_Stein180(1,4,5,6),
	T_Stein270(0,4,5,8),
	I_Stein(0,4,8,12),
	I_Stein90(0,1,2,3),
	S_Stein(1,2,4,5),
	S_Stein90(0,4,5,9),
	WS_Stein(0,1,5,6),
	WS_Stein90(1,4,5,8);
	
	SteinTypKoordinaten(int a, int b, int c, int d){
		punkte.add(a);
		punkte.add(b);
		punkte.add(c);
		punkte.add(d);
	}
	private List<Integer> punkte = new ArrayList<Integer>();
}
