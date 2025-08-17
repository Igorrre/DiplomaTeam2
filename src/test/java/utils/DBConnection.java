package utils;

import lombok.extern.log4j.Log4j2;
import tests.ui.BaseTest;

import java.sql.*;

@Log4j2
public class DBConnection extends BaseTest {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    private static final String URL = "jdbc:postgresql://82.142.167.37:4832/pflb_trainingcenter";

    public void connect() {
        try {
            log.info("Access to the database");
            connect = DriverManager.getConnection(URL, user1, password1);
            statement = connect.createStatement();
        } catch (SQLException e) {
            log.error("Connection cannot be established");
            e.printStackTrace();
        }
    }

    public ResultSet select(String query) {
        log.info("Getting data from a database");
        try {
            log.info("Executing query: {}", query);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            log.error("Query failed");
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            if (connect != null) {
                log.info("Closing the connection");
                connect.close();
            }
            if (statement != null) {
                log.info("Closing the connection");
                statement.close();
            }
            if (result != null) {
                log.info("Closing the connection");
                result.close();
            }
        } catch (Exception ignore) {
        }
    }
}
