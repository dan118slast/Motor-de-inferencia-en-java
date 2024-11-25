package motor.de.inferencia;

import estructuras.Grafo;
import estructuras.Nodo;
import estructuras.Inferencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class Motor_de_inferencia {

    public static void llenarListaAdyacencias(Scanner sc, Grafo graph, Nodo vertice) {
        int flag2 = 0;
        while (flag2 == 0) {
            System.out.print("Atributo hecho:");
            String atributo_adyacente = sc.nextLine();
            System.out.print("Valor hecho:");
            String valor_adyacente = sc.nextLine();
            Nodo adyacente = new Nodo(valor_adyacente, atributo_adyacente);
            graph.agregarArista(vertice, adyacente);

            System.out.println("Finalizar hechos? yes[1] no[0]");
            flag2 = sc.nextInt();
            sc.nextLine();
        }
    }

    public static void main(String[] args) {

        int flag = 0;
        int flag3 = 0;
        Grafo graph = new Grafo();
        Inferencia inf = new Inferencia();
        Scanner sc = new Scanner(System.in);
        List<Nodo> hechosSeleccionados = new ArrayList<>();

        while (true) {
            System.out.println("Opciones: ");
            System.out.println("1. Agregar hipótesis y hechos");
            System.out.println("2. Seleccionar hechos para hacer una inferencia");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:

                    flag = 0;
                    while (flag == 0) {
                        System.out.print("Atributo (hipótesis): ");
                        String atributo = sc.nextLine();
                        System.out.print("Valor (hipótesis): ");
                        String valor = sc.nextLine();
                        Nodo vertice = new Nodo(valor, atributo);
                        graph.agregarVertice(vertice);
                        llenarListaAdyacencias(sc, graph, vertice);

                        System.out.println("¿Desea agregar más hipótesis? yes[0] no[1]");
                        flag = sc.nextInt();
                        sc.nextLine();
                    }
                    break;

                case 2:
                    // Ciclo para la selección de hechos y realizar la inferencia
                    while (true) {
                        System.out.println("Seleccionar hechos para inferir:");
                        flag = 0;
                        while (flag == 0) {
                            System.out.print("Atributo hecho: ");
                            String atributoHecho = sc.nextLine();
                            System.out.print("Valor hecho: ");
                            String valorHecho = sc.nextLine();
                            hechosSeleccionados.add(new Nodo(valorHecho, atributoHecho));

                            System.out.println("Finalizar selección de hechos? yes[1] no[0]");
                            flag = sc.nextInt();
                            sc.nextLine();  // Limpiar el buffer
                        }

                        // Realizar la inferencia usando BFS
                        inf.inferir_hacia_adelante_con_BFS(graph, hechosSeleccionados);

                        // Preguntar si desea limpiar la lista de hechos seleccionados para una nueva deducción
                        System.out.println("¿Desea eliminar los hechos seleccionados y hacer una nueva deducción? yes[1] no[0]");
                        int limpiarFlag = sc.nextInt();
                        sc.nextLine();  // Limpiar el buffer

                        if (limpiarFlag == 1) {
                            inf.limpiarHechosSeleccionados(hechosSeleccionados);
                        } else {
                            break;  // Salir al menú principal
                        }
                    }
                    break;

                case 3:
                    // Salir del programa
                    System.out.println("Saliendo del programa.");
                    sc.close();
                    return;

                case 4:
                    // Ver el grafo actual (hipótesis y hechos asociados)
                    System.out.println("Estado actual del grafo:");
                    graph.imprimirGrafo();
                    break;

                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
                    break;
            }
        }
    }
}
