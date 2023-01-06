package de.softwaretechnik.models;

import java.io.Serializable;

public class SelectedCategory implements Serializable {
    public SelectedCategory() {
        //not used
    }

    public int setCat(String s) {
        int cat = 0;
        switch (s) {
            case "All" -> cat = 0;
            case "Action" -> cat = 1;
            case "Animation" -> cat = 2;
            case "Children" -> cat = 3;
            case "Classics" -> cat = 4;
            case "Comedy" -> cat = 5;
            case "Documentary" -> cat = 6;
            case "Drama" -> cat = 7;
            case "Family" -> cat = 8;
            case "Foreign" -> cat = 9;
            case "Games" -> cat = 10;
            case "Horror" -> cat = 11;
            case "Music" -> cat = 12;
            case "New" -> cat = 13;
            case "Sci-Fi" -> cat = 14;
            case "Sports" -> cat = 15;
            case "Travel" -> cat = 16;
            default -> System.out.println("ungültige Kategorie!");
        }
        return cat;
    }
}