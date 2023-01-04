package de.softwaretechnik.views;

import de.softwaretechnik.models.Model;
import de.softwaretechnik.models.MovieConnection;
import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainWindow extends Frame {
	
	/*
		Main Window als Singleton.
		Nur UI/GUI spezifische Implementierungen
	 */

	// ------------------------------------------------------------------------------------------------
	// Singleton
	private static MainWindow window = new MainWindow();
	public static MainWindow getInstance(){
		return window;
	}
	private static Model model = new Model();

	public final Choice GenreChoice = new Choice();
	public final TextField testfield = new TextField();
	public final TextArea textArea = new TextArea();
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
		add(textArea, BorderLayout.CENTER);
		GenreChoice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					drawCategory(setCat(String.valueOf(e.getItem())));
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		createGUI();
	}

	public int setCat(String s) {
		int cat = 0;
		switch (s) {
			case "Action":
				cat = 1;
				break;
			case "Animation":
				cat = 2;
				break;
			case "Children":
				cat = 3;
				break;
			case "Classics":
				cat = 4;
				break;
			case "Comedy":
				cat = 5;
				break;
			case "Documentary":
				cat = 6;
				break;
			case "Drama":
				cat = 7;
				break;
			case "Family":
				cat = 8;
				break;
			case "Foreign":
				cat = 9;
				break;
			case "Games":
				cat = 10;
				break;
			case "Horror":
				cat = 11;
				break;
			case "Music":
				cat = 12;
				break;
			case "New":
				cat = 13;
				break;
			case "Sci-Fi":
				cat = 14;
				break;
			case "Sports":
				cat = 15;
				break;
			case "Travel":
				cat = 16;
				break;
			default:
				System.out.println("ungültige Kategorie!");
				break;
		}
		return cat;
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

	public void drawCategory(int selectedCategory) throws SQLException {
		window.textArea.setText("");
		ArrayList<MovieConnection> movieConnections;
		movieConnections = model.getAllMovieConnections(selectedCategory);
		for (int i = 0; i < movieConnections.size(); i++) {
			window.textArea.append(String.valueOf(movieConnections.get(i)));
		}
	}

}
