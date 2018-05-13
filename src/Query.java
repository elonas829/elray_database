import java.util.Scanner;

public class Query {

    private String query;
    private String option;
    private Employee employee = new Employee();
    private Assignment assignment = new Assignment();
    private Scanner scanner = new Scanner(System.in);

    Query() {
        query = "";
        option = "";
    }

    public void executeQuery() {
        askQuery();
        checkQuery();
    }

    private void askQuery() {
        System.out.println("1. All Employee info.");
        System.out.println("2. All Assignments.");
        System.out.println("3. Get Employee info by ID.");
        System.out.print("Please enter option: ");
        option = scanner.next();
        System.out.println("---------------------------------------------------------");
    }

    private void checkQuery() {
        switch (option) {
            case "1":
                query = employeeQueryString();
                break;
            case "2":
                query = assignmentQueryString();
                break;
            case "3":
                query = employeeInfoByIdQueryString();
            default:
                break;
        }
    }

    public void iterateData() throws Exception {
        switch (option) {
            case "1":
                employee.iterateAllEmployeeQuery();
                break;
            case "2":
                assignment.iterateAssignmentQuery();
                break;
            case "3":
                employee.iterateEmployeeInfoByIdQuery();
            default:
                break;
        }
    }

    private String employeeQueryString() {
        return "SELECT  * FROM employee ";
    }

    private String assignmentQueryString() {
        return "SELECT `assignment`.`ProjectID`,\n" +
                "    `assignment`.`EmpID`,\n" +
                "    `employee`.`FirstName`,\n" +
                "    `employee`.`LastName` \n" +
                "FROM `elray`.`assignment` \n" +
                "INNER JOIN `employee` ON `assignment`.`EmpID`=`employee`.`EmpID`;\n";
    }

    private String getEmployeeId() {
        System.out.print("Enter employee ID: ");
        return scanner.next();

    }

    private String employeeInfoByIdQueryString() {
        return "SELECT FirstName, LastName, EmpID "
                + "FROM employee "
                + "WHERE EmpID = " + getEmployeeId();
    }

    public String getQuery() {
        return query;
    }
}