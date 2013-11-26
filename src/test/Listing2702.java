package test;

/* Listing2702.java */
 
 import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
public class Listing2702 extends Window{
   public static void main(String[] args)
   {
     final Listing2702 wnd = new Listing2702();
     wnd.setLocation(new Point(0,0));
     wnd.setSize(wnd.getToolkit().getScreenSize());
     wnd.setVisible(true);
     wnd.requestFocus();
     wnd.addKeyListener(      
       new KeyAdapter() {
         public void keyPressed(KeyEvent event)
         {
           wnd.setVisible(false);
           wnd.dispose();
           System.exit(0);
         }
       }
     );
   }
 
   public Listing2702()
   {
     super(new Frame());
     setBackground(Color.black);
   }
 
   public void paint(Graphics g)
   {
     g.setColor(Color.red);
     g.drawString(
       "Bildschirmgröße ist "+
       getSize().width+"*"+getSize().height,
       10,
       20
     );
     g.drawString("Bitte eine Taste drücken",10,40);
   }
 }
