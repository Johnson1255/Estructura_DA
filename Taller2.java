import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Punto2D { //Al querer reutilizar el codigo realizado en clase he obtenido algunos problemas
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
    EstacionBase estacionAsociada; //Ubicar la estacion en donde estan asociadas

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

    public static double medirTiempo(int N, int M, int k) {
        double tiempoTotal = 0;
        double tiempoSegundos;
        long inicio, fin;
        List<Persona> personas = crearPersonas(N);
        List<EstacionBase> estaciones = crearEstaciones(M);

        for(int i = 0; i < k; i++){
            inicio = System.nanoTime();
            asignarEstaciones(personas, estaciones);
            fin = System.nanoTime();
            tiempoSegundos = (fin - inicio) / (1 * Math.pow(10, 9)); //Para convertir los nano segundos a segundos
            tiempoTotal += tiempoSegundos;
        }
        return tiempoTotal / k;
    }

    //Main para probar con valores ya puestos, comentar si es que quiero poner los valores por mi cuenta
    
    public static void main(String[] args){
        int[] valoresN = {1000, 2000, 4000, 8000, 16000}; //Personas en la red
        int[] valoresM = {10, 50, 100}; //Estaciones base
        int k = 10; //Repeticiones
        double tiempoPromedio;
    
        for(int M : valoresM) {
            StdOut.println("Resultados para M = " + M + ": ");
            StdOut.println("N\tTiempo Promedio");
            
            for(int N : valoresN){
                tiempoPromedio = medirTiempo(N, M, k);
                StdOut.println(N + "\t" + tiempoPromedio);
            }
            StdOut.println(); //Para tener una linea de separacion entre cada 'M'
        }
    }
    
    //Como lo pidio en el ejercicio que en el main debia preguntar los valores de N y de M
    /*
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StdOut.println()("Ingrese el número de personas: ");
        int N = scanner.nextInt();

        StdOut.println()("Ingrese el número de estaciones baseM: ");
        int M = scanner.nextInt();

        scanner.close();

        StdOut.println()("Tiempo transcurrido para asignar estaciones: " + medirTiempo(N, M, M) + " segundos");
    }
    */
}