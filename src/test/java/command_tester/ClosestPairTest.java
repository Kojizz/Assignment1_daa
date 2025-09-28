package command_tester;
import org.example.algorithms.ClosestPair;
import org.example.util.Metrics;
import org.example.util.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPairTest {

    @Test
    void testClosestPair() {
        Point[] points = {
                new Point(2, 3),
                new Point(12, 30),
                new Point(40, 50),
                new Point(5, 1),
                new Point(12, 10),
                new Point(3, 4)

        };

        Metrics metrics = new Metrics();
        double dist = ClosestPair.closestPair(points, metrics);

        assertEquals(Math.sqrt(2), dist, 1e-9); // closest are (2,3) and (3,4)
    }

}
