import java.sql.*;
import java.util.Scanner;

public class Database {

    private Query query = new Query();
    private PassResultSet passResultSet = new PassResultSet();

    private Connection connection;

    Database() {
        connection = null;
    }

    public void executeDatabaseQuery() throws Exception {
        loadDatabaseDriver();
        databaseConnection();
        query.executeQuery();
        iterateQueryResults();
    }

    private void loadDatabaseDriver() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
    }

    private void databaseConnection() throws Exception {
        final String URL = "jdbc:mysql://localhost/elray";
        final String USERNAME = "root";
        final String PASSWORD = "";

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private boolean isDatabaseConnected() {
        return connection != null;
    }

    private void iterateQueryResults() throws Exception {
        if (isDatabaseConnected()) {
            setPreparedStatementToResultSet();
            if (passResultSet.getResultSet().next()) {
                query.iterateData();
            }
        }
    }

    private void setPreparedStatementToResultSet() throws Exception {
        passResultSet.setResultSet(prepareQueryStatement().executeQuery());
    }

    private PreparedStatement prepareQueryStatement() throws Exception {
        return connection.prepareStatement(query.getQuery());
    }

    private void logSQLError(SQLException e) {
        System.err.println("SQL exception: " + e.getMessage());
        System.err.println("SQL state: " + e.getSQLState());
        System.err.println("Error code: " + e.getErrorCode());
    }

    private void logDriverError(Exception e) {
        System.err.println("Error when loading DB driver:");
        System.err.println(e.getMessage());
    }
}