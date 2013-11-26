package de.sdzhang.tetrix;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TetrixWindow extends Frame implements KeyListener{
	
	private int breite=16*20+8;
	private int hoehe=16*40+34;
	
	public KeyEvent keyEvent;
	
	private TetrixSpiel spiel;

	
	public List<TetrixWindowListener> listener=new ArrayList<TetrixWindowListener>();
	
	public TetrixWindow(){
		assignWindow();
		assignColors();
		this.spiel=new TetrixSpiel();
		listener.add(spiel);
		addKeyListener(this);
		setVisible(true);
		action();
		spiel.makeSpielfeld();
		spiel.spielStart();
	}
	public void action(){
		for(TetrixWindowListener t:listener){
			t.setFrame(this);
		}
	}
	
	private void assignWindow(){
		//ermittelt den linken oberen Punkt des Fensters
		int x=getToolkit().getScreenSize().width/2-breite/2;
		int y=getToolkit().getScreenSize().height/2-hoehe/2;
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		setTitle("Tetrix");
		setBounds(x,y,breite,hoehe);

	}
	
	private void assignColors(){
		setBackground(Color.BLACK);
		setForeground(Color.RED);
	}

	public void paint(Graphics g){
		spiel.getSpielfeld().getAktuellStein().drawStein(g);
		spiel.getSpielfeld().drawBoden(g);
	}
	
	//test programm
	public static void main(String args[]){
		new TetrixWindow();
		
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

	@Override
	public void keyPressed(KeyEvent e) {

		this.keyEvent=e;
		this.spiel.keyAction();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
