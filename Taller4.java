import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
