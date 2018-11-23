import java.sql.ResultSet;

public class Assignment {

    private String employeeFName, employeeLName, employeeID, assignmentID;


    void iterateAssignmentQuery(ResultSet resultSet) throws Exception {
        do {
            assignmentID = resultSet.getString("ProjectID");
            employeeID = resultSet.getString("EmpId");
            employeeFName = resultSet.getString("FirstName");
            employeeLName = resultSet.getString("LastName");
            System.out.println("Employee: " + employeeFName + " " + employeeLName + " " + employeeID);
            System.out.println("Assignment ID: " + assignmentID);
            System.out.println("---------------------------------------------------------");
        } while (resultSet.next());
    }

    String assignmentQueryString() {
        return "SELECT `assignment`.`ProjectID`, " +
                " `assignment`.`EmpID`, " +
                " `employee`.`FirstName`, " +
                " `employee`.`LastName`  " +
                "FROM `elray`.`assignment` " +
                "INNER JOIN `employee` ON `assignment`.`EmpID`=`employee`.`EmpID`; ";
    }
}
