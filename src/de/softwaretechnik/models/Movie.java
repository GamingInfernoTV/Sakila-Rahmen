package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Movie {
    private static final String QUERY = "SELECT film_id, title, length, release_year, description FROM film";
    private final int filmID;
    private final String title;
    private final int releaseYear;
    private final int length;
    private final String description;

    public Movie(int filmID, String title, int length, int releaseYear, String description) {
        this.filmID = filmID;
        this.releaseYear = releaseYear;
        this.length = length;
        this.title = title;
        this.description = description;
    }

    public static ArrayList<Movie> readMovies() throws SQLException {
        ArrayList<Movie> movieCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(QUERY);
        while (rs.next()) {
           movieCollection.add(new Movie(
                   rs.getInt(1),
                   rs.getString(2),
                   rs.getInt(3),
                   rs.getInt(4),
                   rs.getString(5))
           );
        }
        return movieCollection;
    }

    @Override
    public String toString() {
        return "Movie:" + filmID +
                " " + releaseYear +
                " , " + length +
                " title: " + title +
                " description: '" + description + '\n';
    }
}
