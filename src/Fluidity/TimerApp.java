package Fluidity;

import gui.GUI;

import java.awt.EventQueue;

public class TimerApp {

	private static GUI g;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				g = new GUI();
			}
		});
	}

	public static void setLoc(int xLoc, int yLoc) {
		g.setLocation(xLoc, yLoc);
	}
}