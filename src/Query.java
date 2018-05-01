import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Query  {

    private void logSQLError(SQLException e) {
        System.err.println("SQL exception: " + e.getMessage());
        System.err.println("SQL state: " + e.getSQLState());
        System.err.println("Error code: " + e.getErrorCode());
    }
}
