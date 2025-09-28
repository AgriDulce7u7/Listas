package Listas.ListaDoble;

public class Main {
    public static void main(String[] args) {
        ListaDoble<String> lista = new ListaDoble<>();

        lista.agregarFinal("Perro");
        lista.agregarFinal("Gato");
        lista.agregarInicio("Loro");

        System.out.println("Probando imprimirLista:");
        lista.imprimirLista();

        System.out.println("Probando obtenerValorNodo:");
        System.out.println("Valor en posición 1: " + lista.obtenerValorNodo(1)); // Perro

        System.out.println("Probando obtenerNodo:");
        System.out.println("Nodo en posición 2: " + lista.obtenerNodo(2).getValor()); // Gato

        System.out.println("Probando obtenerPosicionNodo:");
        System.out.println("Posición de 'Gato': " + lista.obtenerPosicionNodo("Gato")); // 2

        System.out.println("Probando indiceValido:");
        System.out.println("¿Índice 5 válido?: " + lista.indiceValido(5)); // false

        System.out.println("Probando estaVacia:");
        System.out.println("¿Lista vacía?: " + lista.estaVacia()); // false

        System.out.println("Probando eliminarPrimero:");
        lista.eliminarPrimero();
        lista.imprimirLista(); // Perro Gato

        System.out.println("Probando eliminarUltimo:");
        lista.eliminarUltimo();
        lista.imprimirLista(); // Perro

        System.out.println("Probando modificarNodo:");
        lista.modificarNodo(0, "Caballo");
        lista.imprimirLista(); // Caballo

        System.out.println("Probando agregarFinal nuevamente:");
        lista.agregarFinal("Tigre");
        lista.agregarFinal("Loro");

        System.out.println("Probando Iterator (for-each):");
        System.out.print("Recorriendo con iterator: ");
        for (String valor : lista) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("Probando ordenarLista:");
        lista.ordenarLista();
        lista.imprimirLista(); // Caballo Loro Tigre

        System.out.println("Probando agregarEnPosicion:");
        lista.agregarEnPosicion(1, "Pato");
        lista.imprimirLista(); // Caballo Pato Loro Tigre

        System.out.println("Probando eliminar por valor:");
        lista.eliminar("Loro");
        lista.imprimirLista(); // Caballo Pato Tigre

        System.out.println("Probando invertirContenido (recursivo):");
        lista.invertirContenido();
        lista.imprimirLista(); // Tigre Pato Caballo

        System.out.println("Probando borrarLista:");
        lista.borrarLista();
        lista.imprimirLista(); // (vacía)

        System.out.println("FIN DE PRUEBAS.");
    }
}