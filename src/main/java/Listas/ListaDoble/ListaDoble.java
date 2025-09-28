package Listas.ListaDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaDoble<T extends Comparable<T>> implements Iterable<T> {

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int size;

    public ListaDoble() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    // Agregar al inicio
    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
        size++;
    }

    // Agregar al final
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        size++;
    }

    // Agregar en posición
    public void agregarEnPosicion(int posicion, T valor) {
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        if (posicion == 0) {
            agregarInicio(valor);
            return;
        }
        if (posicion == size) {
            agregarFinal(valor);
            return;
        }

        Nodo<T> siguiente = obtenerNodo(posicion);
        Nodo<T> anterior = siguiente.getAnterior();
        Nodo<T> nuevo = new Nodo<>(valor);

        anterior.setSiguiente(nuevo);
        nuevo.setAnterior(anterior);

        nuevo.setSiguiente(siguiente);
        siguiente.setAnterior(nuevo);

        size++;
    }

    // Obtener nodo en posición
    public Nodo<T> obtenerNodo(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        Nodo<T> actual;
        if (posicion <= size / 2) {
            actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
        } else {
            actual = cola;
            for (int i = size - 1; i > posicion; i--) {
                actual = actual.getAnterior();
            }
        }
        return actual;
    }

    // Obtener valor en posición
    public T obtenerValorNodo(int posicion) {
        return obtenerNodo(posicion).getValor();
    }

    // Sobrecarga: busca por VALOR (usa equals, maneja nulls)
    public int obtenerPosicionNodo(T valor) {
        int idx = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            T v = actual.getValor();
            if (valor == null) {
                if (v == null) return idx;
            } else {
                if (valor.equals(v)) return idx;
            }
            actual = actual.getSiguiente();
            idx++;
        }
        return -1;
    }

    /**
     * Devuelve true si la posición es válida para ACCEDER a un elemento:
     * posiciones permitidas: 0 .. size-1
     */
    public boolean indiceValido(int posicion) {
        return posicion >= 0 && posicion < size;
    }

    // Eliminar primero
    public void eliminarPrimero() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cabeza = cabeza.getSiguiente();
            cabeza.setAnterior(null);
        }
        size--;
    }

    // Eliminar último
    public void eliminarUltimo() {
        if (estaVacia()) return;
        if (cabeza == cola) {
            cabeza = cola = null;
        } else {
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        }
        size--;
    }

    // Eliminar por valor (primer match)
    public void eliminar(T valor) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getValor().equals(valor)) {
                if (actual == cabeza) eliminarPrimero();
                else if (actual == cola) eliminarUltimo();
                else {
                    Nodo<T> ant = actual.getAnterior();
                    Nodo<T> sig = actual.getSiguiente();
                    ant.setSiguiente(sig);
                    sig.setAnterior(ant);
                    size--;
                }
                return;
            }
            actual = actual.getSiguiente();
        }
    }

    // Modificar nodo por posición
    public void modificarNodo(int posicion, T nuevoValor) {
        if (posicion < 0 || posicion >= size) return;
        obtenerNodo(posicion).setValor(nuevoValor);
    }

    // Ordenar lista (usando burbuja, ascendente)
    public void ordenarLista() {
        if (size <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            while (actual != null && actual.getSiguiente() != null) {
                if (actual.getValor().compareTo(actual.getSiguiente().getValor()) > 0) {
                    T temp = actual.getValor();
                    actual.setValor(actual.getSiguiente().getValor());
                    actual.getSiguiente().setValor(temp);
                    cambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (cambiado);
    }

    // Imprimir lista
    public void imprimirLista() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.print(actual.getValor() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // Iterator (para usar foreach)
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (actual == null) throw new NoSuchElementException();
                T val = actual.getValor();
                actual = actual.getSiguiente();
                return val;
            }
        };
    }

    // Borrar toda la lista
    public void borrarLista() {
        cabeza = cola = null;
        size = 0;
    }

    public void invertirContenido() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return; // lista vacía o un solo elemento → no se hace nada
        }
        // Llamada recursiva inicial
        invertirRecursivo(cabeza, null);

        // intercambiar cabeza y cola
        Nodo<T> temp = cabeza;
        cabeza = cola;
        cola = temp;
    }

    private void invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        if (actual == null) return;

        // fase de ida: seguimos avanzando
        invertirRecursivo(actual.getSiguiente(), actual);

        // fase de vuelta: reasignamos punteros
        actual.setSiguiente(anterior);
        if (anterior != null) {
            anterior.setAnterior(actual);
        }
    }


}