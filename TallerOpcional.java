import java.util.PriorityQueue;

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
}
