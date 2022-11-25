package de.softwaretechnik.models;

import java.util.ArrayList;

public class Actor {
    private int _actorID;
    private String _firstName;

    private ArrayList<Actor> _actor = new ArrayList<>();


    public Actor(int _actorID) {
        this._actorID = _actorID;

    }
}
