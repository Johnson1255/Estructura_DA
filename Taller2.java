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
