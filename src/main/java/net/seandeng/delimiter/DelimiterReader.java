package net.seandeng.delimiter;

import net.seandeng.delimiter.analysis.DelimiterAnalyser;
import net.seandeng.delimiter.analysis.DelimiterAnalyserImpl;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;

import java.io.Closeable;
import java.util.Arrays;

/**
 * Delimiter reader are all rad in event mode
 *
 * @author deng
 */
public class DelimiterReader implements Closeable {

    /**
     * Analyser
     */
    private final DelimiterAnalyser delimiterAnalyser;

    public DelimiterReader(ReadWorkbook readWorkbook) {
        delimiterAnalyser = new DelimiterAnalyserImpl(readWorkbook);
    }

    public DelimiterReader read(ReadFile... readFile) {
        delimiterAnalyser.analysis(Arrays.asList(readFile), Boolean.FALSE);
        return this;
    }

    public void finish() {
        if (delimiterAnalyser != null) {
            delimiterAnalyser.finish();
        }
    }

    @Override
    public void close() {
        finish();
    }
}
