package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnectionLength extends MovieConnection {
    private static final String QUERY_WITH_LENGTH = "SELECT film.title, film.length " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "WHERE category.category_id = ";

    private static final String QUERY_WITH_LENGTH_0 = "SELECT film.title, film.length " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "GROUP BY title;";
    private static final String QUERY_PART_2 = " AND film.title LIKE '";
    private static final String QUERY_PART_3 = "%' OR category.category_id = ";
    private static final String QUERY_PART_4 = " AND film.title LIKE '%";
    private static final String QUERY_PART_5 = "%' OR category.category_id = ";
    private static final String QUERY_PART_6 = " AND film.title LIKE '%";
    private static final String QUERY_PART_7 = "%' GROUP BY title;";
    private final String filmTitel;
    private final int length;

    public MovieConnectionLength(String filmTitel, int length) {
        super(filmTitel);
        this.filmTitel = filmTitel;
        this.length = length;
    }

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs;
        if (category == 0) {
            rs = DBModel.getInstance().executeQuery(QUERY_WITH_LENGTH_0);
        } else {
            rs = DBModel.getInstance().executeQuery(
                    QUERY_WITH_LENGTH + category +
                            QUERY_PART_2 + title +
                            QUERY_PART_3 + category +
                            QUERY_PART_4 + title +
                            QUERY_PART_5 + category +
                            QUERY_PART_6 + title +
                            QUERY_PART_7
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
        return "Movie: " + filmTitel +
                " [" + length +
                "]" + '\n';
    }
}
