package Listas.ListaCircularDoble;

public class Nodo<T> {
    private T valor;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    public Nodo(T valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }
}

