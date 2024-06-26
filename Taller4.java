import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdOut;

//Trabajo realizado por: Johnson Chen Yu
// ID: 000174359

public class Taller4 {
    private String ID;
    private String productID;
    private String userID;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private Date timeStamp;
    private String summary;
    private String text;


    public Taller4(String ID, String productID, String userID, String profileName, int helpfulnessNumerator, int helpfulnessDenominator, int score, long timeStamp, String summary, String text){
        this.ID = ID;
        this.productID = productID;
        this.userID = userID;
        this.profileName = profileName;
        this.helpfulnessNumerator = helpfulnessNumerator;
        this.helpfulnessDenominator = helpfulnessDenominator;
        this.score = score;
        this.timeStamp = new Date(timeStamp * 1000);
        this.summary = summary;
        this.text = text;
    }

    public String getID(){
        return ID;
    }

    public String getProductID(){
        return productID;
    }

    public String getUserID(){
        return userID;
    }

    public String getProfileName(){
        return profileName;
    }

    public int getHelpfulnessNumerator() {
        return helpfulnessNumerator;
    }

    public int getHelpfulnessDenominator() {
        return helpfulnessDenominator;
    }

    public int getScore() {
        return score;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getSummary() {
        return summary;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Formato de fecha
        return "Review{ ID = " + ID + 
        ", ProductID = " + productID + 
        ", UserID = " + userID + 
        ", ProfileName = " + profileName + 
        ", HelpfulnessNumerator = " + helpfulnessNumerator + 
        ", HelpfulnessDenominator = " + helpfulnessDenominator +
        ", Score = " + score + 
        ", TimeStamp = " + formato.format(timeStamp) + 
        ", Summary = " + summary + 
        ", Text = " + text + " }";
    }

    //Lectura de Cvs y devolucion de la lista de los Reviews
    public static List<Taller4> leerCsv(String ruta){
        List<Taller4> reviews = new ArrayList<>();
        BufferedReader br = null;
        String line = "";
        String simboloSeparar = ",";
        String[] data;

        try {
            br = new BufferedReader(new FileReader(ruta));

            br.readLine();

            while ((line = br.readLine()) != null) {
                // Utilizar expresiones regulares para dividir la línea respetando las comillas
                data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // Eliminar las comillas de los datos
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replaceAll("^\"|\"$", "");
                }
                // Crear el objeto Review y agregarlo a la lista
                reviews.add(new Taller4(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), Long.parseLong(data[7]), data[8], data[9]));
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if(br != null){
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return reviews;
    }

    //Calcular el puntaje total de cada producto
    public static Map<String, Integer> puntajeTotal(List<Taller4> reviews){
        Map<String, Integer> puntajes = new HashMap<>();
        String productId;
        int score;

        for(Taller4 review : reviews){
            productId = review.getProductID();
            score = review.getScore();
            puntajes.put(productId, puntajes.getOrDefault(productId, 0) + score);
        }

        return puntajes;
    }

    //Listar los Top-M productos en orden descendente depende del puntaje
    public static void listarTopM(Map<String, Integer> puntajes, int m){
        List<Map.Entry<String, Integer>> puntajesList = new ArrayList<>(puntajes.entrySet());
        puntajesList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for(int i = 0; i < Math.min(m, puntajesList.size()); i++){
            Map.Entry<String, Integer> entrada = puntajesList.get(i);
            StdOut.println("Producto: " + entrada.getKey() + ", Puntaje Total: " + entrada.getValue());
        }
    }

    //Creacion de arbol de busqueda para las reseñas
    public static TreeMap<Date, List<Taller4>> reviewsPorFecha(List<Taller4> reviews){
        TreeMap<Date, List<Taller4>> reviewsPorFecha = new TreeMap<>();
        Date fecha;

        for(Taller4 review : reviews){
            fecha = review.getTimeStamp();

            if(!reviewsPorFecha.containsKey(fecha)){
                reviewsPorFecha.put(fecha, new ArrayList<>());
            }
            reviewsPorFecha.get(fecha).add(review);
        }
        return reviewsPorFecha;
    }

    //Imprimir lista de reseñas limitadas a 30 caracteres
    public static void imprimirReseñas(List<Taller4> reviews){
        for(Taller4 review : reviews){
            StdOut.println("ID: " + review.getID().substring(0, Math.min(30, review.getID().length())) + 
            ", ProductID: " + review.getProductID().substring(0, Math.min(30, review.getProductID().length())) + 
            ", UserID: " + review.getUserID().substring(0, Math.min(30, review.getUserID().length())) + 
            ", ProfileName: " + review.getProfileName().substring(0, Math.min(30, review.getProfileName().length())) + 
            ", Score: " + review.getScore() + 
            ", TimeStamp: " + review.getTimeStamp() + 
            ", Summary: " + review.getSummary().substring(0, Math.min(30, review.getSummary().length())) + 
            ", Text: " + review.getText().substring(0, Math.min(30, review.getText().length())));
        }
    }

    //Consulta el Top-M en rango de fechas
    public static void listarTopMPorRango(Map<String, Integer> puntajes, TreeMap<Date, List<Taller4>> reviewsPorFecha, Date fechaInicio, Date fechaFinal, int m){
        TreeMap<Date, List<Taller4>> subMap = new TreeMap<>(reviewsPorFecha.subMap(fechaInicio, true, fechaFinal, true));
        List<Taller4> reviewsEnRango = new ArrayList<>();
        Map<String, Integer> puntajesEnRango;

        for(List<Taller4> list : subMap.values()){
            reviewsEnRango.addAll(list);
        }

        //Un pequeño manejo de errores con respecto a las fechas, solo para que no aparezca vacia
        if(reviewsEnRango.isEmpty()){
            StdOut.println("No hay productos en el rango de fechas que se ha especificado");
        } else {
            puntajesEnRango = puntajeTotal(reviewsEnRango);
            listarTopM(puntajesEnRango, m);
        }

    }

    public static void main(String[] args) {
        //Poner la ruta al archivo cvs de los productos de amazon, en este ejercicio esta con los datos de mi pc y es importante el doble back slash

        //List<Taller4> reviews = leerCsv("C:\\Users\\Johnson\\Downloads\\Dato de prueba\\Reviews.csv"); //Con los archivos completos
        //List<Taller4> reviews = leerCsv("C:\\Users\\Johnson\\Downloads\\Dato de prueba\\Reviews-Part-aa.csv"); //Parte aa del archivo
        //List<Taller4> reviews = leerCsv("C:\\Users\\Johnson\\Downloads\\Dato de prueba\\Reviews-Part-ab.csv"); //Parte ab del archivo
        List<Taller4> reviews = leerCsv("C:\\Users\\Johnson\\Downloads\\Dato de prueba\\Reviews-Part-ac.csv"); //Parte ac del archivo
        
        Map<String, Integer> puntajesTotales = puntajeTotal(reviews);

        //Uso del puntaje total
        StdOut.println("Puntajes Totales: ");
        for(Map.Entry<String, Integer> entrada : puntajesTotales.entrySet()){
            StdOut.println("Producto: " + entrada.getKey() + ", Puntaje Total: " + entrada.getValue());
        }

        //Uso del listarTopM
        int topM = 5; //Este numero puede llegar a ser variable depende de cuantos productos se quiera representar
        StdOut.println("\nTop-" + topM + " productos: ");
        listarTopM(puntajesTotales, topM);

        //Uso de los reviewsFecha
        TreeMap<Date, List<Taller4>> reviewsPorFecha = reviewsPorFecha(reviews);
        StdOut.println("\nReseñas por fecha: ");
        for(Map.Entry<Date, List<Taller4>> entrada : reviewsPorFecha.entrySet()){
            StdOut.println("Fecha: " + entrada.getKey());
            imprimirReseñas(entrada.getValue());
        }

        //Uso de listarTopM por rango
        
        // Quitar el comentario de abajo y comentar el date con fecha puesta si se quiere llegar a probar que no hay productos en el rango especificado
        //Date fechaInicial = new Date();
        //Date fechaFinal = new Date();
        
        //El metodo esta obsoleto, pero funciona
        //Nota: para calcular el año, se debe restar 1900 por como esta diseñado la funcion de Date de java, si no se le aplica puede llegar a aparecer errores. Año 2013 = Año 3913 si NO se le realiza el (2013 - 1900)
        Date fechaInicial = new Date(2000 - 1900, 0, 1); //Enero es 0 y Diciembre es 11
        Date fechaFinal = new Date(2013 - 1900, 11, 31);

        int topMRango = 5; //Lo mismo que con lo de listar top, este numero se puede cambiar depende de la cantidad de productos que se quiera ver

        StdOut.println("\nTop-" + topMRango + " productos en el rango de fechas de: " + fechaInicial + " y " + fechaFinal);
        listarTopMPorRango(puntajesTotales, reviewsPorFecha, fechaInicial, fechaFinal, topMRango);
    }
}
