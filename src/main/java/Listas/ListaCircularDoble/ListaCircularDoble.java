package Listas.ListaCircularDoble;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaCircularDoble<T extends Comparable<T>> implements Iterable<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int size;

    public ListaCircularDoble() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    public boolean estaVacia() {
        return size == 0;
    }

    public boolean indiceValido(int index) {
        return index >= 0 && index < size;
    }

    // AGREGAR NODOS
    public void agregarFinal(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevo.setAnterior(cola);
            nuevo.setSiguiente(cabeza);
            cola.setSiguiente(nuevo);
            cabeza.setAnterior(nuevo);
            cola = nuevo;
        }
        size++;
    }

    public void agregarInicio(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.setSiguiente(cabeza);
            cabeza.setAnterior(cabeza);
        } else {
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(cola);
            cabeza.setAnterior(nuevo);
            cola.setSiguiente(nuevo);
            cabeza = nuevo;
        }
        size++;
    }

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

    // ELIMINAR NODOS
    public void eliminarPrimero() {
        if (!estaVacia()) {
            if (cabeza == cola) {
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.getSiguiente();
                cabeza.setAnterior(cola);
                cola.setSiguiente(cabeza);
            }
            size--;
        }
    }

    public void eliminarUltimo() {
        if (!estaVacia()) {
            if (cabeza == cola) {
                cabeza = null;
                cola = null;
            } else {
                cola = cola.getAnterior();
                cola.setSiguiente(cabeza);
                cabeza.setAnterior(cola);
            }
            size--;
        }
    }

    public void eliminar(T valor) {
        if (estaVacia()) return;

        Nodo<T> actual = cabeza;
        do {
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
        } while (actual != cabeza);

    }

    // OBTENER Y MODIFICAR
    public T obtenerValorNodo(int posicion) {
        return obtenerNodo(posicion).getValor();
    }

    public Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Índice inválido: " + posicion);
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    public int obtenerPosicionNodo(T valor) {
        Nodo<T> actual = cabeza;
        int idx = 0;
        if (estaVacia()) return -1;

        do {
            if (actual.getValor().equals(valor)) return idx;
            actual = actual.getSiguiente();
            idx++;
        } while (actual != cabeza);

        return -1;
    }

    public void modificarNodo(int posicion, T nuevoValor) {
        Nodo<T> nodo = obtenerNodo(posicion);
        nodo.setValor(nuevoValor);
    }

    // ORDENAR / INVERTIR / BORRAR
    public void ordenarLista() {
        if (size <= 1) return;
        boolean cambiado;
        do {
            cambiado = false;
            Nodo<T> actual = cabeza;
            do {
                Nodo<T> siguiente = actual.getSiguiente();
                if (actual != cola && actual.getValor().compareTo(siguiente.getValor()) > 0) {
                    T temp = actual.getValor();
                    actual.setValor(siguiente.getValor());
                    siguiente.setValor(temp);
                    cambiado = true;
                }
                actual = siguiente;
            } while (actual != cabeza);
        } while (cambiado);
    }

    // Método público
    public void invertirContenido() {
        if (cabeza != null && cabeza.getSiguiente() != null) {
            cabeza = invertirRecursivo(cabeza, null);
        }
    }

    // Método recursivo privado
    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        Nodo<T> siguiente = actual.getSiguiente();

        // Revertimos el enlace
        actual.setSiguiente(anterior);

        // Caso base: llegamos al último nodo
        if (siguiente == null) {
            return actual; // este se convierte en la nueva cabeza
        }

        // Paso recursivo
        return invertirRecursivo(siguiente, actual);
    }


    public void borrarLista() {
        cabeza = null;
        cola = null;
        size = 0;
    }

    // IMPRIMIR
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("(vacía)");
            return;
        }
        Nodo<T> actual = cabeza;
        do {
            System.out.print(actual.getValor() + " ");
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        System.out.println();
    }

    // ITERATOR
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = cabeza;
            private boolean primeraVez = true;

            @Override
            public boolean hasNext() {
                return actual != null && (primeraVez || actual != cabeza);
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T valor = actual.getValor();
                actual = actual.getSiguiente();
                primeraVez = false;
                return valor;
            }
        };
    }
}


