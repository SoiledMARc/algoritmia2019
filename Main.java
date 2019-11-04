package algoritmia.Arboles;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author noe_7
 */
public class Main {

    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        Nodo nodo;// set to null
        int numero;
        int opcion = 0;
        Scanner t = new Scanner(System.in);

        do {
            try {
                System.out.println("\nMENU "
                        + "\n 1.- Agregar"
                        + "\n 2.- Imprimir"
                        + "\n 3.- Terminar");
                opcion = t.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese numero");
                        numero = t.nextInt();
                        if (numero >= 0) {
                            arbol.insertar(numero);
                            System.out.println("Agregado");
                        }
                        break;
                    case 2:
                        arbol.imprimirPre();
                        arbol.imprimirIn();
                        break;
                    case 3:
                        System.out.println("Ingresar numero a eliminar");
                        numero = t.nextInt();
                        arbol.eliminar(numero);
                        break;
                    default:
                        System.out.println("Opcion no existe");
                        break;

                }
            } catch (InputMismatchException e) {
                System.out.println("Dato incorrecto, solo numero porfavor");
                opcion = 3;
            }
        } while (opcion != 4);

    }

}
