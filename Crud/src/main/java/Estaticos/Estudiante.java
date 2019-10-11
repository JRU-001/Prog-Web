package Estaticos;

public class Estudiante {
    private int matricula;
    private String nombre;
    private String apellido;
    private String telefono;

    public Estudiante(int mat, String name, String appel, String telefono){
        this.matricula=mat;
        this.nombre=name;
        this.apellido=appel;
        this.telefono=telefono;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
