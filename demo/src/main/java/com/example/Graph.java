package com.example;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String city) {
        adjacencyList.putIfAbsent(city, new HashMap<>());
    }

    public void addEdge(String source, String destination, int weight) {
        adjacencyList.get(source).put(destination, weight);
    }

    public void removeEdge(String source, String destination) {
        adjacencyList.get(source).remove(destination);
    }

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