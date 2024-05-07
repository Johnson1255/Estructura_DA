import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

            while((line = br.readLine()) != null){
                data = line.split(simboloSeparar);
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
}
