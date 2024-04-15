import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class Person {
    String nombre;
    String apellido;
    int edad;
    float calificacion;

    public Person(String nombre, String apellido, int edad, float calificacion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.calificacion = calificacion;
    }

    @Override
    public String toString(){
        return nombre + " " + apellido + "\n Edad: " + edad + " años\n" + "Calificacion: " + calificacion;
    }
}

class ListaDoblementeEnlazada {
    ListNode head;
    ListNode tail;

    public void add(Person val) {
        ListNode nuevoNodo = new ListNode(val);
        if(head == null){
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.next = nuevoNodo;
            nuevoNodo.prev = tail;
            tail = nuevoNodo;
        }
    }

    public void ImprimirLista(){
        ListNode current = head;
        while(current != null) {
            StdOut.println(current.val + " ");
            current = current.next;
        }
        StdOut.println();
    }
}

class ListNode {
    Person val;
    ListNode prev;
    ListNode next;

    public ListNode(Person val){
        this.val = val;
    }
}

public class Taller3 {

    private static String[] mujer = { "Isabella", "Olivia", "Alexis", "Sofía", "Victoria", "Amelia", "Alexa", "Julia",
            "Camila", "Alexandra", "Maya", "Andrea", "Ariana", "María", "Eva", "Angelina", "Valeria", "Natalia",
            "Isabel", "Sara", "Liliana", "Adriana", "Juliana", "Gabriela", "Daniela", "Valentina", "Lila", "Vivian",
            "Nora", "Ángela", "Elena", "Clara", "Eliana", "Alana", "Miranda", "Amanda", "Diana", "Ana", "Penélope",
            "Aurora", "Alexandría", "Lola", "Alicia", "Amaya", "Alexia", "Jazmín", "Mariana", "Alina", "Lucía",
            "Fátima", "Ximena", "Laura", "Cecilia", "Alejandra", "Esmeralda", "Verónica", "Daniella", "Miriam",
            "Carmen", "Iris", "Guadalupe", "Selena", "Fernanda", "Angélica", "Emilia", "Lía", "Tatiana", "Mónica",
            "Carolina", "Jimena", "Dulce", "Talía", "Estrella", "Brenda", "Lilian", "Paola", "Serena", "Celeste",
            "Viviana", "Elisa", "Melina", "Gloria", "Claudia", "Sandra", "Marisol", "Asia", "Ada", "Rosa", "Isabela",
            "Regina", "Elsa", "Perla", "Raquel", "Virginia", "Patricia", "Linda", "Marina", "Leila", "América",
            "Mercedes" };

    private static String[] hombre = { "Daniel", "David", "Gabriel", "Benjamín", "Samuel", "Lucas", "Ángel", "José",
            "Adrián", "Sebastián", "Xavier", "Juan", "Luis", "Diego", "Óliver", "Carlos", "Jesús", "Alex", "Max",
            "Alejandro", "Antonio", "Miguel", "Víctor", "Joel", "Santiago", "Elías", "Iván", "Óscar", "Leonardo",
            "Eduardo", "Alan", "Nicolás", "Jorge", "Omar", "Paúl", "Andrés", "Julián", "Josué", "Román", "Fernando",
            "Javier", "Abraham", "Ricardo", "Francisco", "César", "Mario", "Manuel", "Édgar", "Alexis", "Israel",
            "Mateo", "Héctor", "Sergio", "Emiliano", "Simón", "Rafael", "Martín", "Marco", "Roberto", "Pedro",
            "Emanuel", "Abel", "Rubén", "Fabián", "Emilio", "Joaquín", "Marcos", "Lorenzo", "Armando", "Adán", "Raúl",
            "Julio", "Enrique", "Gerardo", "Pablo", "Jaime", "Saúl", "Esteban", "Gustavo", "Rodrigo", "Arturo",
            "Mauricio", "Orlando", "Hugo", "Salvador", "Alfredo", "Maximiliano", "Ramón", "Ernesto", "Tobías", "Abram",
            "Noé", "Guillermo", "Ezequiel", "Lucián", "Alonzo", "Felipe", "Matías", "Tomás", "Jairo" };

