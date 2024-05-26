package com.example;

public class FloydResult {
    public int[][] distances;
    public int[][] predecessors;

    public FloydResult(int[][] distances, int[][] predecessors) {
        this.distances = distances;
        this.predecessors = predecessors;
    }
}