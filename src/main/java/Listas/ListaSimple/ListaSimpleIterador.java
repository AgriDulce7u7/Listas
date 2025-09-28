package Listas.ListaSimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimpleIterador<T> implements Iterator<T> {
    private Nodo<T> nodoActual;  // Nodo donde está posicionado actualmente el iterador

    public ListaSimpleIterador(Nodo<T> primerNodo) {
        this.nodoActual = primerNodo;
    }

    /**
     * Verifica si hay más elementos por recorrer
     * @return true si hay siguiente elemento, false si llegamos al final
     */
    @Override
    public boolean hasNext() {
        return nodoActual != null;
    }

    /**
     * Obtiene el siguiente elemento y avanza el iterador
     * @return El dato del nodo actual
     * @throws NoSuchElementException si no hay más elementos
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No hay más elementos en la lista");
        }

        T dato = nodoActual.getDato();           // Obtener el dato actual
        nodoActual = nodoActual.getSiguienteNodo(); // Avanzar al siguiente nodo
        return dato;
    }

    /**
     * Método opcional para eliminar el último elemento retornado por next()
     * En esta implementación básica no se implemento para mantener simplicidad
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Operación remove no soportada en este iterador");
    }
}