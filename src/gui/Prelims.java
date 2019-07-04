package gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Prelims {
	public static JLabel[] listOfLabels = new JLabel[9];
	public static String spacing = "         ";
	private static final double X = 0.015625; // n * 1/64
	private static final double Y = 0.015625; // n * 1/128
	private static final double Z = 0.00390625; // n * 1/256
	
	private static double[] fiveRoundExpected =
		{2*X, 10*X, 20*X, 20*X, 10*X, 2*X}; 
	private static double[] sixRoundExpected =
		{2*Y, 12*Y, 30*Y, 40*Y, 30*Y, 12*Y, 2*Y};
	private static double[] sevenRoundExpected =
		{2*Z, 14*Z, 42*Z, 70*Z, 70*Z, 42*Z, 14*Z, 2*Z};
	private static double[] eightRoundExpected =
		{1*Z, 8*Z,  28*Z, 56*Z, 70*Z, 56*Z, 28*Z, 8*Z, 1*Z};	

	public static JPanel makePrelimPanel() {
		JPanel botHalf = new JPanel();
		for (int i = 0; i < Prelims.listOfLabels.length; i++) {
			Prelims.listOfLabels[i] = new JLabel(Prelims.spacing
					+ "Lost " + i + " - ");
			botHalf.add(Prelims.listOfLabels[i]);
		}
		Prelims.updateBracket();
		botHalf.setLayout(new GridLayout(9, 1));
		return botHalf;
	}

	public static String getPrefix(int numRounds, int numLoss) {
		return spacing + (numRounds - numLoss) + "-" + numLoss + ":   ";
	}

	public static void updateBracket() {
		int numRounds = getNumRounds();
		int[] arr = new int[numRounds + 1];
		arr = calculateBracket(numRounds, getNumDebaters());
		for (int i = 0; i < listOfLabels.length; i++) {
			if (numRounds < i) {
				listOfLabels[i].setText("");
			} else {
				listOfLabels[i].setText(getPrefix(numRounds, i) + +arr[i]);
			}
		}
	}

	private static int getNumRounds() {
		return Dialog.combo.getSelectedIndex() + 5;
	}

	private static int getNumDebaters() {
		return Integer.valueOf(Dialog.numDebaters.getText());
	}

	private static int[] calculateBracket(int numRounds, int numDebaters) {
		int[] arr = new int[numRounds + 1];
		if (numRounds == 5)
			return bracketHelper(fiveRoundExpected, numDebaters, arr);
		else if (numRounds == 6)
			return bracketHelper(sixRoundExpected, numDebaters, arr);
		else if (numRounds == 7)
			return bracketHelper(sevenRoundExpected, numDebaters, arr);
		else
			return bracketHelper(eightRoundExpected, numDebaters, arr);
	}

	private static int[] bracketHelper(double[] expected, int numDebaters,
			int[] arr) {
		for (int numWins = 0; numWins < arr.length; numWins++)
			arr[numWins] = (int) (expected[numWins] * numDebaters);

		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		while (total < numDebaters - 1) {
			if (total < numDebaters - 3) {
				arr[1] += 1;
				arr[arr.length - 2] += 1; // yeah i know this ain't precise but
										 // it's better than not even 
			} 						    // trying to correct for rounding
			arr[0] += 1;
			arr[arr.length - 1] += 1;
			total = total + 2;
		}
		return arr;
	}
}
