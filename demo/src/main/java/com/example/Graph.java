package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase Graph representa un grafo ponderado utilizando una lista de adyacencia. Cada vértice
 * del grafo está representado por una ciudad y las aristas están ponderadas con distancias entre
 * ciudades.
 * @author David Dominguez
 * @version 1.0
 * @since 2024-05
 */
public class Graph {
    /** Lista de adyacencia que mapea cada ciudad a sus vecinos y las distancias correspondientes. */
    Map<String, Map<String, Integer>> adjacencyList;

    /** Constructor de la clase Graph. Inicializa la lista de adyacencia como un nuevo HashMap. */
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    /**
     * Método para agregar un vértice (ciudad) al grafo.
     *
     * @param city Nombre de la ciudad a agregar como vértice.
     */
    public void addVertex(String city) {
        adjacencyList.putIfAbsent(city, new HashMap<>());
    }

    /**
     * Método para agregar una arista ponderada (conexión entre ciudades) al grafo.
     *
     * @param source      Ciudad de origen de la arista.
     * @param destination Ciudad de destino de la arista.
     * @param weight      Peso (distancia) de la arista.
     */
    public void addEdge(String source, String destination, int weight) {
        addVertex(source);  // Asegura que el vértice fuente exista
        addVertex(destination);  // Asegura que el vértice destino exista
        adjacencyList.get(source).put(destination, weight);
    }

    /**
     * Método para eliminar una arista (conexión entre ciudades) del grafo.
     *
     * @param source      Ciudad de origen de la arista a eliminar.
     * @param destination Ciudad de destino de la arista a eliminar.
     */
    public void removeEdge(String source, String destination) {
        Map<String, Integer> neighbors = adjacencyList.get(source);
        if (neighbors != null) {
            neighbors.remove(destination);
        }
    }

    /**
     * Método para imprimir el grafo en la consola, mostrando cada ciudad y sus vecinos con las
     * distancias correspondientes.
     */
    public void printGraph() {
        for (String source : adjacencyList.keySet()) {
            System.out.print("(" + source + ") -> ");
            for (Map.Entry<String, Integer> neighbor : adjacencyList.get(source).entrySet()) {
                System.out.print("(" + neighbor.getKey() + ", " + neighbor.getValue() + ") ");
            }
            System.out.println();
        }
    }
}
