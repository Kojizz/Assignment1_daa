package org.example;
import org.example.algorithms.MergeSort;
import org.example.algorithms.QuickSort;
import org.example.algorithms.DeterministicSelect;
import org.example.algorithms.ClosestPair;
import org.example.util.Metrics;
import org.example.util.MetricsWriter;
import org.example.util.Point;
import java.io.IOException;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {

        int[] arr1 = generateRandomArray(1000, 10000);
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();

        Point[] points = generateRandomPoints(500, 1000);

        try (MetricsWriter writer = new MetricsWriter("results.csv")) {
            writer.writeHeader();

            Metrics mergeMetrics = new Metrics();
            mergeMetrics.startTimer();
            MergeSort.sort(arr1, mergeMetrics);
            mergeMetrics.stopTimer();
            writer.writeRow("MergeSort", mergeMetrics);


            Metrics quickMetrics = new Metrics();
            quickMetrics.startTimer();
            QuickSort.sort(arr2, quickMetrics);
            quickMetrics.stopTimer();
            writer.writeRow("QuickSort", quickMetrics);

            Metrics selectMetrics = new Metrics();
            selectMetrics.startTimer();
            int k = arr3.length / 2;
            DeterministicSelect.select(arr3, k, selectMetrics);
            selectMetrics.stopTimer();
            writer.writeRow("DeterministicSelect", selectMetrics);

            Metrics pairMetrics = new Metrics();
            pairMetrics.startTimer();
            double minDist = ClosestPair.closestPair(points, pairMetrics);
            pairMetrics.stopTimer();
            writer.writeRow("ClosestPair", pairMetrics);

            System.out.println("Results written to results.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static int[] generateRandomArray(int size, int maxVal) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxVal);
        }
        return arr;

    }


    private static Point[] generateRandomPoints(int size, int maxVal) {
        Random rand = new Random();
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(rand.nextInt(maxVal), rand.nextInt(maxVal));
        }
        return points;

    }
}
