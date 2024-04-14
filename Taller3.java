import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

class ListNode {
    int val;
    ListNode prev;
    ListNode next;

    public ListNode(int val){
        this.val = val;
    }
}

class ListaDoblementeEnlazada {
    ListNode head;
    ListNode tail;

    public void add(int val) {
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
            System.out.println(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class MergeSortListaDoblementeEnlazada {

    private int getSize(ListaDoblementeEnlazada list){
        int size = 0;
        ListNode current = list.head;

        while(current != null){
            size++;
            current = current.next;
        }
        return size;
    }

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

    public ListaDoblementeEnlazada merge(ListaDoblementeEnlazada lista1, ListaDoblementeEnlazada lista2) {
        ListaDoblementeEnlazada mergeList = new ListaDoblementeEnlazada();
        ListNode actual1 = lista1.head;
        ListNode actual2 = lista2.head;

        while (actual1 != null && actual2 != null){

            if(actual1.val < actual2.val) {
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
}

class QuickSortListaDoblementeEnlazada {

    public ListaDoblementeEnlazada[] partition(ListaDoblementeEnlazada lista){
        ListaDoblementeEnlazada menorQue = new ListaDoblementeEnlazada();
        ListaDoblementeEnlazada mayorQue = new ListaDoblementeEnlazada();

        ListNode pivote = lista.head;
        ListNode actual = lista.head.next;

        while(actual != null){

            if(actual.val < pivote.val){
                menorQue.add(actual.val);
            } else {
                mayorQue.add(actual.val);
            }
            actual = actual.next;
        }
        return new ListaDoblementeEnlazada[] {menorQue, mayorQue};
    }

    public void Quicksort(ListaDoblementeEnlazada lista){
        if(lista.head == null || lista.head.next == null){
            return;
        }

        ListaDoblementeEnlazada[] particiones = partition(lista);
        ListaDoblementeEnlazada menorQue = particiones[0];
        ListaDoblementeEnlazada mayorQue = particiones[1];

        Quicksort(menorQue);
        Quicksort(mayorQue);

        lista.head = menorQue.head;
        ListNode actual = menorQue.tail;
        actual.next = new ListNode(lista.head.val);
        actual.next.prev = actual;
        actual.next.next = mayorQue.head;
        mayorQue.head.prev = actual.next;
        lista.tail = mayorQue.tail;
    }

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
}
