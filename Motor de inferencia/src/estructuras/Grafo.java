/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import static java.time.InstantSource.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
        public Map<Nodo, List<Nodo>> getAdyacencias() {
        return this.adyacencias;
    }
}
