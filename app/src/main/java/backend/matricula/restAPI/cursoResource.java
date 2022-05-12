package backend.matricula.restAPI;

import jakarta.ws.rs.Produces;
import java.sql.SQLException;
import java.util.ArrayList;

import backend.matricula.entidades.Curso;
import backend.matricula.models.CursoModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/curso")
public class cursoResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleInsertarCurso(Curso course)
    {
        String valorRetorno = CursoModel.getInstance().create(course.getCodigoCarrera(),course.getCodigo(), course.getNombre(), 
        course.getCreditos(), course.getHorasPorSemana());
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
    @Path("/nombre/{name}")
    public Curso handleGetCursoPorNombre(@PathParam("name") String nombre)
    {
        try 
        {
            Curso valorCurso = CursoModel.getInstance().getBuscarNombreCurso(nombre);
            return valorCurso;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codigo/{code}")
    public Curso handlegetCarreraPorCodigo(@PathParam("code") String code)
    {
        try 
        {
            Curso valorCurso = CursoModel.getInstance().getBuscarNombreCodigo(code);
            return valorCurso;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codigoCarrera/{code}")
    public ArrayList<Curso> handleGetCursosPorCarrera(@PathParam("code") String code)
    {
        try 
        {
            ArrayList<Curso> valorCursos = CursoModel.getInstance().getCursosPorCarrera(code);
            return valorCursos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }       
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/borrarCurso/{code}")
    public Response handleBorrarCurso(@PathParam("code") String code){
        try 
        {
            String valorRetorno = CursoModel.getInstance().delete(code);
            return Response
            .ok(valorRetorno)
            .status(200)
            .build();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            return Response
            .status(404)
            .build();
        }       
    }

}
