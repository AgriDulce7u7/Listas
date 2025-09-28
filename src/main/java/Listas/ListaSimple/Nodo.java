package Listas.ListaSimple;

public class Nodo<T> {
    private T dato;                    // Información que almacena el nodo
    private Nodo<T> siguienteNodo;    // Referencia al siguiente nodo

    public Nodo(T dato) {
        this.dato = dato;
        this.siguienteNodo = null;  // Inicialmente no apunta a ningún nodo
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguienteNodo() {
        return siguienteNodo;
    }

    public void setSiguienteNodo(Nodo<T> siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }
}