import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Punto2D {
    double x;
    double y;

    public Punto2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distancia(Punto2D otroPunto) {
        return Math.sqrt(Math.pow(this.x - otroPunto.x, 2) + Math.pow(this.y - otroPunto.y, 2));
    }
}

class EstacionBase {
    int idEstacion;
    Punto2D ubicacion;

    public EstacionBase(int idEstacion, Punto2D ubicacion) {
        this.idEstacion = idEstacion;
        this.ubicacion = ubicacion;
    }
}

class Persona {
    int idPersona;
    Punto2D ubicacion;
    EstacionBase estacionAsociada;

    public Persona(int idPersona, Punto2D ubicacion) {
        this.idPersona = idPersona;
        this.ubicacion = ubicacion;
        this.estacionAsociada = null;
    }
}

public class Taller2 {

    public static List<Persona> crearPersonas(int N) {
        List<Persona> personas = new ArrayList<>();
        Random rand = new Random();
        double x, y;
        Punto2D ubicacion;

        for(int i = 0; i < N; i++) {
            x = rand.nextDouble() * 10000; //Genera la ubicaion aleatoria
            y = rand.nextDouble() * 10000;
            ubicacion = new Punto2D(x, y);
            personas.add(new Persona(i, ubicacion));
        }
        return personas;
    }

    public static List<EstacionBase> crearEstaciones(int M) {
        List<EstacionBase> estaciones = new ArrayList<>();
        Random rand = new Random();
        double x, y;
        Punto2D ubicacion;

        for(int i = 0; i < M; i++) {
            x = rand.nextDouble() * 10000;
            y = rand.nextDouble() * 10000;
            ubicacion = new Punto2D(x, y);
            estaciones.add(new EstacionBase(i, ubicacion));
        }
        return estaciones;
    }

    public static void asignarEstaciones(List<Persona> personas, List<EstacionBase> estaciones) {
        double distanciaMinima;
        double distancia;

        for(Persona persona : personas){
            distanciaMinima = Double.POSITIVE_INFINITY;

            for(EstacionBase estacion : estaciones) {
                distancia = persona.ubicacion.distancia(estacion.ubicacion);

                if(distancia < distanciaMinima){
                    distanciaMinima = distancia;
                    persona.estacionAsociada = estacion;
                }
            }
        }
    }

}