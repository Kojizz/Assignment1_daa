package command_tester;

import org.example.algorithms.MergeSort;
import org.example.util.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    void testMergeSort() {
        int[] arr = {5, 2, 9, 1, 5, 6};

        Metrics mergeMetrics = new Metrics();

        MergeSort.sort(arr, mergeMetrics);
        assertArrayEquals(new int[]{1, 2, 5, 5, 6, 9}, arr);
    }
}
