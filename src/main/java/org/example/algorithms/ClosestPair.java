package org.example.algorithms;

import org.example.util.Metrics;
import org.example.util.Point;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double closestPair(Point[] points, Metrics pairMetrics) {
        Point[] sortedByX = points.clone();
        Arrays.sort(sortedByX, Comparator.comparingDouble(p -> p.x));
        Point[] aux = new Point[points.length];
        return closestUtil(sortedByX, aux, 0, points.length - 1, pairMetrics, 1);
    }


    private static double closestUtil(Point[] pts, Point[] aux, int left, int right, Metrics metrics, int depth) {
        metrics.recordDepth(depth);

        if (right - left <= 3) {
            return bruteForce(pts, left, right, metrics);
        }

        int mid = (left + right) / 2;
        double dLeft = closestUtil(pts, aux, left, mid, metrics, depth + 1);
        double dRight = closestUtil(pts, aux, mid + 1, right, metrics, depth + 1);
        double d = Math.min(dLeft, dRight);

        int k = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - pts[mid].x) < d) {
                aux[k++] = pts[i];
                metrics.incrementAllocations();

            }

        }

        Arrays.sort(aux, 0, k, Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k && (aux[j].y - aux[i].y) < d; j++) {
                metrics.incrementComparisons();
                d = Math.min(d, ptsDistance(aux[i], aux[j]));
            }
        }

        return d;
    }

    private static double bruteForce(Point[] pts, int left, int right, Metrics metrics) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                min = Math.min(min, ptsDistance(pts[i], pts[j]));
            }

        }
        return min;

    }


    private static double ptsDistance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

}
