package Fluidity;

import gui.ColorManager;
import gui.GUI;

import  java.util.prefs.*;
import java.awt.Color;

public class UserSettings {

	private Preferences prefs;
	private final String PRIMARY_COLOR = "primary";
	private final String SECONDARY_COLOR = "secondary";
	private final String EVERY_MIN_COLOR = "every minute";
	private final String LAST_MIN_COLOR = "last minute"; 
	private final String LAST_45_SEC = "last 45 seconds";
	private final String LAST_15_SEC = "last 15 seconds";
	private final String X_LOCATION = "x loc";
	private final String Y_LOCATION = "y loc";
	
	private int primaryColor = ColorManager.getIndexOfColor(Color.CYAN);
	private int secondaryColor = ColorManager.getIndexOfColor(Color.BLACK);
	private int everyMinute = ColorManager.getIndexOfColor(Color.WHITE);
	private int lastMinute = ColorManager.getIndexOfColor(Color.YELLOW);
	private int lastFourtyFiveSec = ColorManager.getIndexOfColor(Color.ORANGE);
	private int lastFifteenSec = ColorManager.getIndexOfColor(Color.RED);
	
	public void storePreferences() {
		prefs.putInt(PRIMARY_COLOR, ColorManager.getIndexOfColor(ColorManager.primaryColor));
		prefs.putInt(SECONDARY_COLOR, ColorManager.getIndexOfColor(ColorManager.secondaryColor));
		prefs.putInt(EVERY_MIN_COLOR, ColorManager.getIndexOfColor(ColorManager.everyMinute));
		prefs.putInt(LAST_MIN_COLOR, ColorManager.getIndexOfColor(ColorManager.lastMinute));
		prefs.putInt(LAST_45_SEC, ColorManager.getIndexOfColor(ColorManager.lastFourtyFiveSec));
		prefs.putInt(LAST_15_SEC, ColorManager.getIndexOfColor(ColorManager.lastFifteenSec));
		prefs.putInt(X_LOCATION, GUI.userChosenX);
		prefs.putInt(Y_LOCATION, GUI.userChosenY);
	}
	
	public void getStoredPreferences() {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		ColorManager.storeColor(0, prefs.getInt(PRIMARY_COLOR, primaryColor));
		ColorManager.storeColor(1, prefs.getInt(SECONDARY_COLOR, secondaryColor));
		ColorManager.storeColor(2, prefs.getInt(EVERY_MIN_COLOR, everyMinute));
		ColorManager.storeColor(3, prefs.getInt(LAST_MIN_COLOR, lastMinute));
		ColorManager.storeColor(4, prefs.getInt(LAST_45_SEC, lastFourtyFiveSec));
		ColorManager.storeColor(5, prefs.getInt(LAST_15_SEC, lastFifteenSec));
		GUI.userChosenX = prefs.getInt(X_LOCATION, GUI.userChosenX);
		GUI.userChosenY = prefs.getInt(X_LOCATION, GUI.userChosenX);
	}
} 
