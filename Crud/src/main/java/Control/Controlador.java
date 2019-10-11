package Control;

import Estaticos.Estudiante;

import java.util.ArrayList;

public class Controlador {
    private ArrayList<Estudiante> listaEstudiantes;
    private static Controlador instance;


    public Controlador() {
        this.listaEstudiantes = new ArrayList<Estudiante>();
    }

    public static Controlador getInstance() {
        if(instance==null){
            instance = new Controlador();
        }
        return instance;
    }


    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void addEstudiante(Estudiante nuevo){
        listaEstudiantes.add(nuevo);
    }

    public Estudiante getEstudiante(int matricula){
        for (Estudiante est: listaEstudiantes) {
            if (est.getMatricula()==matricula){
                return est;
            }
        }
        return null;
    }

    public Estudiante updateEstudiante(int matricula, Estudiante noveau){
        for (Estudiante est: listaEstudiantes) {
            if (est.getMatricula()==matricula){
                listaEstudiantes.set(listaEstudiantes.indexOf(est), noveau);
            }
        }
        return null;
    }

    public void deleteEstudiante(int matricula){
        for (Estudiante est: listaEstudiantes) {
            if (est.getMatricula()==matricula){
                listaEstudiantes.remove(listaEstudiantes.indexOf(est));
                return;
            }
        }
        return;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}
