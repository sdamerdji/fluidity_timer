package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Dialog {
	public static JTextField numDebaters = new JTextField("100");
	private static String[] items = {"5 Rounds", "6 Rounds", "7 Rounds", "8 Rounds"};
	public static JComboBox combo = new JComboBox(items);
	private static String[] items2 = {"Primary Color", "Secondary Color",
									  "Every Minute",  "Last Minute", "Last 45s", "Last 30s"};
	public static JComboBox combo2 = new JComboBox(items2);
	private static String[] items3 = {"Red", 	"Orange", 	 "Yellow",  	"Green",
									  "Blue",	"Purple", 	 "Cyan", 	    "Fusia",
									  "Black",	"White",	 "Silver", 		"Pink"};
	public static JComboBox combo3 = new JComboBox(items3);
	
//	private static String[] debateEvents = {"Pofo", "Policy", "LD"};
//	public static JComboBox debateEventsCombo = new JComboBox(debateEvents);
	
	public static JTextField xLoc = new JTextField("000");
	public static JTextField yLoc = new JTextField("000");
	
	private static JPanel createSettingsPanel(){
		JPanel settingsPanel = new JPanel();
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new BorderLayout());
		
		JPanel zero = new JPanel();
		zero.add(new JLabel("Choose anything's color"));
		
		JPanel one = new JPanel();
		one.setLayout(new BorderLayout());
		
		JPanel oneHalf = new JPanel();
		oneHalf.setLayout(new FlowLayout());
		oneHalf.add(combo2);
		
		JPanel secondHalf = new JPanel();
		secondHalf.setLayout(new FlowLayout());
		JButton jb = new JButton("Do");
		jb.addActionListener(new ComboListener());
		jb.setBorder(new CompoundBorder(new LineBorder(Color.BLACK),
				new EmptyBorder(1, 10, 1, 10)));
		secondHalf.add(jb);
		secondHalf.add(combo3);
		
		colorPanel.add(zero, BorderLayout.PAGE_START);
		one.add(oneHalf, BorderLayout.NORTH);
		one.add(secondHalf, BorderLayout.CENTER);
		colorPanel.add(one, BorderLayout.CENTER);
		
		/*
		JPanel debateEvents = new JPanel();
		debateEvents.setLayout(new FlowLayout());
		debateEventsCombo.addActionListener(new ComboListener());
		debateEvents.add(new JLabel("  Change event:"));
		debateEvents.add(debateEventsCombo);
		*/
		JPanel location = new JPanel();
		location.setLayout(new BorderLayout());
		JPanel mid = new JPanel();
		mid.setLayout(new FlowLayout());
		location.add(new JLabel("          Set default location:"), BorderLayout.NORTH);

		JButton locButton = new JButton("Move");
		locButton.addActionListener(new ComboListener());
		mid.add(xLoc);
		mid.add(yLoc);
		mid.add(locButton);
		location.add(mid, BorderLayout.CENTER);
		
		settingsPanel.setLayout( new BorderLayout());
		settingsPanel.add(colorPanel, BorderLayout.NORTH);
//		settingsPanel.add(debateEvents, BorderLayout.CENTER);
		settingsPanel.add(location, BorderLayout.SOUTH);
		
		return settingsPanel;
	}

	private static JPanel createCalculatorPanel(){		
		JPanel panel = new JPanel();	
		JPanel topHalf = new JPanel(); // Top half for Calculator Panel
		combo.addActionListener(new ComboListener());
		
		JPanel northWest = new JPanel();
		JPanel north = new JPanel();
		north.add(combo);
		north.setLayout(new FlowLayout(0,0,0));
		JPanel west = new JPanel();
		west.setLayout(new FlowLayout(0,0,0)); 
		west.add(new JLabel(" # Debaters:"));
		west.add(numDebaters);
		northWest.setLayout(new BorderLayout());
		northWest.add(north, BorderLayout.NORTH);
		northWest.add(west, BorderLayout.SOUTH);
		
		JPanel northEast = new JPanel();
		JButton calculate = new JButton("Go");
		calculate.addActionListener(new ComboListener());
		calculate.setBorder(new CompoundBorder(new LineBorder(Color.BLACK),
							new EmptyBorder(15, 10, 15, 10)));
		northEast.add(calculate);
		
		topHalf.add(northWest, BorderLayout.WEST);
		topHalf.add(northEast, BorderLayout.EAST);
		
		JPanel botHalf = Prelims.makePrelimPanel(); // Bottom half for Calculator Panel	
		
		panel.setLayout(new BorderLayout());
		panel.add(topHalf, BorderLayout.NORTH);
		panel.add(botHalf, BorderLayout.CENTER);
		return panel;
	}
	
	public static void display() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel whole = new JPanel();
		whole.add(tabbedPane);
		tabbedPane.addTab("Settings", null, createSettingsPanel());
		tabbedPane.addTab("Calculator", null, createCalculatorPanel());
		JOptionPane.showOptionDialog(null, tabbedPane,"Advanced Features",
				JOptionPane.PLAIN_MESSAGE,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
	}
}