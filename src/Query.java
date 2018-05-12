import java.sql.SQLException;
import java.util.Scanner;

public class Query {

    private String query;
    private String option;

    Query() {
        query = "";
        option = "";
    }

    public void executeQuery() {
        askQuery();
        checkQuery();
    }

    private void askQuery() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Employee info.");
        System.out.print("Please enter option: ");
        option = scanner.next();
    }

    private String checkQuery() {

        switch (option) {
            case "1":
                query = "SELECT  * FROM employee ";
                break;
        }
        return query;
    }

    public String getQuery() {
        return checkQuery();
    }

    private void logSQLError(SQLException e) {
        System.err.println("SQL exception: " + e.getMessage());
        System.err.println("SQL state: " + e.getSQLState());
        System.err.println("Error code: " + e.getErrorCode());
    }
}
