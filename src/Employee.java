import java.sql.ResultSet;
import java.util.Scanner;

public class Employee {

    private String employeeFName;
    private String employeeLName;
    private String employeeID;

    private Scanner scanner = new Scanner(System.in);

    void iterateAllEmployeeQuery(ResultSet resultSet) throws Exception {
        System.out.printf("%12s%12s%7s%12s\n", "First Name", "Last Name", "ID", "SSN");
        System.out.println("-------------------------------------------");
        do {
            employeeFName = resultSet.getString("first_name");
            employeeLName = resultSet.getString("last_name");
            employeeID = resultSet.getString("emp_id");
            String employeeSSN = resultSet.getString("SSN");
            System.out.printf("%12s%12s%7s%12s\n", employeeFName, employeeLName, employeeID, employeeSSN);
        } while (resultSet.next());
    }

    void iterateEmployeeInfoByIdQuery(ResultSet resultSet) throws Exception {
        System.out.printf("%12s%12s%7s\n", "First Name", "Last Name", "ID");
        System.out.println("-------------------------------------------");
        do {
            employeeFName = resultSet.getString("FirstName");
            employeeLName = resultSet.getString("LastName");
            employeeID = resultSet.getString("EmpId");
            System.out.printf("%12s%12s%7s\n", employeeFName, employeeLName, employeeID);
        } while (resultSet.next());
    }

    String updateEmployeeInfo() {
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. SNN");
        System.out.println("4. Phone Number");
        System.out.println("5. Email");
        System.out.print("Select data to update: ");

        String option = scanner.next();

        switch (option) {
            case "1":
                option = "first_name";
                break;
            case "2":
                option = "last_name";
                break;
            case "3":
                option = "SSN";
                break;
            case "4":
                option = "PhoneNumber";
                break;
            case "5":
                option = "Email";
                break;
            default:
                System.out.println("No option chosen: quiting.");
                break;
        }

        String employeeId = promptEmployeeId();
        String changedData = promptEmployeeData(option);

        return "UPDATE `elray`.`employee` \n" +
                "SET `" + option + "` = \"" + changedData + "\" \n" +
                "WHERE `emp_id` = " + employeeId +";";
    }


    private String promptEmployeeId() {
        System.out.print("Enter Employee ID: ");
        return scanner.next();
    }

    private String promptEmployeeData(String column) {
        System.out.print("Enter " + column + ": ");
        return scanner.next();
    }


    String infoByIdQueryString() {
        return "SELECT first_name, last_name, emp_id"
                + "FROM `elray`.`employee` "
                + "WHERE EmpID = " + promptEmployeeId();
    }

    String employeeQueryString() {
        return "SELECT  * FROM employee ";
    }


}

