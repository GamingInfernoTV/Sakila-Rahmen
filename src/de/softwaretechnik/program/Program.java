package de.softwaretechnik.program;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.models.Model;
import de.softwaretechnik.views.MainWindow;

/**
 * @author Aaron m30115
 * @author Max TODO insert mNummer
 */
public class Program {

	public static final String APP_TITLE = "Sakila Viewer";
	public static final float APP_V = 0.1F;
	public static final String DBCONSOLE = "jdbc:mysql://localhost/sakila";

	public static void main(String[] args) {
		// lose Kopplung von GUI und Datenmodel
		Model model = new Model();
		MainWindow mw = MainWindow.getInstance();
		MainWindowController mc = new MainWindowController(mw, model);
		mc.startProgram();
	}
}
