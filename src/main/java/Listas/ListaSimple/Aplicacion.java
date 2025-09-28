package Listas.ListaSimple;

public class Aplicacion {
    public static void main(String[] args) {
        // Crear una lista de enteros
        ListaSimple<Integer> lista = new ListaSimple<>();

        System.out.println("=== Demostración de Lista Simple Enlazada ===");
        System.out.println("Lista inicial vacía: " + lista.estaVacia());

        // Agregar elementos
        System.out.println("\n--- Agregando elementos ---");
        lista.agregarInicio(10);
        lista.agregarFinal(20);
        lista.agregar(1, 15);
        lista.imprimirLista();
        System.out.println("Tamaño: " + lista.getTamano());

        // Buscar elementos
        System.out.println("\n--- Búsquedas ---");
        System.out.println("Posición de 15: " + lista.obtenerPosicionNodo(15));
        System.out.println("Valor en posición 0: " + lista.obtenerValorNodo(0));

        // Usar iterator con forEach - AQUÍ PRUEBAS EL ITERATOR
        System.out.println("\n--- Usando forEach con Iterator ---");
        System.out.print("Elementos: ");
        for (Integer valor : lista) {
            System.out.print(valor + " ");
        }
        System.out.println();

        // Modificar elemento
        System.out.println("\n--- Modificando elemento ---");
        lista.modificarNodo(1, 99);
        lista.imprimirLista();

        // Eliminar elementos
        System.out.println("\n--- Eliminando elementos ---");
        lista.eliminar(99);
        lista.imprimirLista();

        // Probar con Strings para mostrar genericidad
        System.out.println("\n=== Probando con Strings ===");
        ListaSimple<String> listaTexto = new ListaSimple<>();
        listaTexto.agregarInicio("Hola");
        listaTexto.agregarFinal("Mundo");
        listaTexto.agregar(1, "Hermoso");

        listaTexto.imprimirLista();

        System.out.print("Usando forEach con Strings: ");
        for (String palabra : listaTexto) {
            System.out.print("'" + palabra + "' ");
        }
        System.out.println();

        System.out.println("\nTamaño final de lista enteros: " + lista.getTamano());
        System.out.println("Tamaño final de lista strings: " + listaTexto.getTamano());
    }
}