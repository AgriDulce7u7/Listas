package Listas.ListaCircular;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircular<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public ListaCircular() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean indiceValido(int index) {
        return index >= 0 && index < tamanio;
    }

    // Agregar al inicio
    public void agregarInicio(T dato) {
        Nodo<T> nodo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nodo;
            cola = nodo;
            cola.setSiguiente(cabeza);
        } else {
            nodo.setSiguiente(cabeza);
            cabeza = nodo;
            cola.setSiguiente(cabeza);
        }
        tamanio++;
    }

    // Agregar al final
    public void agregarFinal(T dato) {
        Nodo<T> nodo = new Nodo<>(dato);
        if (estaVacia()) {
            cabeza = nodo;
            cola = nodo;
            cola.setSiguiente(cabeza);
        } else {
            cola.setSiguiente(nodo);
            cola = nodo;
            cola.setSiguiente(cabeza);
        }
        tamanio++;
    }

    // Agregar en posición específica
    public void agregar(int index, T dato) {
        if (index < 0 || index > tamanio) throw new IndexOutOfBoundsException();
        if (index == 0) {
            agregarInicio(dato);
        } else if (index == tamanio) {
            agregarFinal(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato);
            Nodo<T> actual = cabeza;
            for (int i = 0; i < index - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
            tamanio++;
        }
    }

    // Obtener nodo en posición
    public Nodo<T> obtenerNodo(int index) {
        if (!indiceValido(index)) throw new IndexOutOfBoundsException();
        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    // Obtener valor de nodo en posición
    public T obtenerValorNodo(int index) {
        return obtenerNodo(index).getDato();
    }

    // Obtener posición de un valor
    public int obtenerPosicionNodo(T dato) {
        if (estaVacia()) return -1;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getSiguiente();
        }
        return -1;
    }

    // Eliminar primero
    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cola.setSiguiente(cabeza);
        }
        tamanio--;
    }

    // Eliminar último
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (tamanio == 1) {
            cabeza = null;
            cola = null;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != cola) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(cabeza);
            cola = actual;
        }
        tamanio--;
    }

    // Eliminar por valor
    public void eliminar(T dato) {
        if (estaVacia()) return;
        if (cabeza.getDato().equals(dato)) {
            eliminarPrimero();
            return;
        }
        Nodo<T> actual = cabeza;
        Nodo<T> anterior = null;
        do {
            if (actual.getDato().equals(dato)) {
                anterior.setSiguiente(actual.getSiguiente());
                if (actual == cola) cola = anterior;
                tamanio--;
                return;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        } while (actual != cabeza);
    }

    // Modificar valor en índice
    public void modificarNodo(int index, T nuevoDato) {
        Nodo<T> nodo = obtenerNodo(index);
        nodo.setDato(nuevoDato);
    }

    // Ordenar lista (burbuja)
    public void ordenarLista() {
        if (tamanio <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            for (int i = 0; i < tamanio - 1; i++) {
                Nodo<T> siguiente = actual.getSiguiente();
                if (actual.getDato().compareTo(siguiente.getDato()) > 0) {
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    // Imprimir lista
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> actual = cabeza;
        do {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        System.out.println();
    }

    // Iterador
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;
            private boolean primeraVez = true;

            @Override
            public boolean hasNext() {
                return actual != null && (primeraVez || actual != cabeza);
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                primeraVez = false;
                return dato;
            }
        };
    }

    // Borrar toda la lista
    public void borrarLista() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    // Invertir lista recursivamente
    public void invertirContenido() {
        if (tamanio <= 1) return;
        Nodo<T> cabezaOriginal = cabeza;
        cabeza = invertirRecursivo(cabeza, null, cabezaOriginal);
        cola = cabezaOriginal;
        cola.setSiguiente(cabeza);
    }

    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior, Nodo<T> cabezaOriginal) {
        Nodo<T> siguiente = actual.getSiguiente();
        actual.setSiguiente(anterior);
        if (siguiente == cabezaOriginal) {
            return actual; // nueva cabeza
        }
        return invertirRecursivo(siguiente, actual, cabezaOriginal);
    }
}
