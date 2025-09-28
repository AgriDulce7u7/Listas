package Listas;

public class Aplicacion {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();

        lista.agregarInicio(1);
        lista.agregarFinal(2);
        lista.agregarInicio(3);
        lista.eliminar(1);

        System.out.println(lista.print());
    }
}
