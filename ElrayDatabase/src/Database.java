import java.sql.*;
import java.util.Scanner;

public class Database {
    Query query = new Query();

    private final String url = "jdbc:mysql://localhost/elray?autoReconnect=true&useSSL=false";
    private final String username = "root";
    private final String password = "root";

    String employeeFName, employeeLName, employeeID;

    private Connection connection = null;
    private ResultSet resultSet = null;

    public void executeDatabaseQuery() {
        loadDatabaseDriver();
        databaseConnection();
        iterateQueryResults();
    }

    private void loadDatabaseDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            logDriverError(e);
        }
    }

    private void databaseConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    public boolean isDatabaseConnected() {
        return connection != null;
    }

    private void setPreparedStatementToResultSet() {
        try {
            resultSet = prepareQueryStatement().executeQuery();
        } catch (SQLException e) {
            logSQLError(e);
        }
    }

    public PreparedStatement prepareQueryStatement(){
        try {
            return connection.prepareStatement(queryStatement());
        } catch (SQLException e) {
            logSQLError(e);
        }
        return null;
    }

    private String queryStatement() {
        if (promptQuery().equals("employee")) {
            return "SELECT  * FROM employee";
        } else {
            return "";
        }
    }

    private String promptQuery() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Employee info.");
        System.out.print("Please enter option: ");
        return scanner.next();
    }

    public void iterateQueryResults() {
        if (isDatabaseConnected()) {
            try {
                setPreparedStatementToResultSet();
                if(resultSet.next()) {
                    iterateEmployeeQuery();
                }
            } catch (SQLException e) {
                logSQLError(e);
            }
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
            employeeFName = resultSet.getString("FirstName");
            employeeLName = resultSet.getString("LastName");
            employeeID = resultSet.getString("empid");
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