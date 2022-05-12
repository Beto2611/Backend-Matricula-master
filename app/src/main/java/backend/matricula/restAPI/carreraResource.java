package backend.matricula.restAPI;


import jakarta.ws.rs.Produces;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.entidades.Carrera;
import backend.matricula.models.CarreraModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carrera")
public class carreraResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleInsertarCarrera(Carrera ca)
    {
        String valorRetorno = CarreraModel.getInstance().create(ca.getCodigo(), ca.getNombre(), ca.getTitulo());
       try {
        return Response
        .ok(valorRetorno)
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
    @Path("/nombre/{name}")
    public Carrera  handlegetCarreraPorNombre(@PathParam("name") String nombre)
    {
        try 
        {
            Carrera valorCarrera = CarreraModel.getInstance().getCarreraPorNombre(nombre);
            return valorCarrera;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codigo/{code}")
    public Carrera handlegetCarreraPorCodigo(@PathParam("code") String code)
    {
        try 
        {
            Carrera valorCarrera =  CarreraModel.getInstance().getCarreraPorCodigo(code);
            return valorCarrera;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAll")
    public ArrayList<Carrera> handlegetCarreraPorCodigo()
    {
        try 
        {
            ArrayList<Carrera> valorCarrera =  CarreraModel.getInstance().getAllCarreras();
            return valorCarrera;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }
}
