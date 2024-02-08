package upb.ea.ea02_EstructurasBasicas;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class PilaArreglo<Item> implements Iterable<Item>{

    private Item[] pila;
    private int n;

    public PilaArreglo(int max) {
        pila = (Item[]) new Object[max];
    }

    public void push(Item s) throws Exception {
        if (n == pila.length)
            throw new Exception("La pila esta llena");
        pila[n++] = s;
    }

    public Item pop() throws Exception {
        if (n==0)
            throw new Exception("La pila esta vacia");
            Item tmp = pila[--n];
        pila[n] = null;
        return tmp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // TODO: Dar una implementación del Iterador para la pila
    // implements Iterable<Item>
    // public Iterator<Item> iterator() {


    // TODO: Implementar el procedimiento para cambiar el tamaño del arreglo
    // @SuppressWarnings("unchecked")
    // private void resize(int m)


    public static void main(String[] args) throws Exception {
        PilaArreglo<String> p = new PilaArreglo<>(5);
        assert(p.isEmpty());
        assert(p.size()==0);
        
        p.push("Hola");
        p.push("Mundo");
        //p.push("Adios");

        for(Iterator<String> i = p.iterator(); i.hasNext(); ){
            System.out.println(i.next());
        }


        //Otra forma de realizar el for
        for(String s: p){
            System.out.println(s);
        }

        assert(!p.isEmpty());
        assert(p.size()==2);


        
        assert(p.pop().equals("Mundo"));
        assert(p.size()==1);
        
        PilaArreglo<Integer> q = new PilaArreglo<>(5);
        //q.push(new Integer(null));
        q.push(Integer.valueOf(1));
        q.push(Integer.valueOf("2"));
        Integer a = q.pop();
        System.out.println(a);

        Integer b = q.pop();
        System.out.println(b);

        //StdOut.println(p.pop());
        //StdOut.println(p.size());

        //System.out.println(p.pop());
        //System.out.println(p.size());
        
        //System.out.println(p.pop());

        //StdOut.println("Ejemplo Pila");
    }

    @Override
    public Iterator iterator(){
        return new IteradorParaPilas();
    }

    private class IteradorParaPilas implements Iterator<Item> {
        int pos = n;

        @Override
        public boolean hasNext(){
            return pos>0;
        }

        @Override
        public Item next(){
            return pila[--pos];
        }
    }



}
    
// Ejercicios
// 1. Completar la implementación de la Pila utilizando un arreglo
// 2. Hacer algunas pruebas unitarias de la implementación
// 3. Hacer la implementacion genérica utilizando un parámetro de tipo
// 4. Implementar el iterador de la pila utilizando el orden LIFO
// 5. Hacer algunos ejemplos utilizando Pilas con distintos tipos de datos