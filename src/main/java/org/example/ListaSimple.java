package org.example;

import java.util.Iterator;

public class ListaSimple implements Iterable<Integer>{

    private int tamano;
    private Nodo nodoPrimero;

    public ListaSimple(){
        super();
        this.tamano = 0;
        this.nodoPrimero = null;
    }

    public int getTamano(){
        return tamano;
    }

    public void setTamano(int tamano){
        this.tamano = tamano;
    }

    public Nodo getNodoPrimero(){
        return nodoPrimero;
    }

    public void setNodoPrimero(Nodo nodoPrimero){
        this.nodoPrimero = nodoPrimero;
    }

    public void agregarInicio(int valor){
        Nodo nodoNuevo = new Nodo(valor);

        if(estaVacia()){
            nodoPrimero = nodoNuevo;
        }else{
            nodoNuevo.setSiguiente(nodoPrimero);
            nodoPrimero = nodoNuevo;
        }
        tamano++;
    }

    public void agregarFinal(int valor){
        Nodo nodoNuevo = new Nodo(valor);

        if(estaVacia()){
            nodoPrimero = nodoNuevo;
        }else{
            Nodo aux = nodoPrimero;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nodoNuevo);
        }
        tamano++;
    }

    public void eliminar(int valor){
        if(nodoPrimero == null){
            return;
        }

        if(nodoPrimero.getValor() == valor){
            nodoPrimero = nodoPrimero.getSiguiente();
            return;
        }

        Nodo actual = nodoPrimero;
        while(actual.getSiguiente() != null && actual.getSiguiente().getValor() != valor){
            actual = actual.getSiguiente();
        }

        if(actual.getSiguiente() != null){
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
        }
    }

    public String print(){
        String lista = " ";
        for(Nodo aux = nodoPrimero; aux != null; aux = aux.getSiguiente()){
            lista += aux.getValor() + " -> ";
        }
        return lista;
    }

    public void imprimirArregloFor(int arreglo[]){
        for (int i = 0; i < arreglo.length; i++) {
            System.out.println(arreglo[i]);
        } 
    }

    public void imprimirArreglo(int arreglo[]){
        imprimirArreglo(arreglo, 0);
    }

    public void imprimirArreglo(int arreglo[], int i){
        if(i < arreglo.length){
            System.out.println(arreglo[i]);
            imprimirArreglo(arreglo, i+1);
        }else{
            System.out.println("Arreglo recorrido.");
        }
    }

    private boolean estaVacia() {
        return tamano == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListaSimpleIterador(nodoPrimero);
    }
}
