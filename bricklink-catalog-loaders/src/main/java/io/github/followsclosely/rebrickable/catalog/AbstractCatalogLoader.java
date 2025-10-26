package io.github.followsclosely.rebrickable.catalog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Abstract loader for catalog files using Apache Commons CSV and Java Streams.
 * <p>
 * Usage: Subclass and implement {@link #apply(CSVRecord)} to map CSV records to your domain object.
 * <p>
 * <b>Important:</b> The returned stream must be closed after use to release file resources.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractCatalogLoader<R> {

    private final String pathToFile;

    public CSVFormat getCSVFormat() {
        return CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setDelimiter('\t')
                .setQuote(null) // This will disable quoting, and leave quotes in the data as-is.
                .setIgnoreEmptyLines(true)
                .get();
    }

    /**
     * Returns a stream of mapped objects from the catalog file.
     * <p>
     * <b>Note:</b> The caller must close the stream to release file resources.
     *
     * @return Stream of mapped objects
     * @throws IOException if the file cannot be opened
     */
    public Stream<R> stream() throws IOException {

        // Open the chain of streams and the CSVParser.
        // These will be closed by the stream's onClose handler.
        System.out.println(new File(".").getAbsolutePath());
        final FileInputStream inputStream = new FileInputStream(pathToFile);
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final CSVParser csvParser = CSVParser.builder().setReader(inputStreamReader).setFormat(getCSVFormat()).get();

        // Create a Spliterator from the CSVParser's iterator.
        // This is the core of converting an Iterable to a Stream.
        // The second parameter 'true' indicates a parallel stream is not supported.
        Stream<CSVRecord> csvRecordStream = StreamSupport.stream(csvParser.spliterator(), false);

        // Add an onClose handler to the stream. This is crucial.
        // This handler is automatically called when the stream is fully consumed
        // or when a terminal operation like forEach, collect, or close() is called.
        // It ensures that the underlying resources (csvParser, reader, and gzis) are properly closed.
        csvRecordStream = csvRecordStream.onClose(() -> {
            try {
                csvParser.close();
            } catch (IOException e) {
                AbstractCatalogLoader.log.error("Error closing CSVParser", e);
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                AbstractCatalogLoader.log.error("Error closing InputStreamReader", e);
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                AbstractCatalogLoader.log.error("Error closing FileInputStream", e);
            }
        });

        // This is a lazy operation and will only happen as the stream is consumed.
        return csvRecordStream.map(this::apply);
    }

    /**
     * Maps a CSVRecord to a domain object.
     *
     * @param record CSV record
     * @return mapped object
     */
    abstract R apply(CSVRecord record);

    public Integer parseInt(CSVRecord record, int index) {
        if (!record.isSet(index)) {
            return null;
        }
        String value = record.get(index);
        return (value == null || "?".equals(value) || value.isBlank()) ? null : Integer.parseInt(value);
    }

    public Long parseLong(CSVRecord record, int index) {
        if (!record.isSet(index)) {
            return null;
        }
        String value = record.get(index);
        return (value == null || "?".equals(value) || value.isBlank()) ? null : Long.parseLong(value);
    }

    public Double parseDouble(CSVRecord record, int index) {
        if (!record.isSet(index)) {
            return null;
        }
        String value = record.get(index);
        return (value == null || "?".equals(value) || value.isBlank()) ? null : Double.parseDouble(value);
    }
}
