package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnection {
    private static final String _queryPart1 = "SELECT film_category.film_id, film.title, film_category.category_id, category.name " +
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
    private int _queryCategory;
    private int _filmCategoryFilmId;
    private String _filmTitel;
    private int _filmCategoryCategoryId;
    private String _categoryName;

    //(int _filmFilmId, int _filmCategoryCategoryId, int _categoryCategoryId, String _filmTitel, String _categoryName)
    public MovieConnection (int _filmCategoryFilmId, String _filmTitel, int _filmCategoryCategoryId, String _categoryName) {
        this._filmCategoryFilmId = _filmCategoryFilmId;
        this._filmTitel = _filmTitel;
        this._filmCategoryCategoryId = _filmCategoryCategoryId;
        this._categoryName = _categoryName;
    }

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(
                _queryPart1 + category +
                _queryPart2 + title +
                _queryPart3 + category +
                _queryPart4 + title +
                _queryPart5 + category +
                _queryPart6 + title +
                _queryPart7
        );
        while (rs.next()) {
            movieConnectionCollection.add(new MovieConnection(
                    rs.getInt(1),
            rs.getString(2),
            rs.getInt(3),
            rs.getString(4)
            ));

        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "Movie: " + _filmTitel +
                " Category: " + _categoryName + '\n';
    }
}
