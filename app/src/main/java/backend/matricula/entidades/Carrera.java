package backend.matricula.entidades;

public class Carrera {
    private String codigo;
    private String nombre;
    private String titulo;

    public Carrera(String codigo, String nombre, String titulo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
    }
    public Carrera() {
    }
    public String getCodigo() {
        return this.codigo;
    }
   
    public String getNombre() {
        return this.nombre;
    }
 
    public String getTitulo() {
        return this.titulo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @Override
    public String toString() {
        return "carrera [codigo=" + codigo +", nombre=" + nombre + ", titulo=" + titulo + "]";
    }
    
}