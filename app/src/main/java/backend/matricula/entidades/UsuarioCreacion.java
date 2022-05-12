package backend.matricula.entidades;

public class UsuarioCreacion {
    private String id;
    private String password;
    private String rol;

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    public String getId(){
        return this.id;
    }
    public String getRol(){
        return this.rol;
    }
    public String getPassword(){
        return this.password;
    }
}
