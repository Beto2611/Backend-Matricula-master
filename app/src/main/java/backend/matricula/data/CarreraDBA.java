package backend.matricula.data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

//import org.json.JSONObject;

import backend.matricula.entidades.Carrera;
import oracle.jdbc.OracleTypes;

public class CarreraDBA {

    private static CarreraDBA instance= null;

    public static CarreraDBA getInstance(){
        if(instance == null) instance= new CarreraDBA();
        return instance;
    }
    public String insertarCarrera(String codigoCarrera,String nombreCarrera,String titulo){

        try {
            Connection connection = databaseconnection.getInstance().getConnection();

         var query =  connection.prepareStatement("call insertarCarrera(?,?,?)");
         query.setString(1, codigoCarrera);
         query.setString(2, nombreCarrera);
         query.setString(3, titulo);
         query.execute();
         query.close();
         return "Ok";


        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public void deleteCourse(String codigoCurso) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            connection.setAutoCommit(true);
            var query = connection.prepareStatement("call borrarCurso( ? )");
            query.setString(1, codigoCurso);
            query.execute();
            query.close();
        } catch (Exception e) {
           
        }
        
    }
    public Carrera buscarCarreraPorCodigo(String codigo) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCarreraPorCodigo( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigo);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            //var object = new JSONObject();
            Carrera carrerita = new Carrera();
            if (rs.next()) {
                carrerita.setCodigo(rs.getString("CodigoCarrera"));
                carrerita.setNombre(rs.getString("Nombre"));
                carrerita.setTitulo(rs.getString("Titulo"));
                
            }
            rs.close();
            query.close();
            return carrerita;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public Carrera buscarCarreraPorNombre(String nombre) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCarreraPorNombre( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, nombre);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            //var object = new JSONObject();
            Carrera carrerita = new Carrera();
            if (rs.next()) {
                carrerita.setCodigo(rs.getString("CodigoCarrera"));
                carrerita.setNombre(rs.getString("Nombre"));
                carrerita.setTitulo(rs.getString("Titulo"));
            }
            rs.close();
            query.close();
            return carrerita;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public ArrayList<Carrera> getAllCarreras() throws SQLException {
        ArrayList<Carrera> carreras = new ArrayList<Carrera>();
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call getAllCarreras()}");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            //var object = new JSONObject();
            while(rs.next()) {
                Carrera carrerita = new Carrera();
                carrerita.setCodigo(rs.getString("CodigoCarrera"));
                carrerita.setNombre(rs.getString("Nombre"));
                carrerita.setTitulo(rs.getString("Titulo"));
                carreras.add(carrerita);
            }
            rs.close();
            query.close();
            return carreras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

}
