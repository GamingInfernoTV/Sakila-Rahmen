package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnection {
    private static final String _queryPart0 = "SELECT film.title " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "GROUP BY title;";
    private static final String _queryPart1 = "SELECT film.title " +
                                            "FROM film_category " +
                                            "INNER JOIN film ON film.film_id = film_category.film_id " +
                                            "INNER JOIN category ON category.category_id = film_category.category_id " +
                                            "WHERE category.category_id = ";

    private static final String _queryPart2 = " AND film.title LIKE '";
    private static final String _queryPart3 = "%' OR category.category_id = ";
    private static final String _queryPart4 = " AND film.title LIKE '%";
    private static final String _queryPart5 = "%' OR category.category_id = ";
    private static final String _queryPart6 = " AND film.title LIKE '%";
    private static final String _queryPart7 = "%' GROUP BY title;";
    private String _filmTitel;

    public MovieConnection (String _filmTitel) {
        this._filmTitel = _filmTitel;
    }

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs;
        if (category == 0) {
            rs = DBModel.getInstance().executeQuery(_queryPart0);
        } else {
            rs = DBModel.getInstance().executeQuery(
                    _queryPart1 + category +
                            _queryPart2 + title +
                            _queryPart3 + category +
                            _queryPart4 + title +
                            _queryPart5 + category +
                            _queryPart6 + title +
                            _queryPart7
            );
        }
        while (rs.next()) {
            movieConnectionCollection.add(new MovieConnection(
            rs.getString(1)
            ));

        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "Movie: " + _filmTitel + '\n';
    }

    public static String findMovie(String movie) throws SQLException {
        String Info = null;
        String query = "SELECT description FROM film WHERE title = '" + movie + "' ;";
        ResultSet rs = DBModel.getInstance().executeQuery(query);
        while(rs.next()) {
            Info = rs.getString(1);
        }
        return Info;
    }
}
