package de.softwaretechnik.interfaces;

import java.awt.event.*;

public interface IMainListener extends ActionListener, ItemListener, TextListener {

    //Interface Methoden definieren für alle Events des Programms
    @Override
    void actionPerformed(ActionEvent e);
    void startProgram();
    void exitProgram();
}
