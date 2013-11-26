package test;
 /* Listing2704.java */
 
 import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
 
 public class Listing2704
 extends Frame
 {
   public static void main(String[] args)
   {
     Listing2704 wnd = new Listing2704();
     wnd.setSize(300,200);
     wnd.setLocation(50,50);
     wnd.setVisible(true);
   }
 
   public Listing2704()
   {
     super("");
     assignTitle();
     assignIcon();
     assignCursor();
     assignColors();
     assignFont();
//     addWindowListener(new WindowAdapter(true));
   }
 
   private void assignTitle()
   {
     setTitle("Veränderte Fensterelemente");
   }
 
   private void assignIcon()
   {
     Image img = getToolkit().getImage("testicon.gif");
     MediaTracker mt = new MediaTracker(this);
 
     mt.addImage(img, 0);
     try {
       //Warten, bis das Image vollständig geladen ist,
       mt.waitForAll();
     } catch (InterruptedException e) {
       //nothing
     }
     setIconImage(img);
   }
 
   private void assignCursor()
   {
    setCursor(new Cursor(Cursor.WAIT_CURSOR));
   }
 
   private void assignColors()
   {
     setForeground(Color.white);
     setBackground(Color.black);
   }
 
   private void assignFont()
  {
     setFont(new Font("Serif", Font.PLAIN, 28));
   }
 
   public void paint(Graphics g)
   {
     g.drawString("Test in Vordergrundfarbe",10,70);
   }
 }