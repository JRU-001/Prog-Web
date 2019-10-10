package Log;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

//http://itachi.avathartech.com:4567/opcion2.html

public class Main {

    public static void main(String[] args){

        String url;
        Scanner urlIn = new Scanner(System.in);
        Document Pagina = null;
        String CambLinea = null;
        System.out.println("\n\nEscriba la Pagina objetivo:");
        url = urlIn.nextLine();
        try {
            Pagina = Jsoup.connect(url).get();
            CambLinea = Jsoup.connect(url).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int lineas = Flineas(CambLinea);
        System.out.println("\n\nLa pagina contiene "+String.valueOf(lineas)+" Lineas");
        int parrafos = Fparrafos(Pagina);
        System.out.println("La pagina contiene "+String.valueOf(parrafos)+" Parrafos");
        int imagenes = Fimagen(Pagina);
        System.out.println("La pagina contiene "+String.valueOf(imagenes)+" Imagenes en los parrafos");
        int gets = Fformas(Pagina,"GET");
        System.out.println("\nLa pagina contiene "+String.valueOf(gets)+" Formas GET");
        int posts = Fformas(Pagina,"POST");
        System.out.println("La pagina contiene "+String.valueOf(posts)+" Formas POST");
        Finputs(Pagina);

        peticionAlDoc(Pagina,url);
    }

    private static int Flineas(String doc){
        //System.out.println(doc.html());
        String[] splitted;
        splitted = doc.split("\n");
        return splitted.length;
    }
    private static int Fparrafos(Document doc){
        Elements ele = doc.select("p");

        return ele.size();
    }
    private static int Fimagen(Document doc){
        Elements ele = doc.select("p img");

        return ele.size();
    }
    private static int Fformas(Document doc, String method){
        Elements ele = doc.select("form[method =\"" +method+"\"]");

        return ele.size();
    }
    private static void Finputs(Document doc){
        Elements ele = doc.body().select("form");
        for (int i=0;i<ele.size();i++){
            Elements elem = ele.select("input");
            System.out.println("\nForma : "+String.valueOf(i+1));
            for (Element el:elem)
                System.out.println("Input tipo: " + el.attr("type"));
        }
        return;
    }
    private static void peticionAlDoc(Document doc, String url){
        Elements postForms = doc.select("form[method=\"POST\"]");
        String newstr = url;
        if (null != url && url.length() > 0 )
        {
            int endIndex = url.lastIndexOf("/");
            if (endIndex != -1 || endIndex != 6 || endIndex != 7)
            {
                newstr = url.substring(0, endIndex);
            }
        }

        for (Element el:postForms) {
            try {
                Document postReq = Jsoup.connect(newstr + el.attr("action")).header("matricula", "2010-2196")
                        .data("asignatura","practica1").post();
                System.out.println("\n\nRespuesta de Peticion: ");
                System.out.println(postReq.html());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
