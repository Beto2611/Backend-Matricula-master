package backend.matricula.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
// import org.json.JSONArray;
import org.json.JSONObject;

import oracle.jdbc.OracleTypes;

public class GrupoDBA {
    private static GrupoDBA instance= null;

    public static GrupoDBA getInstance(){
        if(instance == null) instance= new GrupoDBA();
        return instance;
    }
    public  String insertarGrupo(String profesor,Integer idCiclo,String codigoCurso,String horaInicio,String horaFinal){

        try {
            Connection connection = databaseconnection.getInstance().getConnection();

         var query =  connection.prepareStatement("call insertarGrupo(?,?,?,?,?)");
         query.setString(1, profesor);
         query.setInt(2, idCiclo);
         query.setString(3, codigoCurso);
         query.setString(4, horaInicio);
         query.setString(5, horaFinal);
         query.execute();
         query.close();
         return "Ok";


        } catch (SQLException e) {
           return "Fail";
        }

    }
    public  String matricular(String idAlumno,String NombreAlumno,int IdGrupo){

        try {
            Connection connection = databaseconnection.getInstance().getConnection();

         var query =  connection.prepareStatement("call Matricular(?,?,?)");
         query.setString(1, idAlumno);
         query.setString(2, NombreAlumno);
         query.setInt(3, IdGrupo);
         query.execute();
         query.close();
         return "Ok";


        } catch (SQLException e) {
           return "Fail";
        }

    }
    public JSONArray buscarGrupoPorCodigoCurso(String codigoCurso){
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarGrupoPorCodigoCurso(?) } ");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigoCurso);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            
            var result = new JSONArray();
            while(rs.next()){
                var object = new JSONObject();
                object.put("Id", rs.getInt("Id"));
                object.put("IdProfesor", rs.getString("IdProfesor"));
                object.put("IdCiclo", rs.getInt("IdCiclo"));
                object.put("CodigoCurso", rs.getString("CodigoCurso"));
                object.put("NumeroGrupo", rs.getInt("NumeroGrupo"));
                object.put("HoraInicio", rs.getString("HoraInicio"));
                object.put("HoraFin", rs.getString("HoraFin"));
                result.put(object);
            }
            return result;
        } catch (SQLException e) {
           return null;
        }
   
    }

    public JSONArray buscarGrupoPorCarrera(String codigoCarrera){
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarGrupoPorCarrera(?) } ");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigoCarrera);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            
            var result = new JSONArray();
            while(rs.next()){
                var object = new JSONObject();
                object.put("Id", rs.getInt("Id"));
                object.put("IdProfesor", rs.getString("IdProfesor"));
                object.put("IdCiclo", rs.getInt("IdCiclo"));
                object.put("CodigoCurso", rs.getString("CodigoCurso"));
                object.put("NumeroGrupo", rs.getInt("NumeroGrupo"));
                object.put("HoraInicio", rs.getString("HoraInicio"));
                object.put("HoraFin", rs.getString("HoraFin"));
                result.put(object);
            }
            return result;
        } catch (SQLException e) {
           return null;
        }
   
    }
    public JSONArray buscarAlumnosPorIdGrupoNotas(Integer idGrupo){
        try{
            Connection connection= databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarAlumnosPorIdGrupoNotas(?) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setInt(2, idGrupo);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var result = new JSONArray();
            while(rs.next()){
                var object = new JSONObject();
                object.put("IdAlumno", rs.getString("IdAlumno"));
                object.put("NombreAlumno", rs.getString("NombreAlumno"));
                object.put("Nota", rs.getInt("Nota"));

                result.put(object);
            }
            return result;
        } catch (SQLException e) {
                return null;
        }
    }

    public JSONArray buscarGrupoPorIdProfesor(String idProfesor){
        try{
            Connection connection= databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarGrupoPorIdProfesor(?) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, idProfesor);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            var result = new JSONArray();
            while(rs.next()){
                var object = new JSONObject();
                object.put("Id", rs.getInt("Id"));
                object.put("IdCiclo", rs.getString("IdCiclo"));
                object.put("codigoCurso", rs.getString("codigoCurso"));
                result.put(object);
            }
            return result;
        } catch (SQLException e) {
                return null;
        }
    }
    
}
