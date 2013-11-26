package test;

/* Listing2703.java */

import java.awt.Frame;

public class Listing2703 {
    public static void main(String[] args) {
        Frame frame = new Frame("Anzeigezustand");
        frame.setSize(300, 200);
        frame.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // nichts
        }
        frame.setState(Frame.ICONIFIED);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // nichts
        }
        frame.setState(Frame.NORMAL);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // nichts
        }
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}