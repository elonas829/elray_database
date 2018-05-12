import java.sql.SQLException;

public class Employee {

    private String employeeFName, employeeLName, employeeID;

    private PassResultSet passResultSet = new PassResultSet();

    public void iterateEmployeeQuery() throws Exception {
        do {
            employeeToString();
        } while (passResultSet.getResultSet().next());
    }

    private void getEmployeeInfo() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
    }

    private void employeeToString() throws Exception {
        getEmployeeInfo();
        System.out.println("Employee: " + employeeFName + " " + employeeLName);
        System.out.println("Employee ID: " + employeeID);
        System.out.println("---------------------------------------------------------");
    }
}

