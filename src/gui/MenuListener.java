package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Fluidity.PresetTimes;
import Fluidity.TimerModel;

public class MenuListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		TimerPanel.setTimerFontSize(44);
		for (PresetTimes pt : PresetTimes.values()) {
			if (e.getActionCommand().equals(pt.getName())) {
				PresetTimes.setCurPresetTimeTrue(pt);
				TimerModel.setTime(pt.getNumMinutes() + ":00");
			}
		}
		if (e.getActionCommand() == "Aff Prep") {
			setPrepAndScroll(PresetTimes.affPrepTracker,
							 PresetTimes.affPrep,
							 PresetTimes.affPrepLeft);
		}
		
		else if (e.getActionCommand() == "Neg Prep") {
			setPrepAndScroll(PresetTimes.negPrepTracker,
							 PresetTimes.negPrep,
							 PresetTimes.negPrepLeft);
		}
		
		else if (e.getActionCommand() == "Alter Prep") {
			String s = (String)JOptionPane.showInputDialog("Input prep time", "04:00");
			PresetTimes.basePrep = s;
			PresetTimes.affPrepTracker = s;
			PresetTimes.negPrepTracker = s;
		}
	}

	/**
	 * Updates the current preset time (for auto-scrolling purposes) to the correct prep time
	 * based on how much prep time is left
	 */
	private void setPrepAndScroll(String timeFound, PresetTimes firstPrepTime, PresetTimes secPrepTime){
		if (timeFound.equals(PresetTimes.basePrep)) {
			PresetTimes.setCurPresetTimeTrue(firstPrepTime);
		}
		else {
			PresetTimes.setCurPresetTimeTrue(secPrepTime);
		}
		TimerModel.setTime(timeFound);
	}
}