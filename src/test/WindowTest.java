package test;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author zsd
 * 
 */

public class WindowTest extends Frame {
	private static final long serialVersionUID = 1L;

	// Fenster Anfangpunkte
	private int xp;
	private int yp;

	// Fenster Breite und Hoehe
	private int breite;
	private int hoehe;

	// Paint Anfangpunkte
	private int ax;
	private int ay;
	private int offset = 16;// pixel

	WindowTest() {

		assignWindow();

		setLocation(xp, yp);

		setVisible(true);
	}

	private void assignWindow() {

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				event.getWindow().setVisible(false);
				event.getWindow().dispose();
				System.exit(0);
			}
		});

		setTitle("Tetrix");
		setBackground(Color.black);
		setForeground(Color.red);
		setBreite(500);
		setHoehe(500);
		setSize(getBreite(), getHoehe());
		setXp(this.getToolkit().getScreenSize().width / 2 - getBreite() / 2);
		setYp(getToolkit().getScreenSize().height / 2 - getHoehe() / 2);

	}

	// 16 pixel quadratStein mit schwarzem Rahmen und roter Fuellung
	private void makeStein(Graphics g, int x, int y) {
		g.setColor(Color.red);
		g.fillRoundRect(x, y, 16, 16, 6, 6);
		g.setColor(Color.black);
		g.drawRoundRect(x, y, 16, 16, 6, 6);
	}

	private void loeschStein(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		g.fillRoundRect(x, y, 16, 16, 6, 6);
	}

	// addiert den letzten PaintAnfangpunkt (ax, ay)
	// mit Vielfach von {$offset}
	public void makeOffset(int xOffset, int yOffset) {
		this.setAx(getAx() + xOffset * getOffset());
		this.setAy(getAy() + yOffset * getOffset());
	}

	public void paint(Graphics g) {
		// mypaint(g);

		// stellen den Anfangpunkt von paint
		setAx(getInsets().left + 4);
		setAy(getInsets().top + 4);

		// I_Stein senkrecht///////////////////////////////
		for (int i = 0; i < 4; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		// ax+=2*offset;
		makeOffset(2, 0);
		// I_Stein waagerecht/////////////////////////////////
		for (int i = 0; i < 4; i++) {
			makeStein(g, ax + i * offset, ay);
		}

		// ax+=5*offset;
		makeOffset(5, 0);
		// Quadrat_Stein/////////////////////////////////////
		makeStein(g, ax, ay);
		makeStein(g, ax + offset, ay);
		makeStein(g, ax, ay + offset);
		makeStein(g, ax + offset, ay + offset);

		// ax+=3*offset;
		makeOffset(3, 0);
		// L_Stein wie L//////////////////////////////////////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}
		makeStein(g, ax + offset, ay + 2 * offset);

		// ax+=3*offset;
		makeOffset(3, 0);
		// L_Stein nach Uhrrichtung 90 Grad Drehen//////////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		makeStein(g, ax, ay + offset);

		// ax+=4*offset;
		makeOffset(4, 0);
		// L_Stein nach Uhrrichtung 180 Grad Drehen///////////////
		makeStein(g, ax, ay);
		// ax+=offset;
		makeOffset(1, 0);
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		// ax+=2*offset;
		// ay+=offset;//um das Stein Kommplett darzustehen
		makeOffset(2, 1);
		// L_Stein nach Uhrrichtung 270 Grad Drehen///////////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		// ax+=2*offset;
		// ay-=offset;
		makeOffset(2, -1);
		makeStein(g, ax, ay);

		// ax+=2*offset;
		makeOffset(2, 0);
		// T_Stein wie T////////////////////////////////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		// ax+=offset;
		// ay+=offset;
		makeOffset(1, 1);
		makeStein(g, ax, ay);

		makeOffset(3, 0);
		// T_Stein nach Uhrrichtung 90 Grad drehen
		makeStein(g, ax, ay);
		makeOffset(1, -1);
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		// Zuruecksetzen den Anfangpunkt von paint
		setAx(getInsets().left + 4);
		setAy(getInsets().top + 4);

		makeOffset(0, 6);
		// T_Stein nach Uhrrichtung 180 Grad drehen///////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		makeOffset(1, -1);
		makeStein(g, ax, ay);

		makeOffset(3, 0);
		// T_Stein nach Uhrrichtung 270 Grad drehen////////////
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}
		makeOffset(1, 1);
		makeStein(g, ax, ay);

		makeOffset(2, 0);
		// S_Stein wie S/////////////////////////////////////
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		makeOffset(1, -1);
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax + i * offset, ay);
		}

		makeOffset(3, 0);
		// S_Stein nach Uhrrichtung 90 Grad drehen////////////
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax, ay + i * offset);
		}
		makeOffset(1, 1);
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		makeOffset(2, -1);
		// Wiederspiegelung von S_Stein
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		makeOffset(1, 1);
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax + i * offset, ay);
		}

		makeOffset(3, 0);
		// 90 Grad von W_S_Stein
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax, ay + i * offset);
		}
		makeOffset(1, -1);
		for (int i = 0; i < 2; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		makeOffset(2, 0);
		// Wiederspiegelung von L_Stein
		makeOffset(0, 2);
		makeStein(g, ax, ay);
		makeOffset(1, -2);
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}

		makeOffset(2, 0);
		// 90 Grad von W_L_Stein
		makeStein(g, ax, ay);
		makeOffset(0, 1);
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}

		makeOffset(4, -1);
		// 180 Grad von W_L_Stein
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax, ay + i * offset);
		}
		makeOffset(1, 0);
		makeStein(g, ax, ay);

		// Zuruecksetzen den Anfangpunkt von paint
		setAx(getInsets().left + 4);
		setAy(getInsets().top + 4);

		makeOffset(0, 9);
		// 270 Grad von W_L_Stein
		for (int i = 0; i < 3; i++) {
			makeStein(g, ax + i * offset, ay);
		}
		makeOffset(2, 1);
		makeStein(g, ax, ay);

		while (true) {// bottom nicht erreicht
			try {
				// this.repaint();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			// loeschen
			for (int i = 0; i < 3; i++) {
				loeschStein(g, ax + i * offset, ay, getBackground());
			}
			makeOffset(2, 1);
			loeschStein(g, ax, ay, getBackground());
			makeOffset(-2, -1);

			// anfangpunkt nach einheit nach unten
			makeOffset(0, 1);

			// drawStein
			for (int i = 0; i < 3; i++) {
				makeStein(g, ax + i * offset, ay);
			}
			makeOffset(2, 1);
			makeStein(g, ax, ay);
			makeOffset(-2, -1);// zurueck zu PaintanfangPunkt
		}

	}

	public static void main(String args[]) {
		new WindowTest();
	}

	// Test draw Funktionen
	@SuppressWarnings("unused")
	private void mypaint(Graphics g) {
		// assenpunkt ap
		// innerenpunkt ip
		Point ap1 = new Point(getInsets().left, getInsets().top);
		Point ip1 = new Point(ap1.x + 50, ap1.y + 50);

		Point ap2 = new Point(ap1.x + 500 - 2 * getInsets().left, ap1.y);
		Point ip2 = new Point(ap2.x - 50, ap2.y + 50);

		Point ap3 = new Point(ap1.x, ap1.y + 500 - getInsets().bottom - getInsets().top);
		Point ip3 = new Point(ap3.x + 50, ap3.y - 50);

		Point ap4 = new Point(ap2.x, ap3.y);
		Point ip4 = new Point(ap4.x - 50, ap4.y - 50);

		g.drawLine(ap1.x, ap1.y, ip1.x, ip1.y);
		g.drawLine(ap2.x, ap2.y, ip2.x, ip2.y);
		g.drawLine(ap3.x, ap3.y, ip3.x, ip3.y);
		g.drawLine(ap4.x, ap4.y, ip4.x, ip4.y);

		g.drawPolygon(new int[] { ip1.x, ip2.x, ip4.x, ip3.x }, new int[] { ip1.y, ip2.y, ip4.y, ip3.y }, 4);

		// g.fillRoundRect(ap1.x+250,ap1.y+250 , 16, 16,6,6);

	}

	public void setXp(int x) {
		xp = x;
	}

	public void setYp(int y) {
		yp = y;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	public int getAx() {
		return ax;
	}

	public void setAx(int x) {
		this.ax = x;
	}

	public int getAy() {
		return ay;
	}

	public void setAy(int y) {
		this.ay = y;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getXp() {
		return xp;
	}

	public int getYp() {
		return yp;
	}
}
