package org.example.util;
import java.io.FileWriter;
import java.io.IOException;

public class MetricsWriter implements AutoCloseable {
    private final FileWriter writer;
    private boolean headerWritten = false;

    public MetricsWriter(String fileName) throws IOException {
        this.writer = new FileWriter(fileName, false); // overwrite
    }


    public void writeHeader() throws IOException {
        if (!headerWritten) {
            writer.write("algorithmName;runTimeMillis;comparisons;maxDepth;allocations\n");
            headerWritten = true;
        }
    }

    public void writeRow(String algorithmName, Metrics metrics) throws IOException {
        writer.write(String.format("%s;%.3f;%d;%d;%d\n",
                algorithmName,
                metrics.getRunTimeMillis(),
                metrics.getComparisons(),
                metrics.getMaxDepth(),
                metrics.getAllocations()));
    }


    @Override
    public void close() throws IOException {
        writer.close();
    }
}
