package Listas.ListaCircularDoble;

public class Main {
    public static void main(String[] args) {
        ListaCircularDoble<String> lista = new ListaCircularDoble<>();

        System.out.println("Agregar al inicio y al final:");
        lista.agregarFinal("Perro");
        lista.agregarFinal("Gato");
        lista.agregarInicio("Loro");
        lista.imprimirLista(); // Loro Perro Gato

        System.out.println("Obtener valor en posición 1:");
        System.out.println(lista.obtenerValorNodo(1)); // Perro

        System.out.println("Obtener nodo en posición 2:");
        System.out.println(lista.obtenerNodo(2).getValor()); // Gato

        System.out.println("Obtener posición del nodo con valor 'Gato':");
        System.out.println(lista.obtenerPosicionNodo("Gato")); // 2

        System.out.println("Validar índice 5:");
        System.out.println(lista.indiceValido(5)); // false

        System.out.println("¿La lista está vacía?:");
        System.out.println(lista.estaVacia()); // false

        System.out.println("Eliminar primero:");
        lista.eliminarPrimero();
        lista.imprimirLista(); // Perro Gato

        System.out.println("Eliminar último:");
        lista.eliminarUltimo();
        lista.imprimirLista(); // Perro

        System.out.println("Modificar nodo en posición 0 (Perro -> Caballo):");
        lista.modificarNodo(0, "Caballo");
        lista.imprimirLista(); // Caballo

        System.out.println("Agregar más datos al final:");
        lista.agregarFinal("Tigre");
        lista.agregarFinal("Loro");
        lista.imprimirLista(); // Caballo Tigre Loro

        System.out.println("Recorrer con iterator:");
        for (String valor : lista) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("Ordenar lista:");
        lista.ordenarLista();
        lista.imprimirLista(); // Caballo Loro Tigre

        System.out.println("Agregar en posición 1 (Pato):");
        lista.agregarEnPosicion(1, "Pato");
        lista.imprimirLista(); // Caballo Pato Loro Tigre

        System.out.println("Eliminar nodo con valor 'Loro':");
        lista.eliminar("Loro");
        lista.imprimirLista(); // Caballo Pato Tigre

        System.out.println("Invertir contenido:");
        lista.invertirContenido();
        lista.imprimirLista(); // Tigre Pato Caballo

        System.out.println("Borrar lista completa:");
        lista.borrarLista();
        lista.imprimirLista(); // (vacía)
    }
}
