import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
            pq.add(new int[]{i, (int)distancia});

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
                cluster = entry.getKey()
            }
        }

        return new int[]{cluster, maxFrecuencia};
    }
}
