package de.softwaretechnik.models;

import de.softwaretechnik.program.Program;

import java.sql.*;

//DBModel als Singleton

public class DBModel {

    private static final DBModel instance = new DBModel();
    private static Connection connection;
    private static Statement statement;
    ResultSet resultSet;


    private DBModel(){
        try {
            connection = DriverManager.getConnection(Program.DBCONSOLE, "root", "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBModel getInstance(){
        return instance;
    }

    public ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
