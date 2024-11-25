/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import java.util.List;
import java.util.Map;

import java.util.*;

public class Inferencia {

    public void recorrer(Grafo graph) {
        // Obtener el mapa de adyacencias del grafo
        Map<Nodo, List<Nodo>> adyacencias = graph.getAdyacencias();
        // Recorrer el mapa de adyacencias
        for (Map.Entry<Nodo, List<Nodo>> entrada : adyacencias.entrySet()) {
            System.out.println("Hipótesis: " + entrada.getKey() + " | Hechos: " + entrada.getValue());
        }
    }

    public void imprimirHechosSeleccionados(List<Nodo> hechosSeleccionados) {
        System.out.println("Hechos seleccionados:");
        for (Nodo hecho : hechosSeleccionados) {
            System.out.println(hecho);
        }
    }

    public void inferir_hacia_adelante_con_BFS(Grafo graph, List<Nodo> hechosSeleccionados) {
  
        System.out.println("Estado actual del grafo:");
        recorrer(graph);

        imprimirHechosSeleccionados(hechosSeleccionados);

        Map<Nodo, List<Nodo>> adyacencias = graph.getAdyacencias();

        for (Map.Entry<Nodo, List<Nodo>> entrada : adyacencias.entrySet()) {
            Nodo hipotesis = entrada.getKey();
            List<Nodo> hechosAsociados = entrada.getValue();

            boolean esHipotesisValida = bfsValidacionHipotesis(hipotesis, hechosAsociados, hechosSeleccionados);

            // Mostrar resultado
            if (esHipotesisValida) {
                System.out.println("La hipótesis '" + hipotesis + "' es válida basado en los hechos seleccionados.");
            } else {
                System.out.println("La hipótesis '" + hipotesis + "' no es válida con los hechos seleccionados.");
            }
        }
    }

    public boolean bfsValidacionHipotesis(Nodo hipotesis, List<Nodo> hechosAsociados, List<Nodo> hechosSeleccionados) {
        Queue<Nodo> cola = new LinkedList<>();
        Set<Nodo> visitados = new HashSet<>();

        cola.add(hipotesis);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();

            if (visitados.contains(actual)) {
                continue;
            }

            visitados.add(actual);

            for (Nodo hecho : hechosAsociados) {
                if (!hechosSeleccionados.contains(hecho)) {
                    return false;
                }
            }
            cola.addAll(hechosAsociados);
        }
        return true;
    }

    public void limpiarHechosSeleccionados(List<Nodo> hechosSeleccionados) {
        hechosSeleccionados.clear(); 
        System.out.println("Hechos seleccionados eliminados. Puedes empezar una nueva deducción.");
    }
}