    private static String[] apellidos = { "González", "Muñoz", "Rojas", "Díaz", "Pérez", "Soto", "Contreras", "Silva",
            "Martínez", "Sepúlveda", "Morales", "Rodríguez", "López", "Fuentes", "Hernández", "Torres", "Araya",
            "Flores", "Espinoza", "Valenzuela", "Castillo", "Tapia", "Reyes", "Gutiérrez", "Castro", "Pizarro",
            "Álvarez", "Vásquez", "Sánchez", "Fernández", "Ramírez", "Carrasco", "Gómez", "Cortés", "Herrera", "Núñez",
            "Jara", "Vergara", "Rivera", "Figueroa", "Riquelme", "García", "Miranda", "Bravo", "Vera", "Molina", "Vega",
            "Campos", "Sandoval", "Orellana", "Cárdenas", "Olivares", "Alarcón", "Gallardo", "Ortiz", "Garrido",
            "Salazar", "Guzmán", "Henríquez", "Saavedra", "Navarro", "Aguilera", "Parra", "Romero", "Aravena", "Vargas",
            "Vázquez", "Cáceres", "Yáñez", "Leiva", "Escobar", "Ruiz", "Valdés", "Vidal", "Salinas", "Zúñiga", "Peña",
            "Godoy", "Lagos", "Maldonado", "Bustos", "Medina", "Pino", "Palma", "Moreno", "Sanhueza", "Carvajal",
            "Navarrete", "Sáez", "Alvarado", "Donoso", "Poblete", "Bustamante", "Toro", "Ortega", "Venegas", "Guerrero",
            "Mendoza", "Farías", "San", "Martín" };

    public static ListaDoblementeEnlazada generar(int n) {
        ListaDoblementeEnlazada personas = new ListaDoblementeEnlazada();

        for(int i = 0; i < n; i++){
            int genero = StdRandom.uniform(0,2);
            String nombre1, nombre2;

            if(genero == 0){
                nombre1 = mujer[StdRandom.uniform(mujer.length)];
                nombre1 = new String(nombre1.getBytes(), StandardCharsets.UTF_8);
                nombre2 = mujer[StdRandom.uniform(mujer.length)];
                nombre2 = new String(nombre2.getBytes(), StandardCharsets.UTF_8);
            } else {
                nombre1 = hombre[StdRandom.uniform(hombre.length)];
                nombre2 = hombreÑStdRandom.uniform[(hombre.length)]; 
            }
            String apellido1 = apellidos[StdRandom.uniform(apellidos.length)];
            String apellido2 = apellidos[StdRandom.uniform(apellidos.length)];
            Person p = new Person(nombre1 + " " + nombre2, apellido1 + " " + apellido2, StdRandom.uniform(0, 80), (float) StdRandom.uniform(2.0, 100.0));
            personas.add(p);
        }
        return personas;
    }

    //GetSize
    private int getSize(ListaDoblementeEnlazada list){
        int size = 0;
        ListNode current = list.head;

        while(current != null){
            size++;
            current = current.next;
        }
        return size;
    }

    //Split
    public ListaDoblementeEnlazada[] split(ListaDoblementeEnlazada list) {
        ListaDoblementeEnlazada primeraMitad = new ListaDoblementeEnlazada();
        ListaDoblementeEnlazada segundaMitad = new ListaDoblementeEnlazada();

        int size = getSize(list);
        int mid = size / 2;

        ListNode current = list.head;
        int counteo = 0;

        while (current != null){

            if(counteo < mid){
                primeraMitad.add(current.val);
            } else {
                segundaMitad.add(current.val);
            }

            current = current.next;
            counteo++;
        }
        
        return new ListaDoblementeEnlazada[] {primeraMitad, segundaMitad};
    }

