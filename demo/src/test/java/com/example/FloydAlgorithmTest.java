package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloydAlgorithmTest {

    @Test
    void testFloydAlgorithm() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        graph.addVertex("City2");
        graph.addVertex("City3");
        graph.addEdge("City1", "City2", 4);
        graph.addEdge("City2", "City3", 3);
        graph.addEdge("City1", "City3", 10);

        FloydAlgorithm floyd = new FloydAlgorithm();
        FloydResult result = floyd.floydAlgorithm(graph);

        assertEquals(7, result.distances[0][2]);
        assertEquals(1, result.predecessors[0][2]);
    }

    @Test
    void testFindCenter() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        graph.addVertex("City2");
        graph.addVertex("City3");
        graph.addEdge("City1", "City2", 4);
        graph.addEdge("City2", "City3", 3);
        graph.addEdge("City1", "City3", 10);

        FloydAlgorithm floyd = new FloydAlgorithm();
        FloydResult result = floyd.floydAlgorithm(graph);

        String center = floyd.findCenter(graph, result.distances);
        assertEquals("City1", center);
    }
}
