package de.softwaretechnik.models;

import de.softwaretechnik.program.Program;

import java.sql.*;

/*

    DB Modell als Singelton
 */

public class DBModel {

    private static DBModel instance = new DBModel();
    private static Connection connection;
    private static Statement statement;
    ResultSet _resultSet;


    private DBModel(){
        try {
            connection = DriverManager.getConnection(Program.DBCON, "root", "");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void query() {


    }

    public static DBModel getInstance(){
        return instance;
    }


    public ResultSet executeQuery(String query) {
        try {
            statement = connection.createStatement();
            _resultSet = statement.executeQuery(query);

            return _resultSet;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
