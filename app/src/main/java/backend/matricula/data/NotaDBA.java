package backend.matricula.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import oracle.jdbc.internal.OracleTypes;


public class NotaDBA {
    private static NotaDBA instance= null;

    public static NotaDBA getInstance(){
        if(instance == null) instance= new NotaDBA();
        return instance;
    }
    public String insertarNota(Integer idCiclo,String codigoCurso,String idProfesor,String idAlumno,Integer nota){

        try {
            Connection connection = databaseconnection.getInstance().getConnection();

         var query =  connection.prepareStatement("call insertarNota(?,?,?,?,?)");
         query.setInt(1, idCiclo);
         query.setString(2, codigoCurso);
         query.setString(3, idProfesor);
         query.setString(4, idAlumno);
         query.setInt(5, nota);
         query.execute();
         query.close();
         return "Ok";
        } catch (SQLException e) {
           return e.getMessage();
        }

    }
    public void updateNota(String cod,Integer nota,String id)  {
       
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call actualizarNotas(?,?,?)")) {      
                 query.setString(1,cod);
                 query.setInt(2,nota);
                 query.setString(3,id);
                 
                 query.execute();
                 query.close();
              }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
      
    }
    public JSONArray buscarHistorialPorIdYCiclo(String idAlumno, String idCiclo) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarHistorialAlumnoPorCiclo( ?,? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, idAlumno);
            query.setString(3, idCiclo);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var result = new JSONArray();
            while (rs.next()) {
                var object = new JSONObject();
                object.put("CodigoCurso", rs.getString("CodigoCurso"));
                object.put("IdProfesor", rs.getString("IdProfesor"));
                object.put("Nombre", rs.getString("Nombre"));
                object.put("Nota", rs.getInt("Nota"));
                result.put(object);
            }
            rs.close();
            query.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public JSONArray buscarHistorialPorId(String idAlumno) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarHistorialAlumnoPorId(?) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, idAlumno);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var result = new JSONArray();
            while (rs.next()) {
                var object = new JSONObject();
                object.put("CodigoCurso", rs.getString("CodigoCurso"));
                object.put("IdProfesor", rs.getString("IdProfesor"));
                object.put("Nombre", rs.getString("IdCiclo"));
                object.put("Nota", rs.getInt("Nota"));
                result.put(object);
            }
            rs.close();
            query.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
