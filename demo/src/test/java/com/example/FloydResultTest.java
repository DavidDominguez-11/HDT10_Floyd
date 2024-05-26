package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloydResultTest {

    @Test
    void testFloydResult() {
        int[][] distances = { { 0, 1 }, { 1, 0 } };
        int[][] predecessors = { { -1, 0 }, { 1, -1 } };
        FloydResult result = new FloydResult(distances, predecessors);

        assertArrayEquals(distances, result.distances);
        assertArrayEquals(predecessors, result.predecessors);
    }
}
