package Listas.ListaCircular;

public class Main {
    public static void main(String[] args) {
        ListaCircular<Integer> lista = new ListaCircular<>();

        lista.agregarFinal(5);
        lista.agregarFinal(10);
        lista.agregarFinal(15);
        lista.agregarFinal(20);

        System.out.println("📌 Lista original:");
        lista.imprimirLista(); // 5 10 15 20

        lista.agregarInicio(99);
        System.out.println("📌 Agregar al inicio (99):");
        lista.imprimirLista(); // 99 5 10 15 20

        lista.agregar(2, 77);
        System.out.println("📌 Agregar en posición 2 (77):");
        lista.imprimirLista(); // 99 5 77 10 15 20

        int valorPos2 = lista.obtenerValorNodo(2); // debería ser 15
        System.out.println("✅ Valor en posición 2: " + valorPos2);

        int posDel20 = lista.obtenerPosicionNodo(20); // debería ser 3
        System.out.println("✅ Posición del valor 20: " + posDel20);

        int posDel99 = lista.obtenerPosicionNodo(99); // debería ser -1
        System.out.println("✅ Posición del valor 99 (no existe): " + posDel99);

        lista.eliminarPrimero();
        System.out.println("📌 Eliminar primero:");
        lista.imprimirLista();

        lista.eliminarUltimo();
        System.out.println("📌 Eliminar último:");
        lista.imprimirLista();

        lista.eliminar(77);
        System.out.println("📌 Eliminar valor 77:");
        lista.imprimirLista();

        lista.modificarNodo(1, 55);
        System.out.println("📌 Modificar nodo en índice 1 a 55:");
        lista.imprimirLista();

        lista.ordenarLista();
        System.out.println("📌 Lista ordenada:");
        lista.imprimirLista();

        lista.invertirContenido();
        System.out.println("📌 Lista invertida:");
        lista.imprimirLista();

        System.out.println("📌 Recorrido con Iterator:");
        for (Integer n : lista) {
            System.out.print(n + " ");
        }
        System.out.println();

        lista.borrarLista();
        System.out.println("📌 Lista borrada:");
        lista.imprimirLista();
    }
}

