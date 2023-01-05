package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnectionLength extends MovieConnection {
    private static final String _queryWithLength = "SELECT film.title, film.length " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "WHERE category.category_id = ";

    private static final String _queryWithLength0 = "SELECT film.title, film.length " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "GROUP BY title;";
    private static final String _queryPart2 = " AND film.title LIKE '";
    private static final String _queryPart3 = "%' OR category.category_id = ";
    private static final String _queryPart4 = " AND film.title LIKE '%";
    private static final String _queryPart5 = "%' OR category.category_id = ";
    private static final String _queryPart6 = " AND film.title LIKE '%";
    private static final String _queryPart7 = "%' GROUP BY title;";
    private String _filmTitel;
    private int _length;

    public MovieConnectionLength(String _filmTitel, int _length) {
        super(_filmTitel);
        this._filmTitel = _filmTitel;
        this._length = _length;
    }

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs;
        if (category == 0) {
            rs = DBModel.getInstance().executeQuery(_queryWithLength0);
        } else {
            rs = DBModel.getInstance().executeQuery(
                    _queryWithLength + category +
                            _queryPart2 + title +
                            _queryPart3 + category +
                            _queryPart4 + title +
                            _queryPart5 + category +
                            _queryPart6 + title +
                            _queryPart7
            );
        }
        while (rs.next()) {
            movieConnectionCollection.add(new MovieConnectionLength(
            rs.getString(1),
            rs.getInt(2)
            ));

        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "Movie: " + _filmTitel +
                " [" + _length +
                "]" + '\n';
    }
}
