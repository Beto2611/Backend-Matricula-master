package backend.matricula.models;

import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.data.CicloDBA;
import backend.matricula.entidades.Ciclo;
import backend.matricula.entidades.CicloRetorno;

public class CicloModel {
    private static CicloModel instance = null;
    private final CicloDBA dba;



    public CicloModel() {
        this.dba = CicloDBA.getInstance();
    }
    public static CicloModel getInstance(){
        if(instance==null) instance= new CicloModel(); 
        return instance;
    } 

    public String create(Integer annio,Integer NumeroCiclo,String titulo,String fechaInicio,String fechaFinal){
        String valorRetorno = this.dba.insertarCiclo(annio, NumeroCiclo, titulo, fechaInicio, fechaFinal);
        return valorRetorno;
    }

    public ArrayList<Ciclo> getCicloPorAnnio(int annio) throws SQLException{
        ArrayList<Ciclo> valorRetorno = this.dba.buscarCicloPorAnnio(annio);
        return valorRetorno;
    }

    public ArrayList<CicloRetorno> getAllCiclos() throws SQLException{
        ArrayList<CicloRetorno> valorRetorno = this.dba.getAllCiclos();
        return valorRetorno;
    }

}
