package com.example;

import java.util.HashMap;
import java.util.Map;

public class FloydAlgorithm {
    public static final int INFINITY = Integer.MAX_VALUE;

    public FloydResult floydAlgorithm(Graph graph) {
        Map<String, Integer> vertexMap = new HashMap<>();
        int n = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(vertex, n++);
        }

        int[][] distances = new int[n][n];
        int[][] predecessors = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = INFINITY;
                predecessors[i][j] = -1;
            }
        }

        for (String source : graph.adjacencyList.keySet()) {
            int sourceIndex = vertexMap.get(source);
            distances[sourceIndex][sourceIndex] = 0;
            for (Map.Entry<String, Integer> neighbor : graph.adjacencyList.get(source).entrySet()) {
                int destinationIndex = vertexMap.get(neighbor.getKey());
                distances[sourceIndex][destinationIndex] = neighbor.getValue();
                predecessors[sourceIndex][destinationIndex] = sourceIndex;
            }
        }

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

        return new FloydResult(distances, predecessors);
    }

    public void printShortestPaths(Graph graph, int[][] distances) {
        Map<Integer, String> vertexMap = new HashMap<>();
        int index = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(index++, vertex);
        }

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

    public String findCenter(Graph graph, int[][] distances) {
        Map<String, Integer> vertexMap = new HashMap<>();
        int n = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(vertex, n++);
        }

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
