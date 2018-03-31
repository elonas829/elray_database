import java.sql.*;


public class Main {

    public static void main(String[] args) {

        // Load database driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.err.println("Error when loading DB driver:");
            System.err.println(e);
        }

        // Establish database connection
        Connection conn = null;

        try {
            final String serverName = "localhost";
            final String mydatabase = "Elray";
            final String url = "jdbc:mysql://localhost/Elray?autoReconnect=true&useSSL=false";
            final String username = "chronic";
            final String password = "Magickey20";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("SQL error" + e.getErrorCode());
        }

        if (conn != null) {
            try {
                PreparedStatement employees = conn.prepareStatement(
                    "SELECT employeeFName, employeeLName, employeeID "
                            + "FROM employee "
                            + "WHERE employeeid = ?");

                ResultSet g;
                String employeeFName, employeeLName, employeeID;


                final String getEmployeeID = "0";

                employees.setString(1, getEmployeeID);

                g = employees.executeQuery();

                if(g.next()) {
                    employeeFName = g.getString("employeefname");
                    employeeLName = g.getString("employeelname");
                    employeeID = g.getString("employeeid");

                    System.out.println("Employee: " + employeeFName + " " + employeeLName);
                    System.out.println("Employee ID: " + employeeID);
                }

            } catch (SQLException e) {
                System.err.println("SQL exception: " + e.getMessage());
                System.err.println("SQL state: " + e.getSQLState());
                System.err.println("Error code: " + e.getErrorCode());
            }




        }




    }
}
