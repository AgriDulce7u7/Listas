package org.example;

import java.util.Iterator;

public class ListaSimpleIterador implements Iterator<Integer> {

    Nodo aux;

    public ListaSimpleIterador(Nodo primero){
        aux = primero;
    }
    @Override
    public boolean hasNext() {
        return aux.getSiguiente() != null;
    }

    @Override
    public Integer next() {
        int valor = aux.getValor();
        aux = aux.getSiguiente();
        return valor;
    }
}
