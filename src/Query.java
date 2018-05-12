import java.sql.SQLException;
import java.util.Scanner;

public class Query {

    private String query;
    private String option;

    private Employee employee = new Employee();
    private Assignment assignment = new Assignment();

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

        System.out.println("1. All Employee info.");
        System.out.println("2. Assignments");
        System.out.print("Please enter option: ");
        option = scanner.next();
        System.out.println("---------------------------------------------------------");
    }

    private void checkQuery() {
        switch (option) {
            case "1":
                query = "SELECT  * FROM employee ";
                break;
            case "2":
                query = "SELECT `assignment`.`ProjectID`,\n" +
                        "    `assignment`.`EmpID`,\n" +
                        "    `employee`.`FirstName`,\n" +
                        "    `employee`.`LastName` \n" +
                        "FROM `elray`.`assignment` \n" +
                        "INNER JOIN `employee` ON `assignment`.`EmpID`=`employee`.`EmpID`;\n";
                break;
        }
    }

    public void iterateData() throws Exception {
        switch (option) {
            case "1":
                employee.iterateEmployeeQuery();
                break;
            case "2":
                assignment.iterateAssignmentQuery();
                break;
        }
    }

    public String getQuery() {
        return query;
    }
}
