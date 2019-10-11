import Control.Controlador;
import Estaticos.Estudiante;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class Main {

    public static void main(String[] args){


        //puerto donde esta.
        port(8081);

        staticFiles.location("/publico");
        get("/",((request, response) -> {
            ArrayList<Estudiante> estudiantes = Controlador.getInstance().getListaEstudiantes();
            Map<String,Object> paramet = new HashMap<>();
            paramet.put("estudiantes", estudiantes);
            return new FreeMarkerEngine().render(new ModelAndView(paramet,"listar.fml"));
        }));

        get("/read/:matricula",((request, response) -> {
            Estudiante estudiante = Controlador.getInstance().getEstudiante(Integer.parseInt(request.params("matricula")));
            Map<String,Object> paramet = new HashMap<>();
            paramet.put("estudiante", estudiante);
            return new FreeMarkerEngine().render(new ModelAndView(paramet,"ver.fml"));
        }));

        get("/create",((request, response) -> {
            return new FreeMarkerEngine().render(new ModelAndView(null,"registrar.fml"));
        }));

        post("/create",((request, response) -> {
            Estudiante nuevo = new Estudiante(Integer.parseInt(request.queryParams("matricula")), request.queryParams("nombre"), request.queryParams("apellido"), request.queryParams("telefono"));
            Controlador.getInstance().addEstudiante(nuevo);
            response.redirect("/", 303);
            return null;
        }));

        get("/update/:matricula",((request, response) -> {
            Estudiante mod = Controlador.getInstance().getEstudiante(Integer.parseInt(request.params("matricula")));
            Map<String,Object> paramet = new HashMap<>();
            paramet.put("estudiante", mod);
            paramet.put("modificar", true);
            return new FreeMarkerEngine().render(new ModelAndView(paramet,"registrar.fml"));
        }));

        post("/update", ((request, response) -> {
            Estudiante nuevo = new Estudiante(Integer.parseInt(request.queryParams("matricula")), request.queryParams("nombre"), request.queryParams("apellido"), request.queryParams("telefono"));
            Controlador.getInstance().updateEstudiante(Integer.parseInt(request.queryParams("matricula")),nuevo);
            response.redirect("/", 303);
            return null;
        }));

        post("/delete/:matricula", (request, response) -> {
            Controlador.getInstance().deleteEstudiante(Integer.parseInt(request.params(":matricula")));
            response.redirect("/",303);
            return null;
        });


    }
}
