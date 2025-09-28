package Ejercicio17;

// Clase de prueba
public class PruebaInvertirLista {
    public static void main(String[] args) {
        // Crear una lista de enteros para probar
        ListaSimple<Integer> lista = new ListaSimple<>();

        // Caso 1: Lista vacía
        System.out.println("=== Caso 1: Lista vacía ===");
        lista.imprimirLista();
        lista.invertirContenido();
        System.out.println("Después de invertir:");
        lista.imprimirLista();
        System.out.println();

        // Caso 2: Lista con un elemento
        System.out.println("=== Caso 2: Lista con un elemento ===");
        lista.agregarAlFinal(42);
        lista.imprimirLista();
        lista.invertirContenido();
        System.out.println("Después de invertir:");
        lista.imprimirLista();
        System.out.println();

        // Caso 3: Lista con múltiples elementos
        System.out.println("=== Caso 3: Lista con múltiples elementos ===");
        ListaSimple<Integer> listaNormal = new ListaSimple<>();
        listaNormal.agregarAlFinal(1);
        listaNormal.agregarAlFinal(2);
        listaNormal.agregarAlFinal(3);
        listaNormal.agregarAlFinal(4);
        listaNormal.agregarAlFinal(5);

        System.out.println("Lista original:");
        listaNormal.imprimirLista();

        listaNormal.invertirContenido();

        System.out.println("Lista después de invertir:");
        listaNormal.imprimirLista();

        // Caso 4: Lista con strings para mostrar genericidad
        System.out.println("\n=== Caso 4: Lista genérica con Strings ===");
        ListaSimple<String> listaStrings = new ListaSimple<>();
        listaStrings.agregarAlFinal("Hola");
        listaStrings.agregarAlFinal("Mundo");
        listaStrings.agregarAlFinal("Java");

        System.out.println("Lista original de strings:");
        listaStrings.imprimirLista();

        listaStrings.invertirContenido();

        System.out.println("Lista de strings después de invertir:");
        listaStrings.imprimirLista();
    }
}