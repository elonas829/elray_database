import java.sql.*;
import java.util.Scanner;

public class Database {

    private final String URL = "jdbc:mysql://localhost/elray";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private Connection connection;
    public ResultSet resultSet = null;

    private String option;
    private Employee employee = new Employee();
    private Assignment assignment = new Assignment();
    private Scanner scanner = new Scanner(System.in);

    Database() throws Exception {
        loadDatabaseDriver();
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        iterateQueryResults();
        connection.close();
    }

    Database(String url, String username, String password) throws Exception {
        loadDatabaseDriver();
        connection = DriverManager.getConnection(url, username, password);
        iterateQueryResults();
        connection.close();
    }

    private void loadDatabaseDriver() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
    }

    private boolean isDatabaseConnected() {
        return connection != null;
    }

    private void iterateQueryResults() throws Exception {
        if (isDatabaseConnected()) {
            resultSet = prepareQueryStatement().executeQuery();
            if (resultSet.next()) {
                iterateData();
            }
        }
    }

    private PreparedStatement prepareQueryStatement() throws Exception {
        return connection.prepareStatement(checkQuery());
    }

    private String checkQuery() {
        String query;
        System.out.println("1. All Employee info.");
        System.out.println("2. All Assignments.");
        System.out.println("3. Get Employee info by ID.");
        System.out.println("4. Update employee info.");
        System.out.print("Please enter option: ");
        option = scanner.next();
        System.out.println("-------------------------------------------");

        switch (option) {
            case "1":
                query = employee.employeeQueryString();
                break;
            case "2":
                query = assignment.assignmentQueryString();
                break;
            case "3":
                query = employee.infoByIdQueryString();
                break;
            case "4":
                query = employee.updateEmployeeInfo();
                break;
            default:
                query = "";
                break;
        }
        return query;
    }

    private void iterateData() throws Exception {

        switch (option) {
            case "1":
                employee.iterateAllEmployeeQuery(resultSet);
                break;
            case "2":
                assignment.iterateAssignmentQuery(resultSet);
                break;
            case "3":
                employee.iterateEmployeeInfoByIdQuery(resultSet);
            case "4":
                break;
            default:
                break;
        }
    }
}