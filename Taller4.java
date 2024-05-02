import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Taller4 {
    private String productID;
    private String userID;
    private int score;
    private Date timestamp;

    public Taller4(String productID, String userID, int score, Date timestamp){
        this.productID = productID;
        this.userID = userID;
        this.score = score;
        this.timestamp = timestamp;
    }

    @Override
    public String toString(){
        return "Review { " + "ID del Producto: " + productID + "\n" + "ID del Usuario: " + userID + "\n Puntaje: " + score + "\n Tiempo: " + timestamp;
    }

    public static List<Taller4> leerCsv(String ruta){
        List<Taller4> reviews = new ArrayList<>();

        try {
            String linea;
            String[] campos;
            String productID, userID;
            int score;
            long timestamp;

            while((linea = br.readLine()) != null){

            }
        } catch (Exception e) {
            
        }
    }
}
