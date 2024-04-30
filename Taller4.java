import java.util.Date;

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
}
