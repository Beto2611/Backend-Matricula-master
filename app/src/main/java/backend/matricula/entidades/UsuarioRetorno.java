package backend.matricula.entidades;

public class UsuarioRetorno {
    private String nombre;
    private String rol;

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setRol(String rol){
        this.rol = rol;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getRol(){
        return this.rol;
    }
}
