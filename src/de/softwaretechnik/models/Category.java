package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category {
    private static final String QUERY = "SELECT category_id, name FROM category";
    private final int id;
    private final String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Category> readCategories() throws SQLException {
        ArrayList<Category> categoriesCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(QUERY);
        while (rs.next()) {
            categoriesCollection.add(new Category(
                            rs.getInt(1),
                            rs.getString(2))
            );
        }
        return categoriesCollection;
    }

    @Override
    public String toString() {
        return name;
    }
}
