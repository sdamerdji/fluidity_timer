package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Fluidity.TimerApp;

public class ComboListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Move")) {
			int x = 0;
			int y = 0;
			try {
				x = Integer.valueOf(Dialog.xLoc.getText());
				y = Integer.valueOf(Dialog.yLoc.getText());
			} catch(NumberFormatException e2) {
				x = Integer.valueOf(Dialog.xLoc.getText().substring(1));
				y = Integer.valueOf(Dialog.yLoc.getText().substring(1));
			}
			GUI.userChosenX = x;
			GUI.userChosenY = y;
			TimerApp.setLoc(x, y);
		}
		else if (e.getActionCommand().equals("Do")){
			ColorManager.doComboColor();
		}
		else {
			Prelims.updateBracket();
		}
	}
}