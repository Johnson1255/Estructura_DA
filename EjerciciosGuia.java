import org.w3c.dom.Node;

public class EjerciciosGuia {
    
    public static boolean esMatrizDiagonal(double[][] a){
        if (a.length != a[0].length) return false;
        boolean esDiagonal = true;
        for(int i = 0; i < a.length; i++)
            for(int j = 0; j < a[0].length; j++)
                if(i != j && a[i][j] != 0){
                    esDiagonal = false;
                    break;
                }
        return esDiagonal;
    }

    public static boolean esMatrizSimetrica(double[][] a){
        if (a.length != a[0].length) return false;
        boolean esSimetrica = true;
        for(int i = 0; i < a.length; i++)
            for(int j = i + 1; j < a[0].length; j++)
                if(a[i][j] != a[j][i]){
                    esSimetrica = false;
                    break;
                }
        return esSimetrica;
    }

    //Item deleteNode
    public class Item {
        //Define la clase Item, se puede incluir cualquier logica relevante
        private String data;
    
        public Item(String data) {
            this.data = data;
        }
    
        public String getData() {
            return data;
        }
    
        public void setData(String data) {
            this.data = data;
        }
    }

    public class Node {
        // Define la clase Node
        // Puedes incluir cualquier lógica relevante para tu aplicación
        Node prev;
        Node next;
        Item item;

        //Extra
        private Item item;
        private Node prev;
        private Node next;

        public Node(Item item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }

        // Métodos getter y setter para el atributo item
        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        // Métodos getter y setter para el nodo previo
        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        // Métodos getter y setter para el nodo siguiente
        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    
    public class ListaDoblementeEnlazada {
        private Node first;
        private Node last;
    
        // Otros métodos de la lista doblemente enlazada
    
        public Item deleteNode(Node x) {
            if (x == null) {
                return null; // No hay nada que borrar
            }
    
            // Guarda el item del nodo a ser borrado
            Item item = x.item;
    
            // Actualiza los enlaces de los nodos adyacentes
            if (x.prev != null) {
                x.prev.next = x.next;
            } else {
                // Si x es el primer nodo, actualiza el primer nodo
                first = x.next;
            }
            if (x.next != null) {
                x.next.prev = x.prev;
            } else {
                // Si x es el último nodo, actualiza el último nodo
                last = x.prev;
            }
    
            // Libera los enlaces del nodo a ser borrado
            x.prev = null;
            x.next = null;
    
            return item; // Retorna el item del nodo borrado
        }
    }
}
