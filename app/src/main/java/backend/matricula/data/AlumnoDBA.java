package backend.matricula.data;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.json.JSONObject;

import oracle.jdbc.OracleTypes;

public class AlumnoDBA {
    
    private static AlumnoDBA instance= null;
   

    public static AlumnoDBA getInstance() {
        if (instance == null) instance = new AlumnoDBA();
        return instance;
    }

    public void RegistrarAlumno(String id,String nombre,String telefono,String email,String fechaNacimiento,String CodigoCarrera)  {
       
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarAlumno(?,?,?,?,?,?)")) {      
                 query.setString(1,id);
                 query.setString(2,nombre);
                 query.setString(3,telefono);
                 query.setString(4,email);
                 query.setString(5,fechaNacimiento);
                 query.setString(6,CodigoCarrera);
                 query.execute();
                 query.close();
              }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
           
    }
    public JSONObject buscarAlumnoPorNombre(String nombre) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarAlumnoPorNombre( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, nombre);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var object = new JSONObject();
            if (rs.next()) {
                object.put("Id", rs.getString("Id"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Telefono", rs.getInt("Telefono"));
                object.put("Email", rs.getInt("Email"));
                object.put("FechaNacimiento", rs.getDate("FechaNacimiento"));
                object.put("CodigoCarrera", rs.getString("CodigoCarrera"));
            }
            rs.close();
            query.close();
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public JSONObject buscarAlumnoPorId(String id) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarAlumnoPorId( ? ) }");
            query.registerOutParameter(1,  OracleTypes.CURSOR);
            query.setString(2, id);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var object = new JSONObject();
            if (rs.next()) {
                
                object.put("Id", rs.getString("Id"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Telefono", rs.getString("Telefono"));
                object.put("Email", rs.getString("Email"));
                object.put("FechaNacimiento", rs.getDate("FechaNacimiento"));
                object.put("CodigoCarrera", rs.getString("CodigoCarrera"));
            }
            rs.close();
            query.close();
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public JSONObject buscarAlumnoPorCarrera(String codigoCarrera) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarAlumnoPorCarrera( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigoCarrera);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var object = new JSONObject();
            if (rs.next()) {
                object.put("Id", rs.getString("Id"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Telefono", rs.getInt("Telefono"));
                object.put("Email", rs.getInt("Email"));
                object.put("FechaNacimiento", rs.getDate("FechaNacimiento"));
                object.put("CodigoCarrera", rs.getString("CodigoCarrera"));
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
