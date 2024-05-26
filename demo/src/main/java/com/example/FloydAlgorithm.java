package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase FloydAlgorithm implementa el algoritmo de Floyd para encontrar las rutas más cortas
 * en un grafo ponderado.
 * @author David Dominguez
 * @version 1.0
 * @since 2024-05
 */
public class FloydAlgorithm {
    /** Valor que representa la distancia infinita en el algoritmo de Floyd. */
    public static final int INFINITY = Integer.MAX_VALUE;

    /**
     * Método que ejecuta el algoritmo de Floyd en el grafo dado para encontrar las rutas más cortas.
     *
     * @param graph Grafo sobre el cual ejecutar el algoritmo.
     * @return El resultado del algoritmo de Floyd, que contiene las distancias más cortas y los predecesores.
     */
    public FloydResult floydAlgorithm(Graph graph) {
        // Crear un mapa para asignar índices a los vértices del grafo
        Map<String, Integer> vertexMap = new HashMap<>();
        int n = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(vertex, n++);
        }

        // Inicializar matrices para almacenar distancias y predecesores
        int[][] distances = new int[n][n];
        int[][] predecessors = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = INFINITY;
                predecessors[i][j] = -1;
            }
        }

        // Inicializar distancias y predecesores para los vértices adyacentes
        for (String source : graph.adjacencyList.keySet()) {
            int sourceIndex = vertexMap.get(source);
            distances[sourceIndex][sourceIndex] = 0;
            for (Map.Entry<String, Integer> neighbor : graph.adjacencyList.get(source).entrySet()) {
                int destinationIndex = vertexMap.get(neighbor.getKey());
                distances[sourceIndex][destinationIndex] = neighbor.getValue();
                predecessors[sourceIndex][destinationIndex] = sourceIndex;
            }
        }

        // Aplicar el algoritmo de Floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][k] != INFINITY && distances[k][j] != INFINITY
                            && distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        predecessors[i][j] = predecessors[k][j];
                    }
                }
            }
        }

        // Devolver el resultado del algoritmo de Floyd
        return new FloydResult(distances, predecessors);
    }

    /**
     * Método para imprimir las rutas más cortas en el grafo.
     *
     * @param graph     Grafo sobre el cual se calcularon las rutas más cortas.
     * @param distances Matriz de distancias más cortas entre todos los pares de vértices.
     */
    public void printShortestPaths(Graph graph, int[][] distances) {
        // Crear un mapa para traducir índices de vértices a nombres de ciudades
        Map<Integer, String> vertexMap = new HashMap<>();
        int index = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(index++, vertex);
        }

        // Imprimir las rutas más cortas
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                if (distances[i][j] != INFINITY) {
                    System.out.println("Ruta más corta de " + vertexMap.get(i) + " a " + vertexMap.get(j) + ": " + distances[i][j]);
                } else {
                    System.out.println("No hay ruta de " + vertexMap.get(i) + " a " + vertexMap.get(j));
                }
            }
        }
    }

    /**
     * Método para encontrar el centro del grafo, es decir, el vértice con la mínima excentricidad.
     *
     * @param graph     Grafo sobre el cual encontrar el centro.
     * @param distances Matriz de distancias más cortas entre todos los pares de vértices.
     * @return La ciudad que representa el centro del grafo.
     */
    public String findCenter(Graph graph, int[][] distances) {
        // Crear un mapa para asignar índices a los vértices del grafo
        Map<String, Integer> vertexMap = new HashMap<>();
        int n = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(vertex, n++);
        }

        // Encontrar el vértice con la mínima excentricidad
        int minEccentricity = Integer.MAX_VALUE;
        String center = "";
        for (String vertex : graph.adjacencyList.keySet()) {
            int eccentricity = 0;
            int vertexIndex = vertexMap.get(vertex);
            for (int i = 0; i < n; i++) {
                if (distances[vertexIndex][i] > eccentricity) {
                    eccentricity = distances[vertexIndex][i];
                }
            }
            if (eccentricity < minEccentricity) {
                minEccentricity = eccentricity;
                center = vertex;
            }
        }

        return center;
    }
}
