package backend.matricula.entidades;

public class Ciclo {
    private int annio;
    private int numeroCiclo;
    private String fechaInicio;
    private String fechaFin;
    private String titulo;
    
    public Ciclo(int annio, int numeroCiclo,String titulo ,String fechaInicio, String fechaFin) {
        this.annio = annio;
        this.numeroCiclo = numeroCiclo;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public Ciclo() {
    }
    public int getAnnio() {
        return annio;
    }
    public void setAnnio(int annio) {
        this.annio = annio;
    }
    public int getNumeroCiclo() {
        return numeroCiclo;
    }
    public void setNumeroCiclo(int numeroCiclo) {
        this.numeroCiclo = numeroCiclo;
    }
    public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Override
    public String toString() {
        return "ciclo [annio=" + annio + ", fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio + ", numeroCiclo="
                + numeroCiclo + "]";
    }
    public String getTitulo() {
        return this.titulo;
    }
    
    
}
