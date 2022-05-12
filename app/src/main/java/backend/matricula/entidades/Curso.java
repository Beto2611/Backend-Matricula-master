package backend.matricula.entidades;

public class Curso {
    private String codigo;
    private String nombre;
    private String codigoCarrera;
    private int creditos;
    private int horasPorSemana;

     public Curso(String codigo, String nombre, int creditos, int horasPorSemana) {
     this.codigo = codigo;
     this.nombre = nombre;
     this.creditos = creditos;
     this.horasPorSemana = horasPorSemana;
     }
     public Curso() {
     }
     public void setCodigo(String codigo) {
         this.codigo = codigo;
     }
     public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }
     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
     public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public void setHorasPorSemana(int horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }
     public String getCodigo() {
        return codigo;
    }
    public String getCodigoCarrera() {
        return this.codigoCarrera;
    }
     public String getNombre() {
        return nombre;
    }
     public int getCreditos() {
         return creditos;
     }
     public int getHorasPorSemana() {
         return horasPorSemana;
     }
   
     @Override
     public String toString() {
         return "curso [codigo=" + codigo + ", creditos=" + creditos + ", horasPorSemana=" + horasPorSemana + ", nombre="
                 + nombre + "]";
     }
    
     
 
 }
 