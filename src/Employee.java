import java.util.Scanner;

public class Employee {

    private String employeeFName, employeeLName, employeeID, employeeSSN;
    private PassResultSet passResultSet = new PassResultSet();
    private Scanner scanner = new Scanner(System.in);

    public void iterateAllEmployeeQuery() throws Exception {
        System.out.printf("%12s%12s%7s%12s\n", "First Name", "Last Name", "ID", "SSN");
        System.out.println("-------------------------------------------");
        do {
            allEmployeeToString();
        } while (passResultSet.getResultSet().next());
    }

    private void allEmployeeToString() throws Exception {
        getAllEmployeeInfo();
        System.out.printf("%12s%12s%7s%12s\n", employeeFName, employeeLName, employeeID, employeeSSN);
    }

    private void getAllEmployeeInfo() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
        employeeSSN = passResultSet.getResultSet().getString("SSN");
    }

    public void iterateEmployeeInfoByIdQuery() throws Exception {
        System.out.printf("%12s%12s%7s\n", "First Name", "Last Name", "ID");
        System.out.println("-------------------------------------------");
        do {
            employeeInfoById();
        } while (passResultSet.getResultSet().next());
    }

    private void employeeInfoById() throws Exception {
        getEmployeeInfoById();
        System.out.printf("%12s%12s%7s\n", employeeFName, employeeLName, employeeID);
    }

    private void getEmployeeInfoById() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
    }

    private String updateEmployeeInfo() {
        String employeeId;
        String changedData;
        String column;
        String query;

        column = checkUpdateColumn(promptUpdateOption());
        employeeId = promptEmployeeId();
        changedData = promptEmployeeData(column);

        query = "UPDATE `elray`.`employee` \n" +
                "SET `" + column + "` = \"" + changedData + "\" \n" +
                "WHERE `EmpID` = " + employeeId +";";

        return query;
    }

    private String checkUpdateColumn(String option) {
        String column = "";

        switch (option) {
            case "1":
                column = "FirstName";
                break;
            case "2":
                column = "LastName";
                break;
            case "3":
                column = "SSN";
                break;
            case "4":
                column = "EmpId";
                break;
            case "5":
                column = "PhoneNumber";
                break;
            case "6":
                column = "Email";
                break;
            default:
                System.out.println("No option chosen: quiting.");
                break;
        }
        return column;
    }

    private String promptEmployeeId() {
        System.out.print("Enter Employee ID: ");
        return scanner.next();
    }

    private String promptEmployeeData(String column) {
        System.out.print("Enter " + column + ": ");
        return scanner.next();
    }

    private String promptUpdateOption() {
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. SNN");
        System.out.println("4. Employee ID");
        System.out.println("5. Phone Number");
        System.out.println("6. Email");
        System.out.print("Select data to update: ");

        return scanner.next();
    }

    public String getUpdateQuery() {
        return updateEmployeeInfo();
    }
}

