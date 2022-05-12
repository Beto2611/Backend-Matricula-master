package backend.matricula.models;

import java.sql.SQLException;

import org.json.JSONArray;

import backend.matricula.data.GrupoDBA;

public class GrupoModel {
    private static GrupoModel instance = null;
    private final GrupoDBA dba;



    public GrupoModel() {
        this.dba = GrupoDBA.getInstance();
    }
    public static GrupoModel getInstance(){
        if(instance==null) instance= new GrupoModel(); 
        return instance;
    } 

    public void create(String profesor,Integer idCiclo,String codigoCurso,String horaInicio,String horaFinal){
        this.dba.insertarGrupo(profesor, idCiclo,codigoCurso, horaInicio, horaFinal);
    }

    public void matricular(String idAlumno,String NombreAlumno,int IdGrupo){
        this.dba.matricular(idAlumno, NombreAlumno, IdGrupo);
    }
    public JSONArray getGruposPorCodigo(String codigo) throws SQLException{
       return this.dba.buscarGrupoPorCodigoCurso(codigo);
    }
    public JSONArray getGruposPorCarrera(String codigo) throws SQLException{
        return this.dba.buscarGrupoPorCarrera(codigo);
     }
    public JSONArray buscarAlumnosPorIdGrupoNotas(Integer idGrupo){
        return this.dba.buscarAlumnosPorIdGrupoNotas(idGrupo);
    }
    public JSONArray buscarGrupoPorIdProfesor(String idProfesor){
        return this.dba.buscarGrupoPorIdProfesor(idProfesor);
    }

}
