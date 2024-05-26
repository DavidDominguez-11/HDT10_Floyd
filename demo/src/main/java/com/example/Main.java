package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * La clase Main representa el punto de entrada principal del programa. Contiene el método main
 * que maneja la ejecución del programa para cargar un grafo desde un archivo, realizar operaciones
 * sobre el grafo y mostrar resultados al usuario.
 * @author David Dominguez
 * @version 1.0
 * @since 2024-05
 */
public class Main {

    /**
     * Método principal que maneja la ejecución del programa. Carga un grafo desde un archivo,
     * ejecuta un algoritmo de Floyd para encontrar las rutas más cortas, y permite al usuario
     * realizar diversas operaciones sobre el grafo, como encontrar la ruta más corta entre dos
     * ciudades, encontrar el centro del grafo y modificar el grafo.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        // Crear una instancia de la clase Graph
        Graph graph = new Graph();

        // Ruta del archivo que contiene los datos del grafo
        String rutaArchivo = "demo\\src\\main\\java\\com\\example\\guategrafo.txt";

        // Cargar el grafo desde el archivo
        loadGraphFromFile(rutaArchivo, graph);

        // Ejecutar el algoritmo de Floyd para encontrar las rutas más cortas
        FloydAlgorithm floyd = new FloydAlgorithm();
        FloydResult result = floyd.floydAlgorithm(graph);

        // Crear un objeto Scanner para la entrada del usuario desde la consola
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Ciclo principal del programa
        while (running) {
            // Mostrar opciones al usuario
            System.out.println("Opciones:");
            System.out.println("1. Encontrar la ruta más corta entre dos ciudades");
            System.out.println("2. Encontrar el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Finalizar el programa");
            System.out.print("Seleccione una opción: ");

            // Leer la opción del usuario
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Ejecutar la opción seleccionada por el usuario
            switch (option) {
                case 1:
                    // Encontrar la ruta más corta entre dos ciudades
                    System.out.print("Ingrese la ciudad origen: ");
                    String origen = scanner.nextLine();
                    System.out.print("Ingrese la ciudad destino: ");
                    String destino = scanner.nextLine();
                    findShortestPath(graph, floyd, result, origen, destino);
                    break;
                case 2:
                    // Encontrar el centro del grafo
                    String center = floyd.findCenter(graph, result.distances);
                    System.out.println("El centro del grafo es: " + center);
                    break;
                case 3:
                    // Modificar el grafo
                    modifyGraph(graph, scanner);
                    result = floyd.floydAlgorithm(graph); // Recalcular distancias
                    break;
                case 4:
                    // Finalizar el programa
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }

        // Cerrar el objeto Scanner
        scanner.close();
    }

    /**
     * Método para cargar un grafo desde un archivo.
     *
     * @param filename Nombre del archivo que contiene los datos del grafo.
     * @param graph    Grafo en el cual cargar los datos.
     */
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

    /**
     * Método para encontrar la ruta más corta entre dos ciudades en el grafo.
     *
     * @param graph    Grafo en el cual buscar la ruta.
     * @param floyd    Algoritmo de Floyd utilizado para calcular las distancias más cortas.
     * @param result   Resultado del algoritmo de Floyd que contiene las distancias más cortas.
     * @param origen   Ciudad de origen.
     * @param destino  Ciudad de destino.
     */
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

    /**
     * Método recursivo para reconstruir la ruta más corta entre dos ciudades.
     *
     * @param predecessors   Matriz de predecesores calculada por el algoritmo de Floyd.
     * @param src            Índice de la ciudad de origen.
     * @param dest           Índice de la ciudad de destino.
     * @param path           Lista que contendrá la ruta reconstruida.
     * @param vertexMap      Mapa para traducir índices de vértices a nombres de ciudades.
     */
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

    /**
     * Método para modificar el grafo.
     *
     * @param graph    Grafo a modificar.
     * @param scanner  Scanner para leer la entrada del usuario.
     */
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
