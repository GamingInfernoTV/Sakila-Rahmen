package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Movie {
    //TODO: Limit auflösen
    private static final String _query = "SELECT film_id, title, length, release_year, description FROM film LIMIT 10";
    private int _filmID;
    private int _releaseYear;
    private int _length;
    private String _title;
    private String _description;

    public Movie(int _filmID, String _title, int _length, int _releaseYear, String _description) {
        this._filmID = _filmID;
        this._releaseYear = _releaseYear;
        this._length = _length;
        this._title = _title;
        this._description = _description;
    }

    public static ArrayList<Movie> readMovies() throws SQLException {
        ArrayList<Movie> movieCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(_query);
        while (rs.next()) {
           movieCollection.add(new Movie(
                   rs.getInt(1),
                   rs.getString(2),
                   rs.getInt(3),
                   rs.getInt(4),
                   rs.getString(5)));
        }
        return movieCollection;
    }

    @Override
    public String toString() {
        return "Movie:" +
                _filmID +
                " " + _releaseYear +
                " , " + _length +
                " title: " + _title + '\'' +
                " description: '" + _description + '\'';
    }
}
