package de.softwaretechnik.controller;

import de.softwaretechnik.interfaces.IMainListener;
import de.softwaretechnik.models.Category;
import de.softwaretechnik.models.Model;
import de.softwaretechnik.models.SelectedCategory;
import de.softwaretechnik.views.MainWindow;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainWindowController extends WindowAdapter implements IMainListener {

    private MainWindow window;
    private Model model;
    private SelectedCategory selectedCategory = new SelectedCategory();

    public MainWindowController(MainWindow mw, Model m) {
        window = mw;
        model = m;

        window.addWindowListener(this);


    }

    // ---------------------------------------------------------------------------
    // Controller Methods
    public void startProgram(){
        window.setVisible(true);
    }

    public void exitProgram(){
        window.dispose();
        System.exit(0);
    }

    // ---------------------------------------------------------------------------
    // Events
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void textValueChanged(TextEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        window.createGUI();
        ArrayList<Category> categorys;
        try {
            categorys = model.getAllCategories();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        window.genreChoice.add("All");
        for (int i = 0; i < categorys.size();i++ ) {
            window.genreChoice.add(String.valueOf(categorys.get(i)));
        }
        window.genreChoice.select(0);
        try {
            int selectedCat = selectedCategory.setCat(window.genreChoice.getSelectedItem());
            String selectedTitle = window.textField.getText();
            window.drawCategory(selectedCat, selectedTitle);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getValue() {
        return window.genreChoice.getSelectedItem();
    }


    @Override
    public void windowClosing(WindowEvent e){
      exitProgram();
    }
    // ---------------------------------------------------------------------------

}
