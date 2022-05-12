package backend.matricula.models;

import java.sql.SQLException;

import org.json.JSONObject;

import backend.matricula.data.ProfesorDBA;

public class ProfesorModel {
    
    private static ProfesorModel instance = null;
    private ProfesorDBA dba;


    public ProfesorModel(){
        this.dba = ProfesorDBA.getInstance();
    }


    public static ProfesorModel getInstance(){
        if(instance == null) instance = new ProfesorModel();
        return instance;
    }

    public void create(String id,String nombre,String telefono,String email){
        this.dba.RegistrarProfesores(id, nombre, telefono, email);
    }
    public JSONObject getProfesorPorId(String id) throws SQLException{
        return this.dba.buscarProfesorPorId(id);
    }
    public JSONObject getProfesorPorNombre(String nombre) throws SQLException{
        return this.dba.buscarProfesorPorNombre(nombre);
    }
}
