package backend.matricula.data;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;

public class databaseconnection {
    private static databaseconnection instance = null;

    final static String DB_URL= "jdbc:oracle:thin:@localhost:1521/XE";
    final static String DB_USER = "system";
    //final static String DB_PASSWORD = "1234";
    final static String DB_PASSWORD = "root";

    public static databaseconnection getInstance() {
        if (instance == null) instance = new databaseconnection();
        return instance;
    }


    public OracleConnection getConnection() throws SQLException {
        Properties info = new Properties();
        info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
        info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);
        info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");


        OracleDataSource ods = new OracleDataSource();
        ods.setURL(DB_URL);
        ods.setConnectionProperties(info);

        try  {

            return (OracleConnection) ods.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
