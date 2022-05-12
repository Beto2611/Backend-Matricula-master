package backend.matricula.entidades;

public class ProfesorCreacion {
    private String id;
    private String nombre;
    private String password;
    private String rol;
    private String telefono;
    private String email;

    public void setId(String id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setRol(String rol){
        this.rol = rol;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getRol(){
        return this.rol;
    }
    public String getPassword(){
        return this.password;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public String getEmail(){
        return this.email;
    }
    public String getId(){
        return this.id;
    }


}
