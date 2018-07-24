import java.sql.ResultSet;

class PassResultSet {

    private static ResultSet resultSet = null;

    ResultSet getResultSet() {
        return resultSet;
    }

    void setResultSet(ResultSet result) {
        resultSet = result;
    }
}
