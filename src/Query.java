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

    public void dataManipulation() {
        askQuery();
        checkQuery();
    }

    private void askQuery() {
        System.out.println("1. All Employee info.");
        System.out.println("2. All Assignments.");
        System.out.println("3. Get Employee info by ID.");
        System.out.println("4. Update employee info.");
        System.out.print("Please enter option: ");
        option = scanner.next();
        System.out.println("-------------------------------------------");
    }

    private void checkQuery() {
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
            case "4":
                break;
            default:
                break;
        }
    }

    public String getQuery() {
        return query;
    }

    public String getOption() {
        return option;
    }
}