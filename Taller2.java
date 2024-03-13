import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Taller2 {
    
    class Punto2D{
        double x;
        double y;
    
        public Punto2D(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double distancia(Punto2D otroPunto) {
            return Math.sqrt(Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2));
        }
    }

}
