package command_tester;

import org.example.algorithms.QuickSort;
import org.example.util.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void testQuickSort() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        Metrics quickMetrics = new Metrics();

        QuickSort.sort(arr, quickMetrics);

        assertArrayEquals(new int[]{1, 5, 7, 8, 9, 10}, arr);
    }
}
