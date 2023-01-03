package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnection {
    private static final String _query = "SELECT film_category.film_id, film.film_id, film_category.category_id, category.category_id, film.title, category.name FROM film_category, film, category" +
            " JOIN film ON film_category.film_id = film.film_id" +
            " GROUP BY film_category.film_id";
    private int _filmCategoryFilmId;
    private int _filmFilmId;
    private int _filmId;
    private int _filmCategoryCategoryId;
    private int _categoryCategoryId;
    private String _filmTitel;
    private String _categoryName;

    private MovieConnection (int _filmId, int _filmCategoryCategoryId, int _categoryCategoryId, String _filmTitel, String _categoryName) {
        this._filmId = _filmId;
        this._filmCategoryCategoryId = _filmCategoryCategoryId;
        this._categoryCategoryId = _categoryCategoryId;
        this._filmTitel = _filmTitel;
        this._categoryName = _categoryName;
    }

    public static ArrayList<MovieConnection> readMovieConnections() throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(_query);
        while (rs.next()) {
            rs.getInt(1);
            rs.getInt(2);
            rs.getInt(3);
            rs.getString(4);
            rs.getString(5);
        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "MovieConnection:" +
                "_filmCategoryFilmId=" + _filmCategoryFilmId +
                ", _filmFilmId=" + _filmFilmId +
                ", _filmCategoryCategoryId=" + _filmCategoryCategoryId +
                ", _categoryCategoryId=" + _categoryCategoryId +
                ", _filmTitel='" + _filmTitel + '\'' +
                ", _categoryName='" + _categoryName + '\'' +
                '}';
    }
}
