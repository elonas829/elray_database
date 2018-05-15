import java.sql.ResultSet;

public class PassResultSet {

    private static ResultSet resultSet = null;

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet result) {
        resultSet = result;
    }
}
