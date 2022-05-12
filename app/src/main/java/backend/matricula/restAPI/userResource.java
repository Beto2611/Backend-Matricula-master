package backend.matricula.restAPI;

import backend.matricula.entidades.UsuarioCreacion;
import backend.matricula.entidades.AlumnoCreacion;
import backend.matricula.entidades.ProfesorCreacion;
import backend.matricula.models.UserModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class userResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addEstudiante")
    public Response handleAddStudent(AlumnoCreacion user)
    {
        try 
        {
            UserModel.getInstance().createStudent(user.getId(), user.getNombre(),
            user.getPassword(), user.getRol(),user.getTelefono(),user.getEmail(),user.getFechaNacimiento(), user.getCodigoCarrera());
            return Response
            .ok("Ok")
            .build();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return Response
            .status(404)
            .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addProfesor")
    public Response handleAddProfessor(ProfesorCreacion user)
    {
        try 
        {
            UserModel.getInstance().createProfessor(user.getId(), user.getNombre(),
            user.getPassword(), user.getRol(),user.getTelefono(),user.getEmail());
            return Response
            .ok("Ok")
            .build();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return Response
            .status(404)
            .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response handleAddUser(UsuarioCreacion user)
    {
        try 
        {
            UserModel.getInstance().create(user.getId(),
            user.getPassword(), user.getRol());
            return Response
            .ok("Ok")
            .build();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return Response
            .status(404)
            .build();
        }
    }

}