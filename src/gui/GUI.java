package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Fluidity.PresetTimes;
import Fluidity.Sound;
import Fluidity.TimerModel;
import Fluidity.UserSettings;

/**
 * This class' constructor is the only thing called in the main method in
 * FluidTimer.java Naturally, this class calls on most of the other classes and
 * is also in charge of handling events from the buttons clicked on the timer
 * panel as well as events triggered by the internal timer from java swing
 * (not the gui, which end-users might call "the timer").
 * 
 * @author salimdam
 */
public class GUI extends JDialog {
	public static Timer ticker;
	public static int userChosenX = 0, userChosenY = 0;
	
	private static final long serialVersionUID = 629873007942313078L;
	private static final int  largeX = 155, largeY = 140,
							  smallX = 155, smallY = 130;
	private static TimerModel timerModel = new TimerModel();
	private static JPanel panel = new JPanel();
	private static TimerPanel timerPanel;
	private static JMenuBar jm;

	/**
	 * This constructor calls everything needed to create the entire gui. It's
	 * the hub. It takes a second to run everything.
	 */
	public GUI() {
		final UserSettings us = new UserSettings();
		us.getStoredPreferences(); // Getting user preferences

		setAlwaysOnTop(true); // timer will remain on top of any other window on
								// screen
		setLocation(userChosenX, userChosenY); // either goes to (0,0) or the
												// last stored user preference
		timerPanel = new TimerPanel(new TimerListener());
		TimerPanel.setDisplay(TimerModel.getTime());
		add(timerPanel);
		jm = MenuBar.getTimerBar();
		setJMenuBar(jm);
		setTitle("Fluidity");
		setResizable(false);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				us.storePreferences();
				Sound.stop();
				System.exit(0);
			}
		});
		setSize(largeX, largeY);
		setVisible(true);
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == TimerPanel.timerDisplay) {
				Dialog.display();
			} else if (e.getActionCommand() == "Start") {
				start();
			} else if (e.getActionCommand() == "Stop") {
				stop(); // Hammer Time!
			} else if (e.getActionCommand() == "Set") {
				set();
			} else if (e.getActionCommand() == "Reset") {
				reset();
			} else if (e.getActionCommand() == "Save") {
				PresetTimes.storePrepTime();
				stop();
				PresetTimes.switchToNextPresetTime();
				timerModel.prepareForNextSpeech();
				TimerPanel.setDisplay(TimerModel.getTime());
			} else if (e.getSource() == ticker) {
				if (timerModel.isTimeUp()) {
					PresetTimes.storePrepTime();
					Sound.play();
					setLocation(userChosenX = getLocation().x,
							userChosenY = getLocation().y);
					setJMenuBar(jm);
					setSize(largeX, largeY);
					if (PresetTimes.anySpeechIsOn())
						PresetTimes.switchToNextPresetTime();
					if (!PresetTimes.isItPrepTime()) {
						setLocationRelativeTo(null);
					}
					timerModel.prepareForNextSpeech();
					ticker.stop();
					
					TimerPanel.resetButtons();
				}
				else {
					timerModel.timeTick();
					switchColor();
					TimerPanel.setDisplay(TimerModel.getTime());
				}
			}
		}

		private void start() {
			if (PresetTimes.isItPrepTime()) {
				TimerPanel.setButtonsForPrep();
			} else {
				TimerPanel.setButtonsForCountDown();
			}
			setLocation(userChosenX, userChosenY);
			setJMenuBar(null);
			setSize(smallX, smallY);
			TimerPanel.setDisplay(TimerModel.getTime());
			ticker = new Timer(1000, this);
			ticker.start();
			TimerPanel.setTimerFontSize(44);
		}

		private void stop() {
			setJMenuBar(jm);
			setSize(largeX, largeY);
			TimerPanel.resetButtons();
			ticker.stop();
			userChosenX = getLocation().x;
			userChosenY = getLocation().y;
		}

		/* You are not meant to understand this */
		private void reset() {
			String name = PresetTimes.getCurSpeech().getName();
			int nameLength = name.length();
			TimerPanel.setDisplay(name);
			if (nameLength >= 4) {
				TimerPanel.setTimerFontSize(35);
			}
			stop();
			try {
				ticker.stop();
			} catch (NullPointerException e) {
				;
			}
			timerModel.restartTimer();
		}

		private void set() {
			try {
				String s = (String) JOptionPane
						.showInputDialog("Input the precise time you want",
								TimerModel.getTime());
				PresetTimes.userSetTime = s;
				PresetTimes.setCurPresetTimeTrue(null);
				TimerModel.setTime(s);
				TimerPanel.setDisplay(TimerModel.getTime());
				ticker.stop();
			} catch (NullPointerException e) {
				;
			}
		}
	}

	/**
	 * Note this method only applies a different color right as a time interval
	 * occurs. If the timer is set precisely at 14 seconds, it'll just stay the
	 * primary color for the next 14 seconds. If the timer is set for 16
	 * seconds, it will hit the interval and switch to red for the last 15
	 * seconds. This is to conserve memory and so alerts don't occur, for
	 * instance, during prep time if there was only 45 seconds left to start
	 * with. Who needs an alert during their already shitty prep time to tell
	 * them they suck at investing prep time properly?
	 */
	public static void switchColor() {
		int time = TimerModel.getSeconds();
		if (time <= 60) {
			if (time == 60) {
				applyColor(ColorManager.lastMinute);
			} else if (time == 45) {
				applyColor(ColorManager.lastFourtyFiveSec);
			} else if (time == 15) {
				applyColor(ColorManager.lastFifteenSec);
			}
		}
		else if (time % 60 <= 10)
			applyColor(ColorManager.everyMinute);
		else if (time % 60 == 59)
			applyColor(ColorManager.primaryColor);
	}

	/**
	 * Helper method only called by switchColor()
	 */
	private static void applyColor(Color c) {
		TimerPanel.applyPrimaryPanelColor(c);
		MenuBar.applyBarColor(c);
	}

	/**
	 * Helper method only called by applyNow() in ColorManager. Color Manager
	 * changes color based on user preferences and wants to apply them
	 * immediately, which doesn't always happen for switchColors(), which
	 * applies changes only when certain intervals are hit as the timer is going
	 * down.
	 */
	public static void applySecondaryColor() {
		panel.setBackground(ColorManager.secondaryColor);
	}
}