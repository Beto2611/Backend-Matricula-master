package backend.matricula.models;

import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.data.CursoDBA;
import backend.matricula.entidades.Curso;

public class CursoModel {
    private static CursoModel instance = null;
    private final CursoDBA dba;



    public CursoModel() {
        this.dba = CursoDBA.getInstance();
    }
    public static CursoModel getInstance(){
        if(instance==null) instance= new CursoModel(); 
        return instance;
    } 

    public String create(String codigoCarrera,String codigoCurso,String nombre,int creditos,int horaSemanales){
        String valorRetorno = this.dba.RegistrarCurso(codigoCarrera,codigoCurso, nombre, creditos, horaSemanales);
        return valorRetorno;
    }
    public Curso getBuscarNombreCurso(String nombre) throws SQLException{
        Curso valorRetorno = this.dba.buscarPorNombreCurso(nombre);
        return valorRetorno;

    }
    public Curso getBuscarNombreCodigo(String codigo) throws SQLException{
        Curso valorRetorno = this.dba.buscarCursoPorCodigo(codigo);
        return valorRetorno;
    }

    public ArrayList<Curso> getCursosPorCarrera(String codigo) throws SQLException{
        ArrayList<Curso> valorRetorno = this.dba.buscarCursosPorCarrera(codigo);
        return valorRetorno;
    }
    public String delete(String codigo) throws SQLException{
        String valorRetorno = this.dba.borrarCurso(codigo);
        return valorRetorno;
    }
}
