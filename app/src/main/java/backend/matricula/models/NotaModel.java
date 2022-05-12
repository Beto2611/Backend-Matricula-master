package backend.matricula.models;

import java.sql.SQLException;

import org.json.JSONArray;
// import org.json.JSONObject;

import backend.matricula.data.NotaDBA;

public class NotaModel {
    private static NotaModel instance = null;
    private NotaDBA dba;


    public NotaModel(){
        this.dba = NotaDBA.getInstance();
    }


    public static NotaModel getInstance(){
        if(instance == null) instance = new NotaModel();
        return instance;
    }

    public void create(Integer idCiclo,String codigoCurso,String idProfesor,String idAlumno,Integer nota){
        this.dba.insertarNota(idCiclo, codigoCurso, idProfesor, idAlumno, nota);
    }
    
    public JSONArray getHistorialPorIdYCiclo(String idAlumno, String idCiclo) throws SQLException{
        return this.dba.buscarHistorialPorIdYCiclo(idAlumno,idCiclo);
    }
    public JSONArray getHistorialPorId(String idAlumno) throws SQLException{
        return this.dba.buscarHistorialPorId(idAlumno);
    }
    public void update(String cod,Integer nota,String idAlumno){
        this.dba.updateNota(cod, nota, idAlumno);
    }
}
