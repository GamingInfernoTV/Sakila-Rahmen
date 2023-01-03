package de.softwaretechnik.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Actor {
    private static final String _query = "SELECT actor_id, first_name, last_name FROM actor";
    private int _actorID;
    private String _firstName;
    private String _lastName;


    public Actor(int _actorID, String _firstName, String _lastName) {
        this._actorID = _actorID;
        this._firstName = _firstName;
        this._lastName = _lastName;
    }

    public static ArrayList<Actor> readActors() throws SQLException {
        ArrayList<Actor> actorCollection = new ArrayList<>();
        ResultSet rs = DBModel.getInstance().executeQuery(_query);
        while (rs.next()) {
            actorCollection.add(new Actor(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3)
                    ));
        }

        return actorCollection;
    }

    @Override
    public String toString() {
        return _actorID + ": " + _firstName + " " + _lastName;
    }
}
