package gui;

import java.awt.*;

import Fluidity.TimerModel;


public class ColorManager {

	public static Color primaryColor 		= Color.CYAN;
	public static Color secondaryColor  	= Color.BLACK;
	public static Color everyMinute  		= Color.WHITE;
	public static Color lastMinute 			= Color.YELLOW;
	public static Color lastFourtyFiveSec 	= Color.ORANGE;
	public static Color lastFifteenSec 		= Color.RED;
	
	public static Color[] possibleColors =
										  { Color.RED, 		Color.ORANGE, 	Color.YELLOW,
											Color.GREEN, 	Color.BLUE, 	new Color(75,0,130),
											Color.CYAN, 	new Color(255,119,255),
											Color.BLACK, 	Color.WHITE, 	Color.LIGHT_GRAY, Color.PINK};
	/**
	 * Given a Color object, it'll return the index with the array possibleColors[]
	 * that the Color object corresponds to
	 */
	public static int getIndexOfColor(Color c) {
		for (int i = 0; i < possibleColors.length; i++) {
			if (possibleColors[i] == c)
				return i;
		}
		return -1;
	}
	
	/**
	 * Changes color based on combo boxes
	 */
	public static void doComboColor() {
		int index = Dialog.combo2.getSelectedIndex();
		int colorIndex = Dialog.combo3.getSelectedIndex();
		storeColor(index, colorIndex);
		applyNow();
	}
	
	
	public static void storeColor(int index, int colorIndex) {
		switch (index) {
		case 0:
			primaryColor = getColor(colorIndex);
			break;
		case 1:
			secondaryColor = getColor(colorIndex);
			break;
		case 2:
			everyMinute = getColor(colorIndex);
			break;
		case 3:
			lastMinute = getColor(colorIndex);
			break;
		case 4:
			lastFourtyFiveSec = getColor(colorIndex);
			break;
		case 5:
			lastFifteenSec = getColor(colorIndex);
			break;
		}
	}

	private static void applyNow() {
		int time = TimerModel.getSeconds(); // a min and two secs converts into "int time = 62;"
		TimerPanel.applySecondaryColor();
		GUI.applySecondaryColor();
		if (time <= 60) {
			if (time <= 15) {
				applyPrimaryColor(ColorManager.lastFifteenSec);
			} else if (time <= 45) {
				applyPrimaryColor(ColorManager.lastFourtyFiveSec);
			} else if (time <= 60) {
				applyPrimaryColor(ColorManager.lastMinute);
			}
		} else if (time % 60 <= 10 && time % 60 > 0 )
			applyPrimaryColor(ColorManager.everyMinute);
		else {
			applyPrimaryColor(ColorManager.primaryColor);
		}
	}
	
	private static void applyPrimaryColor(Color c){
		TimerPanel.applyPrimaryPanelColor(c);
		MenuBar.applyBarColor(c);
	}
	
	public static Color getColor(int index) {
		return possibleColors[index];
	}
}