    //Merge
    public ListaDoblementeEnlazada merge(ListaDoblementeEnlazada lista1, ListaDoblementeEnlazada lista2) {
        ListaDoblementeEnlazada mergeList = new ListaDoblementeEnlazada();
        ListNode actual1 = lista1.head;
        ListNode actual2 = lista2.head;

        while (actual1 != null && actual2 != null){

            if(actual1.val.apellido.compareTo(actual2.val.apellido) < 0 || (actual1.val.apellido.compareTo(actual2.val.apellido) == 0 && actual1.val.nombre.compareTo(actual2.val.nombre) < 0)) {
                mergeList.add(actual1.val);
                actual1 = actual1.next;
            } else {
                mergeList.add(actual2.val);
                actual2 = actual2.next;
            }
        }

        while (actual1 != null) {
            mergeList.add(actual1.val);
            actual1 = actual1.next;
        }

        while (actual2 != null){
            mergeList.add(actual2.val);
            actual2 = actual2.next;
        }

        return mergeList;
    }

    //MergeSort
    public void mergesort(ListaDoblementeEnlazada lista) {
        if(lista.head == null || lista.head.next == null) {
            return;
        }

        ListaDoblementeEnlazada[] splitListas = split(lista);
        ListaDoblementeEnlazada primeraMitad = splitListas[0];
        ListaDoblementeEnlazada segundaMitad = splitListas[1];

        mergesort(primeraMitad);
        mergesort(segundaMitad);

        ListaDoblementeEnlazada sortedLista = merge(primeraMitad, segundaMitad);
        lista.head = sortedLista.head;
        lista.tail = sortedLista.tail;

    }

    // Partition
    public ListaDoblementeEnlazada partition(ListaDoblementeEnlazada lista){
        if(lista.head == null || lista.head.next == null) {
            return lista;
        }
        
        ListaDoblementeEnlazada menorQue = new ListaDoblementeEnlazada();
        ListaDoblementeEnlazada mayorQue = new ListaDoblementeEnlazada();

        ListNode pivote = lista.head;
        ListNode actual = lista.head.next;
        lista.head = lista.head.next;

        while(actual != null){

            if(actual.val.apellido.compareTo(pivote.val.apellido) < 0 || (actual.val.apellido.compareTo(pivote.val.apellido) == 0 && actual.val.nombre.compareTo(pivote.val.nombre) < 0)){
                menorQue.add(actual.val);
            } else {
                mayorQue.add(actual.val);
            }
            actual = actual.next;
        }

        ListaDoblementeEnlazada ListaSorted = new ListaDoblementeEnlazada();
        ListaSorted.head = partition(menorQue).head;
        ListaSorted.tail = menorQue.tail;
        menorQue.tail.next = pivote;
        pivote.prev = menorQue.tail;
        pivote.next = partition(mayorQue).head;

        if(pivote.next != null){
            pivote.next.prev = pivote;
        } else {
            ListaSorted.tail = pivote;
        }

        return ListaSorted;
    }

    //QuickSort
    public void Quicksort(ListaDoblementeEnlazada lista){
        lista.head = partition(lista).head;
    }

    public static void main(String[] args) {
        String id1 = "0001233";
        String id2 = "0009234";
        String id3 = "0005567";

        String concatenado = id1 + id2 + id3;

        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(concatenado.getBytes(StandardCharsets.UTF_8));

            java.math.BigInteger hashInt = new java.math.BigInteger(1, hash);

            ListaDoblementeEnlazada personas = generar(10) //Se puede llegar a colocar la cantidad requerida

            if(hashInt.mod(java.math.BigInteger.valueOf(2)).equals(java.math.BigInteger.ZERO)){
                StdOut.println("Hash de equipo PAR");
                mergesort(personas);
                personas.ImprimirLista();
            } else {
                StdOut.println("Hash de equipo impar");
                Quicksort(personas);
                personas.ImprimirLista();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/*class MergeSortListaDoblementeEnlazada {

}

class QuickSortListaDoblementeEnlazada {

}

public class Taller3 {
    public static void main(String[] args) {
        String id1 = "0001233";
        String id2 = "0009234";
        String id3 = "0005567";
        
        String concatenado = id1 + id2 + id3;
        
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(concatenado.getBytes());
            
            BigInteger hashInt = new BigInteger(1, hash);
            
            if (hashInt.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                System.out.println("Hash de equipo par");
                // Implementación del Mergesort
            } else {
                System.out.println("Hash de equipo impar");
                // Implementación del Quicksort
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}*/
