/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import static java.time.InstantSource.system;
import java.io.*;
import java.util.*;

public class Grafo {
    private Map<Nodo,List<Nodo>> adyacencias;

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }
 
    public void agregarVertice(Nodo nodo){
    adyacencias.putIfAbsent(nodo, new ArrayList<>());
    }
    
    public void agregarArista(Nodo nodo1, Nodo nodo2){
        adyacencias.get(nodo1).add(nodo2);
    }
    
    public void eliminarVertice(String vertice){
     
    }
    
    public void imprimirGrafo(){
        for(Nodo nodo:adyacencias.keySet()){
            System.out.print(nodo.toString() + "->");
            for(Nodo vecino: adyacencias.get(nodo)){
                System.out.print(vecino + " ");
            }
            System.out.println();
        }
    }

    // Guardar el grafo en un archivo de texto
    public void guardarEnArchivo(String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<Nodo, List<Nodo>> entry : adyacencias.entrySet()) {
                Nodo hipotesis = entry.getKey();
                List<Nodo> hechos = entry.getValue();

                // Construir la línea de texto
                StringBuilder linea = new StringBuilder();
                linea.append(hipotesis.atributo).append(":").append(hipotesis.valor).append(" -> ");

                for (int i = 0; i < hechos.size(); i++) {
                    Nodo hecho = hechos.get(i);
                    linea.append(hecho.atributo).append(":").append(hecho.valor);
                    if (i < hechos.size() - 1) {
                        linea.append(", ");
                    }
                }

                // Escribir la línea al archivo
                writer.write(linea.toString());
                writer.newLine();
            }
            System.out.println("Grafo guardado exitosamente en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el grafo: " + e.getMessage());
        }
    }

    // Cargar el grafo desde un archivo de texto
    public static Grafo cargarDesdeArchivo(String archivo) {
        Grafo grafo = new Grafo();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Parsear la línea
                String[] partes = linea.split("->");
                String[] hipotesisPartes = partes[0].trim().split(":");
                Nodo hipotesis = new Nodo(hipotesisPartes[1].trim(), hipotesisPartes[0].trim());

                // Agregar la hipótesis al grafo
                grafo.agregarVertice(hipotesis);

                if (partes.length > 1) {
                    String[] hechosPartes = partes[1].trim().split(",");
                    for (String hechoStr : hechosPartes) {
                        String[] hechoPartes = hechoStr.trim().split(":");
                        Nodo hecho = new Nodo(hechoPartes[1].trim(), hechoPartes[0].trim());
                        grafo.agregarArista(hipotesis, hecho);
                    }
                }
            }
            System.out.println("Grafo cargado exitosamente desde " + archivo);
        } catch (IOException e) {
            System.out.println("Error al cargar el grafo: " + e.getMessage());
        }
        return grafo;
    }

        public Map<Nodo, List<Nodo>> getAdyacencias() {
        return this.adyacencias;
    }
}
