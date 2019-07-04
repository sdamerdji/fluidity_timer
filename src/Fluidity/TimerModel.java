package Fluidity;

import gui.GUI;
import gui.TimerPanel;

public class TimerModel {
	private static int minutes;
	private static int seconds;
	
	/**
	 * Constructor that initializes the value for minutes/seconds. The values used here refer
	 * to the first possible speech of the current event.
	 */
	public TimerModel() {
		minutes = PresetTimes.getCurSpeech().getNumMinutes();
		seconds = 0;
	}

	public static int getSeconds() {
		return minutes * 60 + seconds;
	}
	
	public void prepareForNextSpeech() {
		if (PresetTimes.anySpeechIsOn()) {
			if (PresetTimes.isItAffPrepTime()) {
				
				setTime(PresetTimes.affPrepTracker);
			}
			else if (PresetTimes.isItNegPrepTime()) {
				
				setTime(PresetTimes.negPrepTracker);
			}
			else {
				minutes = PresetTimes.getCurSpeech().getNumMinutes();
				seconds = 0;
			}
		}
		else {
			setTime(PresetTimes.userSetTime);
		}
	}
	
	public void restartTimer() {
		if (PresetTimes.anySpeechIsOn()) {
			if (PresetTimes.isItPrepTime()) {
				setTime(PresetTimes.basePrep);
			}
			else {
				minutes = PresetTimes.getCurSpeech().getNumMinutes();
				seconds = 0;
			}
		}
		else {
			setTime(PresetTimes.userSetTime);
		}
	}

	public static void setTime(String time) {
		int instance = time.indexOf(":");
		minutes = Integer.parseInt(time.substring(0, instance));
		seconds = Integer.parseInt(time.substring(instance + 1));
		try {
			GUI.ticker.stop();
		} catch (NullPointerException e) {
			;
		}
		TimerPanel.setDisplay(getTime());
	}

	/**
	 * @return Converts the values of int minutes and int seconds into a string
	 */
	public static String getTime() {
		String time = "";
		time += minutes;
		if (seconds < 10)
			time += ":" + "0" + seconds;
		else
			time += ":" + seconds;
		return time;
	}

	public boolean isTimeUp() {
		if (minutes < 1 && seconds < 1)
			return true;
		else
			return false;
	}

	public void timeTick() {
		if (seconds != 0)
			seconds--;
		else {
			minutes--;
			seconds = 59;
		}
	}
}