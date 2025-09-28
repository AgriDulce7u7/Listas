package Ejercicio17;

class ListaSimple<T> {
    private Nodo<T> nodoPrimero;
    private int tamano;

    public ListaSimple() {
        this.nodoPrimero = null;
        this.tamano = 0;
    }

    // Método público para invertir el contenido de la lista
    public void invertirContenido() {
        // Caso base: si la lista está vacía o tiene un solo elemento, no hay nada que hacer
        if (nodoPrimero == null || nodoPrimero.siguiente == null) {
            return;
        }

        // Llamamos al método recursivo auxiliar
        // Le pasamos el primer nodo y null como nodo anterior
        nodoPrimero = invertirRecursivo(nodoPrimero, null);
    }

    /**
     * Método recursivo auxiliar que invierte la lista
     *
     * @param actual   - Nodo actual que estamos procesando
     * @param anterior - Nodo anterior al actual (será el siguiente del actual después de invertir)
     * @return - El nuevo primer nodo de la lista (que era el último antes de invertir)
     */
    private Nodo<T> invertirRecursivo(Nodo<T> actual, Nodo<T> anterior) {
        // Caso base: cuando llegamos al final de la lista (actual es null)
        // En este punto, anterior es el último nodo original, que será nuestro nuevo primero
        if (actual == null) {
            return anterior;
        }

        // Guardamos la referencia al siguiente nodo antes de perderla
        Nodo<T> siguienteOriginal = actual.siguiente;

        // Invertimos el enlace: el actual ahora apunta al anterior
        actual.siguiente = anterior;

        // Llamada recursiva: continuamos con el siguiente nodo original
        // Ahora 'actual' se convierte en el 'anterior' para la siguiente llamada
        return invertirRecursivo(siguienteOriginal, actual);
    }

    // Métodos auxiliares para probar la implementación
    public void agregarAlFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (nodoPrimero == null) {
            nodoPrimero = nuevoNodo;
        } else {
            Nodo<T> actual = nodoPrimero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    public void imprimirLista() {
        Nodo<T> actual = nodoPrimero;
        System.out.print("Lista: ");
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public boolean estaVacia() {
        return nodoPrimero == null;
    }

    public int getTamano() {
        return tamano;
    }
}
