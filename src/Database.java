import java.sql.*;
import java.util.Scanner;

public class Database {

    private String employeeFName, employeeLName, employeeID;

    public Connection connection = null;
    private ResultSet resultSet = null;

    Query query = new Query();

    public void executeDatabaseQuery() throws Exception {
        loadDatabaseDriver();
        databaseConnection();
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
            if (resultSet.next()) {
                iterateEmployeeQuery();
            }
        }
    }

    private void iterateEmployeeQuery() throws Exception {
        do {
            employeeToString();
        } while (resultSet.next());
    }

    private void setPreparedStatementToResultSet() throws Exception {
        resultSet = prepareQueryStatement().executeQuery();
    }

    private PreparedStatement prepareQueryStatement() throws Exception {
        return connection.prepareStatement("SELECT  * FROM employee ");
    }

    private void getEmployeeInfo() throws Exception {
        employeeFName = resultSet.getString("FirstName");
        employeeLName = resultSet.getString("LastName");
        employeeID = resultSet.getString("empid");
    }

    private String promptQuery() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Employee info.");
        System.out.print("Please enter option: ");
        return scanner.next();
    }

    private void employeeToString() throws Exception {
        getEmployeeInfo();
        System.out.println("Employee: " + employeeFName + " " + employeeLName);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("---------------------------------------------------------");
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