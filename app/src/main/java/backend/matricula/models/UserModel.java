package backend.matricula.models;

import java.sql.SQLException;

import backend.matricula.data.UserDBA;
import backend.matricula.entidades.UsuarioRetorno;

public class UserModel {
    private static UserModel instance= null;

    private final UserDBA dba;

    private UserModel() {
        this.dba = UserDBA.getInstance();
    }

    public static UserModel getInstance(){
        if(instance == null) instance =  new UserModel();
        return instance;
    }

    public void create(String nombre,String Password,String rol)throws SQLException{
       this.dba.registerUsers(nombre, Password, rol);
    }

    public void createStudent(String id, String nombre,String Password,String rol,String telefono,String email,String fechaNacimiento,String codigoCarrera)throws SQLException{
        this.dba.registerStudent(id, nombre, Password, rol, telefono, email, fechaNacimiento, codigoCarrera);
     }
     public void createProfessor(String id, String nombre,String Password,String rol,String telefono,String email)throws SQLException{
        this.dba.registerProfessor(id, nombre, Password, rol, telefono, email);
     }
    public UsuarioRetorno login(String nombre,String Password)throws Exception{
        UsuarioRetorno respuesta =  this.dba.loginCliente(nombre, Password);
        return respuesta;
     }
}
