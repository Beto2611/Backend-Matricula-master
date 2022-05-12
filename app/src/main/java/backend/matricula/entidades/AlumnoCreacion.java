package backend.matricula.entidades;

public class AlumnoCreacion {
    private String id;
    private String nombre;
    private String password;
    private String rol;
    private String telefono;
    private String email;
    private String fechaNacimiento;
    private String codigoCarrera;


    public AlumnoCreacion(){}

    public void setId(String id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setRol(String rol){
        this.rol = rol;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setCodigoCarrera(String codigoCarrera){
        this.codigoCarrera = codigoCarrera;
    }

    public String getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRol(){
        return this.rol;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public String getEmail(){
        return this.email;
    }
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public String getCodigoCarrera(){
        return this.codigoCarrera;
    }

}
