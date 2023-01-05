package de.softwaretechnik.models;

import java.io.Serializable;

public class SelectedCategory implements Serializable {
    public SelectedCategory() {
    }

    public int setCat(String s) {
        int cat = 0;
        switch (s) {
            case "All":
                cat = 0;
                break;
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
}