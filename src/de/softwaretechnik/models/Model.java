package de.softwaretechnik.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class Model {

    /*
        Gesamte Business Logik der Modelle die für den Controller und die aktuellle View notwendig ist.
     */

    public ArrayList<Category> getAllCategories() throws SQLException {
        return Category.readCategories();
    }

    public ArrayList<Actor> getAllActors() throws SQLException {
        return Actor.readActors();
    }

    public ArrayList<Movie> getAllMovies() throws SQLException {
        return Movie.readMovies();
    }

    public ArrayList<MovieConnection> getAllMovieConnections(int category, String title) throws SQLException {
        return MovieConnection.readMovieConnections(category, title);
    }
}
