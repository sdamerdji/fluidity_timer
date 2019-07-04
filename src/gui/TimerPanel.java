package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Creates and maintains the panel with the time display and buttons.
 * 
 * @author salimdam
 */

public class TimerPanel extends JPanel {
	private static final long serialVersionUID = -6859077750705897628L;
	public static JButton timerDisplay;
	private static JButton firstButton, secButton;
	private static JPanel displayPanel = new JPanel();
	private static JPanel commandPanel = new JPanel();

	public TimerPanel(ActionListener listener) {
		// display Panel
		timerDisplay = new JButton("00:00");
		timerDisplay.setForeground(ColorManager.primaryColor);
		timerDisplay.setBackground(ColorManager.secondaryColor);
		Border line = new LineBorder(ColorManager.secondaryColor);
		timerDisplay.setBorder(line);
		timerDisplay.addActionListener(listener);
		timerDisplay.setOpaque(true);
		setTimerFontSize(44);
		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(timerDisplay, BorderLayout.CENTER);

		// command Panel
		commandPanel.setBackground(ColorManager.secondaryColor);
		commandPanel.setLayout(new FlowLayout());
		firstButton = buttonMaker(firstButton, "Start", listener);
		secButton = buttonMaker(secButton, "Set", listener);
		commandPanel.add(firstButton);
		commandPanel.add(secButton);
		
		// add them togetha
		setLayout(new BorderLayout());
		add(displayPanel, BorderLayout.CENTER);
		add(commandPanel, BorderLayout.SOUTH);
	}

	public static void setDisplay(String display) {
		timerDisplay.setText(display);
	}

	private static void setButtonOne(String command) {
		firstButton.setText(command);
	}

	private static void setButtonTwo(String command) {
		secButton.setText(command);
	}

	public static void setTimerFontSize(int size) {
		timerDisplay.setFont(new Font("", Font.PLAIN, size));
	}

	public static void resetButtons() {
		setButtonOne("Start");
		setButtonTwo("Set");
	}

	public static void setButtonsForCountDown() {
		setButtonOne("Stop");
		setButtonTwo("Reset");
	}

	public static void setButtonsForPrep() {
		setButtonOne("Save");
		setButtonTwo("Reset");
	}

	public static void applyPrimaryPanelColor(Color c) {
		firstButton.setForeground(c);
		firstButton.setBorder(makeBorder(c));
		secButton.setForeground(c);
		secButton.setBorder(makeBorder(c));
		timerDisplay.setForeground(c);
	}
	
	public static void applySecondaryColor() {
		firstButton.setBackground(ColorManager.secondaryColor);
		secButton.setBackground(ColorManager.secondaryColor);
		Border line = new LineBorder(ColorManager.secondaryColor);
		timerDisplay.setBackground(ColorManager.secondaryColor);
		timerDisplay.setBorder(line);
		displayPanel.setBackground(ColorManager.secondaryColor);
		commandPanel.setBackground(ColorManager.secondaryColor);
	}

	private JButton buttonMaker(JButton jb, String name, ActionListener listener) {
		jb = new JButton(name);
		jb.addActionListener(listener);
		jb.setForeground(ColorManager.primaryColor);
		jb.setBorder(makeBorder(ColorManager.primaryColor));
		return jb;
	}

	private static Border makeBorder(Color c) {
		Border line = new LineBorder(c);
		Border margin = new EmptyBorder(1, 15, 1, 15);
		Border compound = new CompoundBorder(line, margin);
		return compound;
	}
}