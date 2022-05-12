package backend.matricula.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.entidades.Ciclo;
import backend.matricula.entidades.CicloRetorno;
import oracle.jdbc.OracleTypes;



public class CicloDBA {
    private static CicloDBA instance= null;

    public static CicloDBA getInstance(){
        if(instance == null) instance= new CicloDBA();
        return instance;
    }
    public String insertarCiclo(Integer annio,Integer NumeroCiclo,String titulo,String fechaInicio,String fechaFinal){

        try {
        Connection connection = databaseconnection.getInstance().getConnection();
         var query =  connection.prepareStatement("call insertarCiclo(?,?,?,?,?)");
         query.setInt(1, annio);
         query.setInt(2, NumeroCiclo);
         query.setString(3, titulo);
         query.setString(4, fechaInicio);
         query.setString(5, fechaFinal);
         query.execute();
         query.close();
         return "Ok";
        } catch (SQLException e) {
            return e.getMessage();
        }

    }
    public  ArrayList<Ciclo> buscarCicloPorAnnio(int annio) throws SQLException {
        ArrayList<Ciclo> ciclos = new ArrayList<Ciclo>();
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call buscarCicloPorAnnio( ? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setInt(2, annio);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            while(rs.next()) {
                Ciclo tempCiclo = new Ciclo(rs.getInt("Annio"), rs.getInt("NumeroCiclo"),
                rs.getString("Titulo"), rs.getDate("FechaInicio").toString(), rs.getDate("FechaFin").toString());
                ciclos.add(tempCiclo);
            }
            rs.close();
            query.close();
            return ciclos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public  ArrayList<CicloRetorno> getAllCiclos() throws SQLException {
        ArrayList<CicloRetorno> ciclos = new ArrayList<CicloRetorno>();
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call getAllCiclos() }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            while(rs.next()) {
                CicloRetorno tempCiclo = new CicloRetorno(rs.getInt("Annio"), rs.getInt("NumeroCiclo"),
                rs.getString("Titulo"), rs.getDate("FechaInicio").toString(), rs.getDate("FechaFin").toString(),rs.getInt("IdCiclo"));
                ciclos.add(tempCiclo);
            }
            rs.close();
            query.close();
            return ciclos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

}
