package de.softwaretechnik.views;

import de.softwaretechnik.models.Model;
import de.softwaretechnik.models.MovieConnection;
import de.softwaretechnik.models.SelectedCategory;
import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.*;
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
	private final SelectedCategory selectedCategory = new SelectedCategory();

	public static MainWindow getInstance(){
		return window;
	}
	private static Model model = new Model();

	public final Choice GenreChoice = new Choice();
	public final TextField testfield = new TextField();
	public final TextField textField = new TextField();
	public final TextArea textArea = new TextArea();
	public final Button button = new Button();
	public final Checkbox checkbox = new Checkbox();
	// GUI Elements
	private MenuBar _menuBar;

	//information to show
	private boolean yearBool = false;
	private boolean durationBool = false;


	private MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");
		setSize(500,600);
		setBackground(Color.darkGray);
		setResizable(false);
		button.setLabel("Search");
		textArea.setEditable(false);
		textField.setSize(300, 20);

		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(textField);
		textField.setPreferredSize(textField.getSize());
		panel.add(GenreChoice);
		panel.add(button);

		setLayout(new BorderLayout());
		add(panel,BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(checkbox, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedCat = selectedCategory.setCat(GenreChoice.getSelectedItem());
					String selectedTitle = textField.getText();
					drawCategory(selectedCat, selectedTitle);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		textField.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				try {
					int selectedCat = selectedCategory.setCat(GenreChoice.getSelectedItem());
					String selectedTitle = textField.getText();
					drawCategory(selectedCat, selectedTitle);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				System.out.println("textField changed");
			}
		});
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

	public void drawCategory(int selectedCategory, String selectedTitle) throws SQLException {
		textArea.setText("");
		ArrayList<MovieConnection> movieConnections;
		movieConnections = model.getAllMovieConnections(selectedCategory, selectedTitle);
		for (int i = 0; i < movieConnections.size(); i++) {
			textArea.append(String.valueOf(movieConnections.get(i)));
		}
	}

}
