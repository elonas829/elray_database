import java.sql.*;

public class Database {

    private Query query = new Query();
    private PassResultSet passResultSet = new PassResultSet();

    private Connection connection;

    Database() {
        connection = null;
    }

    void executeProgram() throws Exception {
        loadDatabaseDriver();
        databaseConnection();
        query.dataManipulation();
        whichQuery();

        connection.close();
    }

    private void whichQuery() throws Exception {
        if (query.getOption().equals("1") || query.getOption().equals("2") || query.getOption().equals("3")) {
            iterateQueryResults();
        } else if(query.getOption().equals("4")) {
            prepareUpdate().executeUpdate();
            System.out.println("Data update successful.");
        }
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

    private PreparedStatement prepareUpdate() throws Exception {
        return connection.prepareStatement(query.getQuery());
    }
}