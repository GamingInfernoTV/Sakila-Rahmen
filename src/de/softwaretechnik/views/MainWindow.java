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
	private static final MainWindow window = new MainWindow();
	public final SelectedCategory selectedCategory = new SelectedCategory();
	public static MainWindow getInstance(){
		return window;
	}
	private static final Model model = new Model();
	public final Choice genreChoice = new Choice();
	public final TextField textField = new TextField();
	public final List filmList = new List();
	public final Button searchButton = new Button();
	public final Button frameButton = new Button();
	private final TextArea textArea = new TextArea();
	private final Frame frame = new Frame();
	private boolean year = false;
	private boolean length = false;

	public MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");
		setSize(600,600);
		setBackground(Color.darkGray);
		setResizable(false);
		searchButton.setLabel("Search");
		textField.setSize(400, 20);

		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(textField);
		textField.setPreferredSize(textField.getSize());
		panel.add(genreChoice);
		panel.add(searchButton);

		frameButton.setLabel("Close");
		frame.setSize(300, 200);
		frame.setLayout(new BorderLayout());
		frame.add(frameButton, BorderLayout.NORTH);

		setLayout(new BorderLayout());
		add(panel,BorderLayout.NORTH);
		add(filmList, BorderLayout.CENTER);
		add(textArea, BorderLayout.SOUTH);
		textArea.setEditable(false);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedCat = selectedCategory.setCat(genreChoice.getSelectedItem());
					String selectedTitle = textField.getText();
					drawCategory(selectedCat, selectedTitle);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		frameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		textField.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				try {
					int selectedCat = selectedCategory.setCat(genreChoice.getSelectedItem());
					String selectedTitle = textField.getText();
					if (selectedTitle.length() > 3) {
						drawCategory(selectedCat, selectedTitle);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		filmList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] movName = filmList.getSelectedItem().toCharArray();
				StringBuilder sb = new StringBuilder();

				for(int i=7 ; i<movName.length-1;i++){
					if (movName[i]==' ' && movName[i+1] == '(' || movName[i+1] == '['){
						break;
					}
					sb.append(movName[i]);
				}
				String movie = sb.toString();
				String description = null;
				try {
					description = MovieConnection.findMovie(movie);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				textArea.setText(description);

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
		MenuItem menuItem = new MenuItem("Beenden");

		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuProgram.add(menuItem);

		menuBar.add(menuProgram);

		Menu informationSelection = new Menu("Anzeige");
		CheckboxMenuItem Year = new CheckboxMenuItem("Erscheinungsjahr");

		Year.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (Year.getState()){
					year = true;
				} else {
					year = false;
				}
				int selectedCat = selectedCategory.setCat(genreChoice.getSelectedItem());
				String selectedTitle = textField.getText();
				try {
					drawBoolCategory(selectedCat, selectedTitle, year, length);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});

		informationSelection.add(Year);
		CheckboxMenuItem duration = new CheckboxMenuItem("Filmlänge");

		duration.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (duration.getState()){
					length = true;
				} else {
					length = false;
				}
				int selectedCat = selectedCategory.setCat(genreChoice.getSelectedItem());
				String selectedTitle = textField.getText();
				try {
					drawBoolCategory(selectedCat, selectedTitle, year, length);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		informationSelection.add(duration);
		menuBar.add(informationSelection);
		return menuBar;
	}

	public void drawCategory(int selectedCategory, String selectedTitle) throws SQLException {
		filmList.removeAll();
		ArrayList<MovieConnection> movieConnections;
		movieConnections = model.getAllMovieConnections(selectedCategory, selectedTitle);
		for (MovieConnection movieConnection : movieConnections) {
			filmList.add(String.valueOf(movieConnection));
		}
	}

	public void drawBoolCategory(int selectedCategory, String selectedTitle, Boolean year, Boolean length) throws SQLException {
		filmList.removeAll();
		ArrayList<MovieConnection> movieConnections;
		movieConnections = model.getAllMovieConnections(selectedCategory, selectedTitle, year, length);
		for (MovieConnection movieConnection : movieConnections) {
			filmList.add(String.valueOf(movieConnection));
		}
	}
}
