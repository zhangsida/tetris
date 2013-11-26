package de.sdzhang.tetrix;

import java.awt.Point;

/*
 * nummerierte Punkte in Tetrix
 *  
 *  0-- 1-- 2-- 3
 * 	4-- 5-- 6-- 7
 *  8-- 9--10--11
 * 12--13--14--15
 * 
 * */
//relative Koordinaten von Punkte
public class TPunkte{
	public static Point[] p=
	{	
		p(0,0),p(1,0),p(2,0),p(3,0),
		p(0,1),p(1,1),p(2,1),p(3,1),
		p(0,2),p(1,2),p(2,2),p(3,2),
		p(0,3),p(1,3),p(2,3),p(3,3)
	};
	
	private static Point  p(int x, int y){
		return new Point(x,y);
	}
}
