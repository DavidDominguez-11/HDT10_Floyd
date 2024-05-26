package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void testAddVertex() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        assertTrue(graph.adjacencyList.containsKey("City1"));
    }

    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        graph.addVertex("City2");
        graph.addEdge("City1", "City2", 10);
        assertTrue(graph.adjacencyList.get("City1").containsKey("City2"));
        assertEquals(10, graph.adjacencyList.get("City1").get("City2").intValue());
    }

    @Test
    void testRemoveEdge() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        graph.addVertex("City2");
        graph.addEdge("City1", "City2", 10);
        graph.removeEdge("City1", "City2");
        assertFalse(graph.adjacencyList.get("City1").containsKey("City2"));
    }
}
