package com.example;

/**
 * La clase FloydResult representa el resultado del algoritmo de Floyd. Contiene las matrices de
 * distancias más cortas entre todos los pares de vértices y las matrices de predecesores para
 * reconstruir las rutas más cortas.
 * @author David Dominguez
 * @version 1.0
 * @since 2024-05
 */
public class FloydResult {
    /** Matriz de distancias más cortas entre todos los pares de vértices. */
    public int[][] distances;

    /** Matriz de predecesores para reconstruir las rutas más cortas. */
    public int[][] predecessors;

    /**
     * Constructor de la clase FloydResult.
     *
     * @param distances    Matriz de distancias más cortas entre todos los pares de vértices.
     * @param predecessors Matriz de predecesores para reconstruir las rutas más cortas.
     */
    public FloydResult(int[][] distances, int[][] predecessors) {
        this.distances = distances;
        this.predecessors = predecessors;
    }
}
