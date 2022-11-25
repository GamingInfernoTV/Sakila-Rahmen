package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Category {
    private static final String _query = "SELECT category_id, name FROM category";
    private int _id;
    private String _name;

    public Category(int _id, String _name) throws SQLException {
        this._id = _id;
        this._name = _name;
    }

    public static ArrayList<Category> readCategories() throws SQLException {
        ArrayList<Category> categoriesCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(_query);
        while (rs.next()) {
            categoriesCollection.add(new Category(
                            rs.getInt(1),
                            rs.getString(2)));
        }

        return categoriesCollection;
    }

    @Override
    public String toString() {
        return _id + ": " + _name;
    }
}
