package command_tester;
import org.example.algorithms.DeterministicSelect;
import org.example.util.Metrics;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeterministicSelectTest {

    @Test
    void testSelect() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        Metrics metrics = new Metrics();

        // 0-based indexing: k=2 → 3rd smallest → should be 7
        int result = DeterministicSelect.select(arr, 2, metrics);
        assertEquals(7, result);

        // k=0 → smallest → 3
        assertEquals(3, DeterministicSelect.select(arr, 0, new Metrics()));

        // k=5 → largest → 20
        assertEquals(20, DeterministicSelect.select(arr, 5, new Metrics()));
    }
}
