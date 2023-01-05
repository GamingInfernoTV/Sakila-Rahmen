package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnectionYear extends MovieConnection {
    private static final String _queryWithYear = "SELECT film.title, film.release_year " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "WHERE category.category_id = ";

    private static final String _queryWithYear0 = "SELECT film.title, film.release_year " +
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
    private int _queryCategory;
    private int _filmCategoryFilmId;
    private String _filmTitel;
    private int _filmCategoryCategoryId;
    private int _realeaseYear;
    private int _length;
    private String _categoryName;

    public MovieConnectionYear(String _filmTitel, int _realeaseYear) {
        super(_filmTitel);
        this._filmTitel = _filmTitel;
        this._realeaseYear = _realeaseYear;
    }

    /*public MovieConnectio(String _filmTitel, int _realeaseYear) {
        this._filmTitel = _filmTitel;
        this._realeaseYear = _realeaseYear;
    }*/

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs;
        if (category == 0) {
            rs = DBModel.getInstance().executeQuery(_queryWithYear0);
        } else {
            rs = DBModel.getInstance().executeQuery(
                    _queryWithYear + category +
                            _queryPart2 + title +
                            _queryPart3 + category +
                            _queryPart4 + title +
                            _queryPart5 + category +
                            _queryPart6 + title +
                            _queryPart7
            );
        }
        while (rs.next()) {
            movieConnectionCollection.add(new MovieConnectionYear(
                    rs.getString(1),
                    rs.getInt(2)
            ));
        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "Movie: " + _filmTitel +
                " (" + _realeaseYear +
                ")" + '\n';
    }
}
