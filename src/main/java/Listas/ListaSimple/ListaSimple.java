package Listas.ListaSimple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimple<T> implements Iterable<T> {
    private Nodo<T> nodoPrimero;  // Referencia al primer nodo (cabeza)
    private int tamano;           // Cantidad de elementos en la lista

    public ListaSimple() {
        nodoPrimero = null;
        tamano = 0;
    }

    /**
     * 1. Agregar un elemento al inicio de la lista
     * @param dato Elemento a agregar
     */
    public void agregarInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        // Si la lista está vacía, el nuevo nodo es el primero
        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            // El nuevo nodo apunta al que era el primero
            nuevoNodo.setSiguienteNodo(nodoPrimero);
            nodoPrimero = nuevoNodo;  // Actualizar la cabeza
        }
        tamano++;
    }

    /**
     * 2. Agregar un elemento al final de la lista
     * @param dato Elemento a agregar
     */
    public void agregarFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        // Si la lista está vacía, agregar al inicio
        if (estaVacia()) {
            nodoPrimero = nuevoNodo;
        } else {
            // Buscar el último nodo
            Nodo<T> actual = nodoPrimero;
            while (actual.getSiguienteNodo() != null) {
                actual = actual.getSiguienteNodo();
            }
            // Conectar el último nodo con el nuevo
            actual.setSiguienteNodo(nuevoNodo);
        }
        tamano++;
    }

    /**
     * 3. Agregar un elemento en una posición específica
     * @param posicion Índice donde insertar (0 = inicio)
     * @param dato Elemento a agregar
     */
    public void agregar(int posicion, T dato) {
        // Validar que la posición sea válida
        if (!indiceValido(posicion) && posicion != tamano) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }

        // Si es la primera posición, usar agregarInicio
        if (posicion == 0) {
            agregarInicio(dato);
            return;
        }

        // Si es la última posición, usar agregarFinal
        if (posicion == tamano) {
            agregarFinal(dato);
            return;
        }

        // Insertar en posición intermedia
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        Nodo<T> anterior = obtenerNodo(posicion - 1);

        nuevoNodo.setSiguienteNodo(anterior.getSiguienteNodo());
        anterior.setSiguienteNodo(nuevoNodo);
        tamano++;
    }

    /**
     * 4. Obtener el valor de un nodo en una posición específica
     * @param posicion Índice del nodo
     * @return Valor almacenado en el nodo
     */
    public T obtenerValorNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }

        Nodo<T> nodo = obtenerNodo(posicion);
        return nodo.getDato();
    }

    /**
     * 5. Obtener la referencia a un nodo en una posición específica
     * @param posicion Índice del nodo
     * @return Referencia al nodo
     */
    public Nodo<T> obtenerNodo(int posicion) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }

        Nodo<T> actual = nodoPrimero;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguienteNodo();
        }
        return actual;
    }

    /**
     * 6. Obtener la posición de un elemento específico
     * @param dato Elemento a buscar
     * @return Posición del elemento (-1 si no se encuentra)
     */
    public int obtenerPosicionNodo(T dato) {
        Nodo<T> actual = nodoPrimero;
        int posicion = 0;

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getSiguienteNodo();
            posicion++;
        }
        return -1; // No encontrado
    }

    /**
     * 7. Validar si un índice es válido
     * @param indice Posición a validar
     * @return true si es válido, false en caso contrario
     */
    public boolean indiceValido(int indice) {
        return indice >= 0 && indice < tamano;
    }

    /**
     * 8. Verificar si la lista está vacía
     * @return true si está vacía, false en caso contrario
     */
    public boolean estaVacia() {
        return nodoPrimero == null || tamano == 0;
    }

    /**
     * 9. Eliminar el primer elemento de la lista
     * @return Elemento eliminado
     */
    public T eliminarPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        T datoEliminado = nodoPrimero.getDato();
        nodoPrimero = nodoPrimero.getSiguienteNodo();
        tamano--;
        return datoEliminado;
    }

    /**
     * 10. Eliminar el último elemento de la lista
     * @return Elemento eliminado
     */
    public T eliminarUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        // Si solo hay un elemento
        if (tamano == 1) {
            return eliminarPrimero();
        }

        // Buscar el penúltimo nodo
        Nodo<T> penultimo = obtenerNodo(tamano - 2);
        T datoEliminado = penultimo.getSiguienteNodo().getDato();
        penultimo.setSiguienteNodo(null);
        tamano--;
        return datoEliminado;
    }

    /**
     * 11. Eliminar un elemento específico por su valor
     * @param dato Elemento a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean eliminar(T dato) {
        if (estaVacia()) {
            return false;
        }

        // Si es el primer elemento
        if (nodoPrimero.getDato().equals(dato)) {
            eliminarPrimero();
            return true;
        }

        // Buscar el elemento en el resto de la lista
        Nodo<T> anterior = nodoPrimero;
        while (anterior.getSiguienteNodo() != null) {
            if (anterior.getSiguienteNodo().getDato().equals(dato)) {
                anterior.setSiguienteNodo(anterior.getSiguienteNodo().getSiguienteNodo());
                tamano--;
                return true;
            }
            anterior = anterior.getSiguienteNodo();
        }
        return false; // No encontrado
    }

    /**
     * 12. Modificar el valor de un nodo en una posición específica
     * @param posicion Índice del nodo a modificar
     * @param nuevoDato Nuevo valor
     */
    public void modificarNodo(int posicion, T nuevoDato) {
        if (!indiceValido(posicion)) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }

        Nodo<T> nodo = obtenerNodo(posicion);
        nodo.setDato(nuevoDato);
    }

    /**
     * 13. Ordenar la lista (requiere que T implemente Comparable)
     * Utiliza el algoritmo de burbuja para simplicidad educativa
     */
    @SuppressWarnings("unchecked")
    public void ordenarLista() {
        if (tamano <= 1) return;

        boolean huboIntercambio;
        do {
            huboIntercambio = false;
            Nodo<T> actual = nodoPrimero;

            while (actual.getSiguienteNodo() != null) {
                T datoActual = actual.getDato();
                T datoSiguiente = actual.getSiguienteNodo().getDato();

                // Comparar elementos (asumiendo que T implementa Comparable)
                if (((Comparable<T>) datoActual).compareTo(datoSiguiente) > 0) {
                    // Intercambiar datos
                    actual.setDato(datoSiguiente);
                    actual.getSiguienteNodo().setDato(datoActual);
                    huboIntercambio = true;
                }
                actual = actual.getSiguienteNodo();
            }
        } while (huboIntercambio);
    }

    /**
     * 14. Imprimir todos los elementos de la lista
     */
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("Lista vacía");
            return;
        }

        System.out.print("Lista: ");
        Nodo<T> actual = nodoPrimero;
        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguienteNodo() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getSiguienteNodo();
        }
        System.out.println();
    }

    /**
     * 15. Implementar Iterator para poder usar forEach
     * AQUÍ ESTÁ LA SOLUCIÓN A TU PROBLEMA:
     * Creamos una nueva instancia del iterador genérico y le pasamos
     * el primer nodo de nuestra lista
     * @return Iterator para recorrer la lista
     */
    @Override
    public Iterator<T> iterator() {
        return new ListaSimpleIterador<>(nodoPrimero);
    }

    /**
     * 16. Borrar todos los elementos de la lista
     */
    public void borrarLista() {
        nodoPrimero = null;
        tamano = 0;
    }

    /**
     * Obtener el tamaño de la lista
     * @return Número de elementos
     */
    public int getTamano() {
        return tamano;
    }
}