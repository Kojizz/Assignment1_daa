package org.example.util;

public class Metrics {
    private long comparisons;
    private long allocations;
    private int currentDepth;
    private int maxDepth;
    private long startTime;
    private long endTime;

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }


    public long getRunTimeNanos() {
        return endTime - startTime;

    }

    public double getRunTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public void incComparisons() {
        comparisons++;

    }


    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long c) {
        comparisons += c;
    }

    public long getComparisons() {
        return comparisons;
    }


    public void incAllocations() {
        allocations++;
    }

    public void incrementAllocations() {
        allocations++;
    }



    public void addAllocations(long a) {
        allocations += a;
    }

    public long getAllocations() {
        return allocations;

    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;

        }

    }

    public void leaveRecursion() {
        currentDepth--;
    }


    public void recordDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;

        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }



    public void setRunTime(long nanos) {
        this.endTime = this.startTime + nanos;
    }


}
