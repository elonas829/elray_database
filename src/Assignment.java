public class Assignment {

//    private String employeeFName, employeeLName, employeeID, employeeSSN;

    private String employeeFName, employeeLName, employeeID, assignmentID;

    private PassResultSet passResultSet = new PassResultSet();

    public void iterateAssignmentQuery() throws Exception {
        do {
            assignmentToString();
        } while (passResultSet.getResultSet().next());
    }

    private void getAssignmentInfo() throws Exception {
        assignmentID = passResultSet.getResultSet().getString("ProjectID");
        employeeID = passResultSet.getResultSet().getString("EmpId");
        employeeFName = passResultSet.getResultSet().getString("FirstName");
        employeeLName = passResultSet.getResultSet().getString("LastName");
    }

    private void assignmentToString() throws Exception {
        getAssignmentInfo();
        System.out.println("Employee: " + employeeFName + " " + employeeLName + " " + employeeID);
        System.out.println("Assignment ID: " + assignmentID);
        System.out.println("---------------------------------------------------------");
    }
}
