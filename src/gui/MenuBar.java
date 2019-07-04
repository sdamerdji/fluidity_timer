package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MenuBar {
	public static JMenuBar 		menuBar = new JMenuBar();
	public static JMenu 		speechMenu = new JMenu(),
								prepCXMenu = new JMenu(),
								affPrepMenu = new JMenu(),
								negPrepMenu = new JMenu();
	public final static Border 	margin = new EmptyBorder(0, 0, 2, 0);
	
	// no comments for you
	// it was hard to write
	// so it should be hard to read
	public static JMenuBar getTimerBar() {
		menuBar.setBackground(ColorManager.primaryColor.darker());
		FlowLayout fl = new FlowLayout();
		fl.setHgap(3);
		fl.setVgap(0);
		menuBar.setLayout(fl);
		
		// Speech Menu
		menuFactory(speechMenu, "Speeches");
		makeMenuOne();
		menuBar.add(speechMenu);
		
		// Non-Speech Menu
		menuFactory(prepCXMenu, "CX & Prep");
		makeMenuTwo();
		menuBar.add(prepCXMenu);
		return menuBar;
	}
	
	public static void applyBarColor(Color c) {
		if (c.equals(Color.BLACK)){
			menuBar.setBackground(ColorManager.secondaryColor.darker());
		}
		else {
			menuBar.setBackground(c.darker());
		}
	}
	
	private static void menuFactory(JMenu jm, String name) {
		jm.setText(name);
		jm.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
		jm.setOpaque(false);
		jm.setBorderPainted(true);
		jm.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(jm);
	}
	
	private static void makeMenuOne() {
		menuItemFactory(speechMenu, "AC");
		menuItemFactory(speechMenu, "NC");
		menuItemFactory(speechMenu, "AR");
		menuItemFactory(speechMenu, "2NR");
		menuItemFactory(speechMenu, "2AR");
	}
	
	private static void makeMenuTwo() {
		menuItemFactory(prepCXMenu, "CX");
		menuItemFactory(prepCXMenu, "Aff Prep");
		menuItemFactory(prepCXMenu, "Neg Prep");
		menuItemFactory(prepCXMenu, "Alter Prep");
	}

	private static void menuItemFactory(JMenu jm, String name) {
		JMenuItem jmi = new JMenuItem(name);
		jmi.addActionListener(new MenuListener());
		jmi.setBorder(margin);
		jmi.setBorderPainted(true);
		jm.add(jmi);
	}
}