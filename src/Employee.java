import java.sql.SQLException;

public class Employee {

    private String employeeFName, employeeLName, employeeID, employeeSSN;

    private PassResultSet passResultSet = new PassResultSet();

    public void iterateEmployeeQuery() throws Exception {
        getEmployeeInfo();
        do {
            employeeToString();
        } while (passResultSet.getResultSet().next());
    }

    private void getEmployeeInfo() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
        employeeSSN = passResultSet.getResultSet().getString("SSN");
    }

    private void employeeToString() {
        System.out.println("Employee: " + employeeFName + " " + employeeLName);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Employee SSN: " + employeeSSN);
        System.out.println("---------------------------------------------------------");
    }
}

