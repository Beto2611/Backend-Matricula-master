package backend.matricula.data;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import backend.matricula.entidades.Curso;
import oracle.jdbc.OracleTypes;

import java.sql.ResultSet;

public class CursoDBA {
    private static CursoDBA instance= null;
   

    public static CursoDBA getInstance() {
        if (instance == null) instance = new CursoDBA();
        return instance;
    }

    public String RegistrarCurso(String codigoCarrera, String codigoCurso,String nombre,int creditos,int horaSemanales)  {
       
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarCurso(?,?,?,?,?)")) {   
                query.setString(1,codigoCarrera);   
                 query.setString(2,codigoCurso);
                 query.setString(3,nombre);
                 query.setInt(4,creditos);
                 query.setInt(5,horaSemanales);
                 query.execute();
                 query.close();
                 return "Ok";
              }   
        } catch (SQLException e) {
            return e.getMessage();
        } 
           
    }
    public Curso buscarPorNombreCurso(String nombre) throws SQLException {
        try 
        {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCursoPorNombre( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, nombre);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            Curso cu = null;  
            if (rs.next()) 
            {
                cu = new Curso();
                cu.setCodigo(rs.getString("CodigoCurso"));
                cu.setNombre(rs.getString("Nombre"));
                cu.setCreditos(rs.getInt("Creditos"));
                cu.setHorasPorSemana(rs.getInt("HorasPorSemana"));
            }
            rs.close();
            query.close();
            return cu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }      
    }
    public Curso buscarCursoPorCodigo(String codigo) throws SQLException {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCursoPorCodigo( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigo);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            Curso cu = null;  
            if (rs.next()) {
                cu = new Curso();
                cu.setCodigo(rs.getString("CodigoCurso"));
                cu.setNombre(rs.getString("Nombre"));
                cu.setCreditos(rs.getInt("Creditos"));
                cu.setHorasPorSemana(rs.getInt("HorasPorSemana"));
                rs.close();
                query.close();
                return cu;
            }
            rs.close();
            query.close();
            return cu;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public ArrayList<Curso> buscarCursosPorCarrera(String codigo) throws SQLException {    
        ArrayList<Curso> cursos = new ArrayList<Curso>();
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCursosPorCarrera( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, codigo);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            while(rs.next()) {
                Curso cu = new Curso();
                cu.setCodigo(rs.getString("CodigoCurso"));
                cu.setNombre(rs.getString("Nombre"));
                cu.setCreditos(rs.getInt("Creditos"));
                cu.setHorasPorSemana(rs.getInt("HorasPorSemana"));
                cursos.add(cu);
            }
            rs.close();
            query.close();
            return cursos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public String borrarCurso(String codigoCurso)  {   
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call borrarCurso(?)")) {      
                 query.setString(1,codigoCurso);
                 query.execute();
                 query.close();
                 return "Ok";
              }   
        } catch (SQLException e) {
            return e.getMessage();
        } 
           
    }

    }

