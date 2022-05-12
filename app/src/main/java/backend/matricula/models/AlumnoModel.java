package backend.matricula.models;

//import java.sql.Date;
import java.sql.SQLException;

import org.json.JSONObject;

import backend.matricula.data.AlumnoDBA;


public class AlumnoModel {
    private static AlumnoModel instance = null;
    private final AlumnoDBA dba;



    public AlumnoModel() {
        this.dba = AlumnoDBA.getInstance();
    }
    public static AlumnoModel getInstance(){
        if(instance==null) instance= new AlumnoModel(); 
        return instance;
    } 

    public void create(String id,String nombre,String telefono,String email,String fechaNacimiento,String CodigoCarrera){
        
        this.dba.RegistrarAlumno(id, nombre, telefono, email, fechaNacimiento, CodigoCarrera);
    }
    public JSONObject getAlumnoPorNombre(String nombre) throws SQLException{
        return this.dba.buscarAlumnoPorNombre(nombre);
    }

    public JSONObject getAlumnoPorId(String id) throws SQLException{
        return this.dba.buscarAlumnoPorId(id);
    }

    public void getAlumnoPorCarrera(String Idcarrera) throws SQLException{
        this.dba.buscarAlumnoPorCarrera(Idcarrera);
    }
    
    
}
