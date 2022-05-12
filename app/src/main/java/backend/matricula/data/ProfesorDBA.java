package backend.matricula.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

import oracle.jdbc.internal.OracleTypes;
public class ProfesorDBA {
    private static ProfesorDBA instance= null;
   

    public static ProfesorDBA getInstance() {
        if (instance == null) instance = new ProfesorDBA();
        return instance;
    }

    public String RegistrarProfesores(String id,String nombre,String telefono,String email)  {
       
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarProfesor(?,?,?,?)")) {      
                 query.setString(1,id);
                 query.setString(2,nombre);
                 query.setString(3,telefono);
                 query.setString(4,email);
                 query.execute();
                 query.close();
                 return "Ok";
              }   
        } catch (SQLException e) {
            return e.getMessage();
        } 
           
    }
    public JSONObject buscarProfesorPorId(String id) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarProfesorPorId( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, id);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var object = new JSONObject();
            if (rs.next()) {
                object.put("Id", rs.getString("Id"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Telefono", rs.getString("Telefono"));
                object.put("Email", rs.getString("Email"));
            }
            rs.close();
            query.close();
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public JSONObject buscarProfesorPorNombre(String nombre) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarProfesorPorNombre( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, nombre);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var object = new JSONObject();
            if (rs.next()) {
                object.put("Id", rs.getString("Id"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Telefono", rs.getString("Telefono"));
                object.put("Email", rs.getString("Email"));
            }
            rs.close();
            query.close();
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

}
