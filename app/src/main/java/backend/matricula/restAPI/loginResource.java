package backend.matricula.restAPI;

import backend.matricula.entidades.Usuario;
import backend.matricula.entidades.UsuarioRetorno;
import backend.matricula.models.UserModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class loginResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sesion")
    public Response handleLoginRequest(Usuario user)
    {
        UsuarioRetorno usuarioRetornado;
        try 
        {
            usuarioRetornado = UserModel.getInstance().login(user.getNombre(), user.getPassword());
            return Response
            .ok()
            .entity(usuarioRetornado)
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