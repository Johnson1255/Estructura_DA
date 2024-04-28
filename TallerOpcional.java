import java.util.PriorityQueue;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;;

public class TallerOpcional {
    private static class Punto2D{
        double x, y;

        public Punto2D(double x, double y){
            this.x = x;
            this.y = y;
        }

        public double distancia(Punto2D otro){
            return Math.sqrt(Math.pow(this.x - otro.x, 2) + Math.pow(this.y - otro.y, 2));
        }
    }

    private static class UnionFind {
        int[] parent, size;
        int conteo;

        public UnionFind(int n){
            conteo = n;
            parent = new int[n];
            size = new int[n];

            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int p) {
            while (p != parent[p]){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            if(rootP == rootQ) return;

            if(size[rootP] < size[rootQ]){
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
            conteo--;
        }
    }

    private int[] parent;

    private int find(int i){
        while(i != parent[i]){
            i = parent[i];
        }
        return i;
    }

    public int clusterizar(Punto2D[] puntos, double Dmax){
        int n = puntos.length;
        double distancia;
        int[] edge;
        int u, v;
        UnionFind uf = new UnionFind(n);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[2], b[2]));

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                distancia = puntos[i].distancia(puntos[j]);
                if(distancia <= Dmax){
                    pq.add(new int[]{i, j, (int)distancia});
                }
            }
        }

        while (!pq.isEmpty()) {
            edge = pq.poll();
            u = edge[0];
            v = edge[1];

            if(uf.find(u) != uf.find(v)){
                uf.union(u, v);
            }
        }
        return uf.conteo;
    }

    public static Punto2D[] leerPuntos(String filename) throws IOException{
        List<Punto2D> puntos = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea;
        String[] valores;
        double x, y;

        while((linea = br.readLine()) != null){
            valores = linea.split(",");
            x = Double.parseDouble(valores[0]);
            y = Double.parseDouble(valores[1]);
            puntos.add(new Punto2D(x, y));
        }
        br.close();

        return puntos.toArray(new Punto2D[0]);
    }

    public int[] clasificar(Punto2D p, Punto2D[] puntos, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[2], b[2]));
        double distancia;
        Map<Integer, Integer> frecuencia = new HashMap<>();
        int indice, componente;
        int maxFrecuencia = 0;
        int cluster = -1;

        for(int i = 0; i < puntos.length; i++){
            distancia = p.distancia(puntos[i]);
            pq.add(new int[]{i, (int)distancia, 0});

            if(pq.size() > k){
                pq.poll();
            }
        }

        while(!pq.isEmpty()){
            indice = pq.poll()[0];
            componente = find(indice);
            frecuencia.put(componente, frecuencia.getOrDefault(componente, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : frecuencia.entrySet()){
            if(entry.getValue() > maxFrecuencia){
                maxFrecuencia = entry.getValue();
                cluster = entry.getKey();
            }
        }

        return new int[]{cluster, maxFrecuencia};
    }

    public void randomTest(){
        Punto2D[] puntos = new Punto2D[10];
        double x, y;
        int[] clusters = new int[10];

        for(int i = 0; i < 10; i++){
            x = -2 + Math.random()*4;
            y = -2 + Math.random()*4;
            puntos[i] = new Punto2D(x, y);
        }

        for(int i = 0; i < 10; i++){
            clusters[i] = clasificar(puntos[i], puntos, 3)[0];
        }

        for(int i = 0; i < 10; i++){
            StdOut.println("Punto " + i + " pertenece al cluster " + clusters[i]);
        }
    }

    public void graficarClusters(Punto2D[] puntos, int[] clusters){
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2, 2);
        StdDraw.setYscale(-2, 2);

        Color[] colores = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};
        int cluster;

        for(int i = 0; i < puntos.length; i++){
            cluster = clusters[i];
            StdDraw.setPenColor(colores[cluster % colores.length]);
            StdDraw.point(puntos[i].x, puntos[i].y);
        }
    }

    public void ajustarEscala(Punto2D[] puntos) {
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (Punto2D punto : puntos) {
            minX = Math.min(minX, punto.x);
            maxX = Math.max(maxX, punto.x);
            minY = Math.min(minY, punto.y);
            maxY = Math.max(maxY, punto.y);
        }

        double padding = 0.1;
        StdDraw.setXscale(minX - padding, maxX + padding);
        StdDraw.setYscale(minY - padding, maxY + padding);
    }

    public static void main(String[] args) {

        //Arreglos con cada nombre de los archivos, para realizar la actividad de forma consecutiva
        //Quitar el comentario del filenames y del for del main si se llega a utilizar

        /*
        String[] filenames = {
                "datapoints-100.csv",
                "datapoints-120.csv",
                "datapoints-150.csv",
                "datapoints-1000.csv",
                "datapoints-2500.csv",
                "datapoints-5000.csv",
                "datapoints-k=2-n=200.csv"
        };  */


        //Comentarios con el nombre del archivo de manera individual, para probar el ejercicio uno a uno
        //Si se quiere llegar a utilizar comentar el for (String filename : filenames) y elegir el documento con el que se quiere realizar la prueba

        String filename = "datapoints-100.csv";
        //String filename = "datapoints-120.csv";
        //String filename = "datapoints-150.csv";
        //String filename = "datapoints-1000.csv";
        //String filename = "datapoints-2500.csv";
        //String filename = "datapoints-5000.csv";
        //String filename = "datapoints-k=2-n=200.csv";

        double Dmax = 0.2; // Puedes ajustar este valor entre 0.1 y 0.3

        //for (String filename : filenames) { //Quitar el for comentado si se va a utilizar el arreglo de los archivos de forma consecutiva sin pausas
            try {
                long tiempoInicio = System.nanoTime();
                Punto2D[] puntos = leerPuntos(filename);
                TallerOpcional clustering = new TallerOpcional();
                int numClusters = clustering.clusterizar(puntos, Dmax);
                int[] clusters = new int[puntos.length];

                StdOut.println("Numero de clusteres obtenidos para " + filename + ": " + numClusters);

                clustering.parent = new int[puntos.length];
                for (int i = 0; i < puntos.length; i++) {
                    clustering.parent[i] = i;
                }

                for (int i = 0; i < puntos.length; i++) {
                    clusters[i] = clustering.find(i);
                }

                clustering.graficarClusters(puntos, clusters);
                clustering.randomTest();

                // Para calcular el tiempo de la ejecucion
                long tiempoFinal = System.nanoTime();
                long duracion = (tiempoFinal - tiempoInicio) / 1000000;

                StdOut.println("Tiempo de ejecución para " + filename + ": " + duracion + " ms");

            } catch (Exception e) {
                e.printStackTrace();
            }
        //} //Quitar la llave comentada si se llega a utilizar el for comentado de arriba
    }
}
