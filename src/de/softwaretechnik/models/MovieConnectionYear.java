package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieConnectionYear extends MovieConnection {
    private static final String QUERY_WITH_YEAR = "SELECT film.title, film.release_year " +
            "FROM film_category " +
            "INNER JOIN film ON film.film_id = film_category.film_id " +
            "INNER JOIN category ON category.category_id = film_category.category_id " +
            "WHERE category.category_id = ";

    private static final String QUERY_WITH_YEAR_0 = "SELECT film.title, film.release_year " +
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
    private final int realeaseYear;

    public MovieConnectionYear(String filmTitel, int realeaseYear) {
        super(filmTitel);
        this.filmTitel = filmTitel;
        this.realeaseYear = realeaseYear;
    }

    public static ArrayList<MovieConnection> readMovieConnections(int category, String title) throws SQLException {
        ArrayList<MovieConnection> movieConnectionCollection = new ArrayList<>();
        ResultSet rs;
        if (category == 0) {
            rs = DBModel.getInstance().executeQuery(QUERY_WITH_YEAR_0);
        } else {
            rs = DBModel.getInstance().executeQuery(
                    QUERY_WITH_YEAR + category +
                            QUERY_PART_2 + title +
                            QUERY_PART_3 + category +
                            QUERY_PART_4 + title +
                            QUERY_PART_5 + category +
                            QUERY_PART_6 + title +
                            QUERY_PART_7
            );
        }
        while (rs.next()) {
            movieConnectionCollection.add(new MovieConnectionYear(
                    rs.getString(1),
                    rs.getInt(2))
            );
        }
        return movieConnectionCollection;
    }

    @Override
    public String toString() {
        return "Movie: " + filmTitel +
                " (" + realeaseYear +
                ")" + '\n';
    }
}
