package backend.matricula.restAPI;

import jakarta.ws.rs.Produces;

import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.entidades.Ciclo;
import backend.matricula.entidades.CicloRetorno;
import backend.matricula.models.CicloModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ciclo")
public class cicloResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleInsertarCiclo(Ciclo cicle)
    {
        String valorRetorno = CicloModel.getInstance().create(cicle.getAnnio(), cicle.getNumeroCiclo(),
        cicle.getTitulo(), cicle.getFechaInicio(), cicle.getFechaFin());
        try {
            return Response
            .ok(valorRetorno)
            .status(200)
            .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response
            .status(404)
            .build();
        }
       
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/annio/{year}")
    public ArrayList<Ciclo> handleGetCicloPorAnnio(@PathParam("year") int annio)
    {
        try 
        {
            ArrayList<Ciclo> valorRetorno =  CicloModel.getInstance().getCicloPorAnnio(annio);
            return valorRetorno;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todos")
    public ArrayList<CicloRetorno> handleGetAllCiclos()
    {
        try 
        {
            ArrayList<CicloRetorno> valorRetorno =  CicloModel.getInstance().getAllCiclos();
            return valorRetorno;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }
}
