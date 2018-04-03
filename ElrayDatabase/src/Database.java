import java.sql.*;

public class Database {
    private final String url = "jdbc:mysql://localhost/Elray?autoReconnect=true&useSSL=false";
    private final String username = "chronic";
    private final String password = "Magickey20";

    String employeeFName, employeeLName, employeeID;

    private ResultSet resultSet = null;
    private Connection connection = null;


    public void executeDatabase() {
        loadDatabaseDriver();
        databaseConnection();
        iterateQueryResults();
    }

    private void loadDatabaseDriver() {
        // Load database driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            logDriverError(e);
        }
    }

    private void databaseConnection() {
        // Establish database connection
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    private void iterateQueryResults() {
        if (isDatabaseConnected()) {
            try {
                prepareQueryStatement();
                if(resultSet.next()) {
                    iterateEmployeeQuery();
                }
            } catch (SQLException e) {
                logSQLError(e);
            }
        }
    }

    private void prepareQueryStatement() {
        try {
            PreparedStatement employees = connection.prepareStatement("SELECT * FROM employee");
            resultSet = employees.executeQuery();
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    private void iterateEmployeeQuery() {
        try {
            do {
                employeeToString();
            } while (resultSet.next());
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    private void getEmployeeInfo() {
        try {
            employeeFName = resultSet.getString("employeefname");
            employeeLName = resultSet.getString("employeelname");
            employeeID = resultSet.getString("employeeid");
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    private void employeeToString() {
        getEmployeeInfo();
        System.out.println("Employee: " + employeeFName + " " + employeeLName);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("---------------------------------------------------------");
    }



    private boolean isDatabaseConnected() {
        return connection != null;
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

//                PreparedStatement employees = conn.prepareStatement(
//                    "SELECT employeeFName, employeeLName, employeeID "
//                            + "FROM employee "
//                            + "WHERE employeeid = ?");
//                final String getEmployeeID = "0";
//                employees.setString(1, getEmployeeID);