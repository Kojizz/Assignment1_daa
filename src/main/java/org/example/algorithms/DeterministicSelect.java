package org.example.algorithms;

import org.example.util.Metrics;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k, Metrics metrics) {
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        return select(arr, 0, arr.length - 1, k, metrics, 1);
    }

    private static int select(int[] arr, int left, int right, int k, Metrics metrics, int depth) {
        metrics.recordDepth(depth);

        if (left == right) {
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right, metrics);

        pivotIndex = partition(arr, left, right, pivotIndex, metrics);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return select(arr, left, pivotIndex - 1, k, metrics, depth + 1);
        } else {
            return select(arr, pivotIndex + 1, right, k, metrics, depth + 1);
        }
    }


    private static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;

        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = (int) Math.ceil((double) n / 5);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);

            Arrays.sort(arr, subLeft, subRight + 1);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, medianIndex);
        }

        return medianOfMedians(arr, left, left + numMedians - 1, metrics);
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
