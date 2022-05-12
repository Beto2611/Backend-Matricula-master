package backend.matricula.models;

import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.data.CarreraDBA;
import backend.matricula.entidades.Carrera;

public class CarreraModel {
    private static CarreraModel instance = null;
    private final CarreraDBA dba;



    public CarreraModel() {
        this.dba = CarreraDBA.getInstance();
    }
    public static CarreraModel getInstance(){
        if(instance==null) instance= new CarreraModel(); 
        return instance;
    } 

    public String create(String codigoCarrera,String nombreCarrera,String titulo){
        String valorRetorno = this.dba.insertarCarrera(codigoCarrera, nombreCarrera, titulo);
        return valorRetorno;
    }
    public Carrera getCarreraPorNombre(String nombre) throws SQLException{
        Carrera valorRetorno = this.dba.buscarCarreraPorNombre(nombre);
        return valorRetorno;
    }
    public Carrera getCarreraPorCodigo(String codigo) throws SQLException{
        Carrera valorRetorno = this.dba.buscarCarreraPorCodigo(codigo);
        return valorRetorno;
    }
    public ArrayList<Carrera> getAllCarreras() throws SQLException{
        ArrayList<Carrera> valorRetorno = this.dba.getAllCarreras();
        return valorRetorno;
    }

    
}
