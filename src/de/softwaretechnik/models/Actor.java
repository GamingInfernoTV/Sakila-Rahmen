package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Actor {
    private static final String QUERY = "SELECT actor_id, first_name, last_name FROM actor";
    private final int actorID;
    private final String firstName;
    private final String lastName;


    public Actor(int actorID, String firstName, String lastName) {
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static ArrayList<Actor> readActors() throws SQLException {
        ArrayList<Actor> actorCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(QUERY);
        while (rs.next()) {
            actorCollection.add(new Actor(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3))
            );
        }
        return actorCollection;
    }

    @Override
    public String toString() {
        return actorID + ": " + firstName + " " + lastName;
    }
}
