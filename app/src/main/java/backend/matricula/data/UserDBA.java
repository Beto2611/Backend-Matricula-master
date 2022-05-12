package backend.matricula.data;

import java.sql.Connection;
import java.sql.ResultSet;
//  import java.sql.ResultSet;
 import java.sql.SQLException;
//  import java.sql.Statement;


import backend.matricula.entidades.UsuarioRetorno;
import oracle.jdbc.internal.OracleTypes;

public class UserDBA {
    private static UserDBA instance= null;
    

    public static UserDBA getInstance() {
        if (instance == null) instance = new UserDBA();
        return instance;
    }

    public void registerUsers(String nombre,String Password,String rol)  {
       
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarUsuario(?,?,?)")) {      
                 query.setString(1,nombre);
                 query.setString(2,Password);
                 query.setString(3,rol);
                 query.execute();
                 query.close();
              }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
           
    }
    public void registerStudent(String id, String nombre,String Password,String rol,String telefono,String email,String fechaNacimiento,String codigoCarrera)  {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarAlumno(?,?,?,?,?,?,?)")) {      
                query.setString(1,id);
                 query.setString(2,nombre);
                 query.setString(3,telefono);
                 query.setString(4,email);
                 query.setString(5,fechaNacimiento);
                 query.setString(6,codigoCarrera);
                 query.setString(7,Password);
                 query.execute();
                 query.close();
              }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
           
    }

    public void registerProfessor(String id, String nombre,String Password,String rol,String telefono,String email)  {
        try {
            Connection connection = databaseconnection.getInstance().getConnection();
            try (var query = connection.prepareStatement("call insertarProfesor(?,?,?,?,?)")) {      
                query.setString(1,id);
                 query.setString(2,nombre);
                 query.setString(3,telefono);
                 query.setString(4,email);
                 query.setString(5,Password);
                 query.execute();
                 query.close();
              }   
        } catch (SQLException e) {
            e.printStackTrace();
        } 
           
    }

    public UsuarioRetorno loginCliente(String user, String password) throws Exception{

        try 
        {
            Connection connection = databaseconnection.getInstance().getConnection();
            var query = connection.prepareCall("{ ? = call login( ?,? ) }");
            query.registerOutParameter(1, OracleTypes.CURSOR);
            query.setString(2, user);
            query.setString(3, password);
            query.execute();
            var rs = (ResultSet) query.getObject(1);
            UsuarioRetorno usuarioRetornado = new UsuarioRetorno();
            if (rs.next()) {
                usuarioRetornado.setNombre( rs.getString("NombreUsuario"));
                usuarioRetornado.setRol( rs.getString("Rol"));
            }
            rs.close();
            query.close();
            return usuarioRetornado;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}