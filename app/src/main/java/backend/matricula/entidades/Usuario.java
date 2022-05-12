package backend.matricula.entidades;

public class Usuario {
    private String nombre;
    private String password;

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getPassword(){
        return this.password;
    }
}
