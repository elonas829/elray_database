public class Employee {

    private String employeeFName, employeeLName, employeeID, employeeSSN;
    private PassResultSet passResultSet = new PassResultSet();

    public void iterateAllEmployeeQuery() throws Exception {
        System.out.printf("%12s%12s%7s%12s\n", "First Name", "Last Name", "ID", "SSN");
        System.out.println("---------------------------------------------------------");
        do {
            allEmployeeToString();
        } while (passResultSet.getResultSet().next());
    }

    private void getAllEmployeeInfo() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
        employeeSSN = passResultSet.getResultSet().getString("SSN");
    }

    private void allEmployeeToString() throws Exception {
            getAllEmployeeInfo();
            System.out.printf("%12s%12s%7s%12s\n", employeeFName, employeeLName, employeeID, employeeSSN);
    }

    public void iterateEmployeeInfoByIdQuery() throws Exception {
        System.out.printf("%12s%12s%7s\n", "First Name", "Last Name", "ID");
        do {
            employeeInfoById();
        } while (passResultSet.getResultSet().next());
    }

    private void getEmployeeInfoById() throws Exception {
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
        employeeID = passResultSet.getResultSet().getString("EmpId");
    }

    private void employeeInfoById() throws Exception {
        getEmployeeInfoById();
        System.out.printf("%12s%12s%7s\n", employeeFName, employeeLName, employeeID);
    }
}

