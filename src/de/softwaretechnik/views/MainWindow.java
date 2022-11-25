package de.softwaretechnik.views;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindow extends Frame {
	
	/*
		Main Window als Singleton
		Nur UI/GUI spezifische Implementierungen
	 */

	// ------------------------------------------------------------------------------------------------
	// Singleton
	private static MainWindow window = new MainWindow();
	public static MainWindow getInstance(){
		return window;
	}

	public final Choice GenreChoice = new Choice();
	private final TextField testfield = new TextField();

	// GUI Elements
	private MenuBar _menuBar;

	//information to show
	private boolean yearBool = false;
	private boolean durationBool = false;


	private MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");
		setSize(500,600);
		setBackground(Color.darkGray);
		setLayout(new GridLayout(3,1));
		add(GenreChoice,BorderLayout.NORTH);
		add(testfield,BorderLayout.CENTER);
		createGUI();
	}

	public void createGUI(){
		setMenuBar(createMainMenu());
	}


	private MenuBar createMainMenu(){
		MenuBar menuBar = new MenuBar();
		Menu menuProgram = new Menu("Program");
		menuProgram.add(new MenuItem("Beenden") );

		menuBar.add(menuProgram);

		Menu informationSelection = new Menu("Anzeige");
		CheckboxMenuItem Year = new CheckboxMenuItem("Erscheinungsjahr");
		Year.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (Year.getState()){
					yearBool = true;
				} else {
					yearBool = false;
				}
				testfield.setText("Jahre: " + yearBool + " dauer: " + durationBool);
			}
		});
		informationSelection.add(Year);
		CheckboxMenuItem duration = new CheckboxMenuItem("Filmlänge");
		duration.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (duration.getState()){
					durationBool = true;
				} else {
					durationBool = false;
				}
				testfield.setText("Jahre: " + yearBool + " dauer: " + durationBool);
			}
		});
		informationSelection.add(duration);

		menuBar.add(informationSelection);
		return menuBar;
	}

	
}
