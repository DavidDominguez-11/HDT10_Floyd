package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        
        String rutaArchivo = "demo\\src\\main\\java\\com\\example\\guategrafo.txt";
        loadGraphFromFile(rutaArchivo, graph);

        FloydAlgorithm floyd = new FloydAlgorithm();
        FloydResult result = floyd.floydAlgorithm(graph);
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Opciones:");
            System.out.println("1. Encontrar la ruta más corta entre dos ciudades");
            System.out.println("2. Encontrar el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Finalizar el programa");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch (option) {
                case 1:
                    System.out.print("Ingrese la ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    String destino = scanner.nextLine();
                    findShortestPath(graph, floyd, result, origen, destino);
                    break;
                case 2:
                    String center = floyd.findCenter(graph, result.distances);
                    System.out.println("El centro del grafo es: " + center);
                    break;
                case 3:
                    modifyGraph(graph, scanner);
                    result = floyd.floydAlgorithm(graph); // Recalcular distancias
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
        
        scanner.close();
    }

    private static void loadGraphFromFile(String filename, Graph graph) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String city1 = parts[0];
                String city2 = parts[1];
                int distance = Integer.parseInt(parts[2]);

                graph.addVertex(city1);
                graph.addVertex(city2);
                graph.addEdge(city1, city2, distance);
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo: " + e.getMessage());
        }
    }

    private static void findShortestPath(Graph graph, FloydAlgorithm floyd, FloydResult result, String origen, String destino) {
        int[][] distances = result.distances;
        int[][] predecessors = result.predecessors;

        Map<String, Integer> vertexMap = new HashMap<>();
        Map<Integer, String> reverseVertexMap = new HashMap<>();
        int index = 0;
        for (String vertex : graph.adjacencyList.keySet()) {
            vertexMap.put(vertex, index);
            reverseVertexMap.put(index, vertex);
            index++;
        }

        Integer srcIndex = vertexMap.get(origen);
        Integer destIndex = vertexMap.get(destino);

        if (srcIndex == null || destIndex == null) {
            System.out.println("Ciudad origen o destino no existen en el grafo.");
            return;
        }

        if (distances[srcIndex][destIndex] == FloydAlgorithm.INFINITY) {
            System.out.println("No hay ruta de " + origen + " a " + destino);
            return;
        }

        System.out.println("La ruta más corta de " + origen + " a " + destino + " es " + distances[srcIndex][destIndex] + " KM");

        List<String> path = new ArrayList<>();
        reconstructPath(predecessors, srcIndex, destIndex, path, reverseVertexMap);

        System.out.println("La ruta es: " + String.join(" -> ", path));
    }

    private static void reconstructPath(int[][] predecessors, int src, int dest, List<String> path, Map<Integer, String> vertexMap) {
        if (src == dest) {
            path.add(vertexMap.get(src));
        } else if (predecessors[src][dest] == -1) {
            path.add("No hay camino");
        } else {
            reconstructPath(predecessors, src, predecessors[src][dest], path, vertexMap);
            path.add(vertexMap.get(dest));
        }
    }

    private static void modifyGraph(Graph graph, Scanner scanner) {
        System.out.println("Opciones de modificación:");
        System.out.println("a) Interrupción de tráfico entre un par de ciudades");
        System.out.println("b) Establecer una nueva conexión entre dos ciudades");
        System.out.print("Seleccione una opción: ");
        char option = scanner.nextLine().charAt(0);

        switch (option) {
            case 'a':
                System.out.print("Ingrese la ciudad origen: ");
                String source = scanner.nextLine();
                System.out.print("Ingrese la ciudad destino: ");
                String destination = scanner.nextLine();
                graph.removeEdge(source, destination);
                break;
            case 'b':
                System.out.print("Ingrese la ciudad origen: ");
                source = scanner.nextLine();
                System.out.print("Ingrese la ciudad destino: ");
                destination = scanner.nextLine();
                System.out.print("Ingrese la distancia en KM: ");
                int distance = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                graph.addEdge(source, destination, distance);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
